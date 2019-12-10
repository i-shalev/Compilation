package AST;

public class AST_EXP_WRAPPER extends AST_EXP
{
    public AST_EXP exp;

    public AST_EXP_WRAPPER(AST_EXP exp)
    {
        PrintRule("exp", "( exp )");

        this.exp = exp;
    }

    public void PrintMe()
    {
        if (exp != null) exp.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "(Exp)");

        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);

    }
}
