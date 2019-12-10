package AST;

public class AST_VAR_SUBSCRIPT extends AST_VAR
{
    public AST_VAR var;
    public AST_EXP subscript;

    public AST_VAR_SUBSCRIPT(AST_VAR var, AST_EXP subscript)
    {
        PrintRule("var", "var [ exp ]");
        this.var = var;
        this.subscript = subscript;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();
        if (subscript != null) subscript.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "var[exp]");

        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (subscript != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, subscript.SerialNumber);
    }
}
