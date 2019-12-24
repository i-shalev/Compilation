package AST;

public class AST_New_Exp extends AST_Node
{
    public String type;
    public AST_Exp exp;

    public AST_New_Exp(String type, AST_Exp exp)
    {

        if (exp != null) PrintRule("newExp", String.format("NEW ID(%s) [ exp ]", type));
        if (exp == null) PrintRule("newExp", String.format("NEW ID(%s)", type));

        this.type = type;
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

}
