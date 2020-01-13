package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.*;
import IR.*;
import java.util.*;

public class AST_Exp_Func_Call extends AST_Exp
{
    public AST_Var instanceName;
    public String funcName;
    public AST_Exp_List args;
    public int numMethod =-1;
    public List<AST_Exp> args2 = new ArrayList<AST_Exp>();

    public AST_Exp_Func_Call(AST_Var var, String ID, AST_Exp_List expList)
    {
        if (var == null && expList == null) PrintRule("exp", "ID ( )");
        if (var == null && expList != null) PrintRule("exp", "ID ( expList )");
        if (var != null && expList == null) PrintRule("exp", "var . ID ()");
        if (var != null && expList != null) PrintRule("exp", "var . ID ( expList )");

        this.instanceName = var;
        this.funcName = ID;
        this.args = expList;
        for (AST_Exp_List it = args; it != null; it = it.tail)
        {
            args2.add(it.head);
        }
    }

    public void PrintMe()
    {
        if (instanceName != null) instanceName.PrintMe();
        if (args != null) args.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "Exp\nFunc");

        if (instanceName  != null) AST_Graphviz.getInstance().logEdge(SerialNumber, instanceName.SerialNumber);
        if (args != null) AST_Graphviz.getInstance().logEdge(SerialNumber, args.SerialNumber);
    }


    public Type SemantMe() throws Exception {
        Type_Func funcType = null;

        if (instanceName != null) {
            Type t = instanceName.SemantMe();
            if (!(t instanceof Type_Class))
                throw new SemanticException("instance is not of type class");
            Type_Class classType = (Type_Class) t;
            funcType = classType.getFuncField(funcName);
            for (int i = 0; i < classType.methods.size(); i++)
            {
                Symbol method = classType.methods.get(i);
                if (funcName.equals(method.name))
                {
                    numMethod = i;
                    funcName = ((Type_Func)method.type).fullName;
                    break;
                }
            }
        }
        else {
            Type t = SymbolTable.find(funcName);
            if (!(t instanceof Type_Func))
                throw new SemanticException("function not declared");
            funcType = (Type_Func) t;
        }
        if (funcType == null)
            throw new SemanticException("function not declared");

        Type_List argsTypes = args != null ? args.SemantMe() : null;
        if (!funcType.IsValidTypeList(argsTypes))
            throw new SemanticException("function arguments mismatch");

        return funcType.returnType;
    }

    public IRReg IRMe()
    {
        switch (funcName)
        {
            case "PrintInt":
                IR.add(new IRcommand_PrintInt(args2.get(0).IRMe()));
                break;
            case "PrintString":
                IR.add(new IRcommand_PrintString(args2.get(0).IRMe()));
                break;
            case "PrintTrace":
                IRReg addrReg = new IRReg.TempReg();
                String printTraceLabel = IR.uniqueLabel("print_trace_loop");
                IR.add(new IRcommand_Move(addrReg, IRReg.fp)); // we loop over all fp's
                IR.add(new IRcommand_Label(printTraceLabel));  // start of loop
                IR.add(new IRcommand_Lw(IRReg.a0, addrReg, 2 * 4));  // get function name
                IR.add(new IRcommand_PrintString(IRReg.a0));
                IR.add(new IRcommand_Lw(addrReg, addrReg, 0));  // deference and get next fp
                IR.add(new IRcommand_bne(addrReg, IRReg.zero, printTraceLabel));  // loop condition
                break;
            default:
                IR.add(new IRcommand_Addi(IRReg.sp, IRReg.sp, -(args2.size() + 1) * 4));  // lower stack
                if (numMethod != -1)  // class method
                {
                    IRReg instanceReg;
                    if (instanceName != null)
                    {
                        instanceReg = instanceName.IRMe();  // get instance
                        IR.add(new IRcommand_Lw(instanceReg, instanceReg, 0));  // dereference instance
                    }
                    else
                    {
                        instanceReg = new IRReg.TempReg();
                        IR.add(new IRcommand_Lw(instanceReg, IRReg.fp, 3 * 4));  // get "this"
                    }
                    IR.add(new IRcommand_beq(instanceReg, IRReg.zero, "exit_invalid_dereference"));  // runtime check
                    IR.add(new IRcommand_Sw(instanceReg, IRReg.sp, 0 * 4));  // add "this" as first argument
                    for (int i = 0; i < args2.size(); i++)  // add arguments
                    {
                        IR.add(new IRcommand_Sw(args2.get(i).IRMe(), IRReg.sp, (i + 1) * 4));
                    }
                    IR.add(new IRcommand_Lw(instanceReg, instanceReg, 0));  // dereference vtable
                    IR.add(new IRcommand_Lw(instanceReg, instanceReg, numMethod * 4));  // dereference method
                    IR.add(new IRcommand_Jalr(instanceReg));  // actual call to method
                }
                else  // global function
                {
                    IR.add(new IRcommand_Sw(IRReg.zero, IRReg.sp, 0 * 4));  // add zero as first argument
                    for (int i = 0; i < args2.size(); i++)  // add arguments
                    {
                        IR.add(new IRcommand_Sw(args2.get(i).IRMe(), IRReg.sp, (i + 1) * 4));
                    }
                    IR.add(new IRcommand_Jal("_" + funcName));  // actual call to function
                }
                IR.add(new IRcommand_Addi(IRReg.sp, IRReg.sp, (args2.size() + 1) * 4));  // fold stack
                IRReg retVal = new IRReg.TempReg();
                IR.add(new IRcommand_Move(retVal, IRReg.v0));  // v0 store the return value
                return retVal;
        }
        return null;
    }
}
