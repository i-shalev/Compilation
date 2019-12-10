package AST;

public class AST_C_FIELD_FUNC extends AST_C_FIELD
{
    public AST_FUNC_DEC funcDec;

    public AST_C_FIELD_FUNC(AST_FUNC_DEC funcDec)
    {
        PrintRule("cField", "funcDec");
        this.funcDec = funcDec;
    }

    public void PrintMe()
    {
        if (funcDec != null) funcDec.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Class\nFunction");

        if (funcDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, funcDec.SerialNumber);
    }

}