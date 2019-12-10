package AST;

public class AST_NEW_EXP extends AST_Node
{
    public String type;
    public AST_EXP exp;

    public AST_NEW_EXP(String type, AST_EXP exp)
    {

        if (exp != null) PrintRule("newExp", String.format("NEW ID(%s) [ exp ]", type));
        if (exp == null) PrintRule("newExp", String.format("NEW ID(%s)", type));

        this.type = type;
        this.exp = exp;
    }

    public void PrintMe()
    {
        if (exp != null) exp.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "NEW\nExp\n");

        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }

}
