package AST;

public class AST_ARRAY_DEC extends AST_Node
{
    public String left;
    public String right;

    public AST_ARRAY_DEC(String left, String right)
    {
        PrintRule("arrayDec", "ARRAY ID = ID");

        this.left = left;
        this.right = right;
    }

    public void PrintMe()
    {
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("Array\nDEC\n(%s = %s[])", left, right));
    }
}
