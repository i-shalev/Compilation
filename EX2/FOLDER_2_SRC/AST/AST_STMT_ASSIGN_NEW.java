package AST;

public class AST_STMT_ASSIGN_NEW extends AST_STMT
{
    public AST_VAR var;
    public AST_NEW_EXP newExp;

    public AST_STMT_ASSIGN_NEW(AST_VAR var, AST_NEW_EXP newExp)
    {
        PrintRule("stmt", "var = newExp ;");
        this.var = var;
        this.newExp = newExp;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();
        if (newExp != null) newExp.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Assign\nvar := newExp");

        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (newExp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, newExp.SerialNumber);
    }
}
