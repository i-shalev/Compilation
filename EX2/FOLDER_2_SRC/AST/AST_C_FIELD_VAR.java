package AST;

public class AST_C_FIELD_VAR extends AST_C_FIELD
{
    public AST_VAR_DEC varDec;

    public AST_C_FIELD_VAR(AST_VAR_DEC varDec)
    {
        PrintRule("cField", "varDec");
        this.varDec = varDec;
    }

    public void PrintMe()
    {
        if (varDec != null) varDec.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Class\nVariable");

        if (varDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDec.SerialNumber);
    }

}
