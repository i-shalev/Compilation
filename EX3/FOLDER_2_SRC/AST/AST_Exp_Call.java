package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.*;

public class AST_Exp_Call extends AST_Exp
{
    public AST_Var instanceName;
    public String funcName;
    public AST_Exp_List args;

    public AST_Exp_Call(AST_Var var, String ID, AST_Exp_List expList)
    {
        if (var == null && expList == null) PrintRule("exp", "ID ( )");
        if (var == null && expList != null) PrintRule("exp", "ID ( expList )");
        if (var != null && expList == null) PrintRule("exp", "var . ID ()");
        if (var != null && expList != null) PrintRule("exp", "var . ID ( expList )");

        this.instanceName = var;
        this.funcName = ID;
        this.args = expList;
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
                throw new Exception("instance is not of type class");
            Type_Class instanceType = (Type_Class) t;
            funcType = instanceType.getFuncField(funcName);
        } else {
            Type t = SymbolTable.find(funcName);
            if (!(t instanceof Type_Func))
                throw new Exception("function not declared");
            funcType = (Type_Func) t;
        }
        if (funcType == null)
            throw new Exception("function not declared");

        Type_List argsTypes = args != null ? args.SemantMe() : null;
        if (!funcType.IsValidTypeList(argsTypes))
            throw new Exception("function arguments mismatch");

        return funcType.returnType;
    }
}
