package AST;

public class AST_STMT_VAR_DEC extends AST_STMT
{
    public AST_VAR_DEC varDec;

    public AST_STMT_VAR_DEC(AST_VAR_DEC varDec)
    {
        PrintRule("stmt", "varDec");
        this.varDec = varDec;
    }

    public void PrintMe()
    {
        if (varDec != null) varDec.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Statement");

        if (varDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDec.SerialNumber);

    }
}
