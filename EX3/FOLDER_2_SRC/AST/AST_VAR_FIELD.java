package AST;

public class AST_VAR_FIELD extends AST_VAR
{
    public AST_VAR var;
    public String fieldName;

    public AST_VAR_FIELD(AST_VAR var, String fieldName)
    {
        PrintRule("var", String.format("var . ID(%s)", fieldName));

        this.var = var;
        this.fieldName = fieldName;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("var.field(%s)",fieldName));

        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
    }
}
