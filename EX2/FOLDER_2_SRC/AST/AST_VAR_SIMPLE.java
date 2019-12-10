package AST;

public class AST_VAR_SIMPLE extends AST_VAR
{
    public String name;

    public AST_VAR_SIMPLE(String name)
    {
        PrintRule("var", String.format("ID(%s)", name));
        this.name = name;
    }

    public void PrintMe()
    {
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("Simple\nVar\n(%s)", name));
    }
}
