package AST;

public class AST_EXP_STRING extends AST_EXP
{
    public String str;

    public AST_EXP_STRING(String str)
    {
        PrintRule("exp", String.format("STRING(%s)", str));

        this.str = str;
    }

    public void PrintMe()
    {
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("STRING(%s)", str));
    }
}