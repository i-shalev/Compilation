package AST;

import TYPES.*;

public class AST_Var_Subscript extends AST_Var
{
    public AST_Var var;
    public AST_Exp subscript;

    public AST_Var_Subscript(AST_Var var, AST_Exp subscript)
    {
        PrintRule("var", "var [ exp ]");
        this.var = var;
        this.subscript = subscript;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();
        if (subscript != null) subscript.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "var[exp]");

        if (var != null) AST_Graphviz.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (subscript != null) AST_Graphviz.getInstance().logEdge(SerialNumber, subscript.SerialNumber);
    }

    public Type SemantMe() throws Exception{
        if (subscript.SemantMe() != Type_Int.getInstance())
            throw new SemanticException("Var subscript - index is not an int");
        Type arrType = var.SemantMe();
        if (!(arrType instanceof Type_Array))
            throw new SemanticException("Var subscript - var is not an array");
        return ((Type_Array)arrType).elementType;
    }
}
