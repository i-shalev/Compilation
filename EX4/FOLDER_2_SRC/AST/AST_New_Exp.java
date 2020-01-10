package AST;

import IR.*;
import SYMBOL_TABLE.SymbolTable;
import TYPES.*;

public class AST_New_Exp extends AST_Exp
{
    public String typeName;
    public AST_Exp exp;
    public Type_Class classType;
    public int numMembers;

    public AST_New_Exp(String typeName, AST_Exp exp)
    {

        if (exp != null) PrintRule("newExp", String.format("NEW ID(%s) [ exp ]", typeName));
        if (exp == null) PrintRule("newExp", String.format("NEW ID(%s)", typeName));

        this.typeName = typeName;
        this.exp = exp;
    }

    public void PrintMe()
    {
        if (exp != null) exp.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "NEW\nExp\n");

        if (exp != null) AST_Graphviz.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }

    public Type SemantMe() throws Exception {
        Type newType = SymbolTable.find(typeName);

        if (!(newType instanceof Type_Class || newType instanceof Type_Array || newType == Type_Int.getInstance() || newType == Type_String.getInstance()))
            throw new SemanticException("Initialize not on an array or a class.");

        if (exp != null) { // Array init
            Type expType = exp.SemantMe();
            if (!(expType instanceof Type_Int))
                throw new SemanticException("Array size declared with a non-int");
        }

        else if (newType instanceof Type_Class)       // Class init
        {
            classType = (Type_Class)newType;
            numMembers = ((Type_Class)newType).members.size();
        }

        return newType;
    }

    public IRReg IRMe()
    {
        if(exp != null)     // Array init
        {
            IRReg sizeReg = exp.IRMe();
            IR.add(new IRcommand_Move(IRReg.a0, sizeReg));              // copy array size
            IR.add(new IRcommand_Addi(IRReg.a0, IRReg.a0, 1));    // add place to remember the size - first element is size
            IR.add(new IRcommand_Sll(IRReg.a0, IRReg.a0, 4));     // convert to size in bytes - shift 4 to left is to mul 2^4
            IR.add(new IRcommand_sbrk());                               // allocate heap memory, v0 contain the result
            IR.add(new IRcommand_Sw(sizeReg, IRReg.v0, 0));      // store size as first element
        }

        else                // Class init
        {
            IR.add(new IRcommand_Li(IRReg.a0, numMembers));  // copy class size
            IR.add(new IRcommand_Addi(IRReg.a0, IRReg.a0, 1));  // first element is vtable
            IR.add(new IRcommand_Sll(IRReg.a0, IRReg.a0, 4));  // convert to size in bytes
            IR.add(new IRcommand_sbrk());  // allocate heap memory, v0 contain the result
            if (classType.getFuncList().size() == 0)
            {
                IR.add(new IRcommand_Sw(IRReg.zero, IRReg.v0, 0));  // store zero as vtable address
            }
            else  // where there are methods there is a vtable
            {
                String vtableLabel = String.format("_%s_vtable", classType.name);
                IRReg vtable = new IRReg.TempReg();
                IR.add(new IRcommand_La(vtable, vtableLabel));  // get vtable
                IR.add(new IRcommand_Sw(vtable, IRReg.v0, 0));  // store vtable as first element
            }
            for (int i = 0; i < numMembers; i++)  // init all members
            {
                Object initVal = classType.initVals.get(i);
                if (initVal instanceof Integer)
                {
                    IR.add(new IRcommand_Li(IRReg.a0, (Integer)initVal));
                }
                else if (initVal instanceof String)
                {
                    IR.add(new IRcommand_La(IRReg.a0, (String)initVal));
                }
                IR.add(new IRcommand_Sw(IRReg.a0, IRReg.v0, (i + 1) * 4));
            }
        }
        return IRReg.v0;
    }
}
