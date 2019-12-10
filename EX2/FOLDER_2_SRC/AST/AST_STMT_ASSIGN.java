package AST;

public class AST_STMT_ASSIGN extends AST_STMT
{
    public AST_VAR var;
    public AST_EXP exp;

    public AST_STMT_ASSIGN(AST_VAR var, AST_EXP exp)
    {
        PrintRule("stmt", "var := exp ;");
        this.var = var;
        this.exp = exp;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();
        if (exp != null) exp.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Assign\nvar := exp");

        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }
}
