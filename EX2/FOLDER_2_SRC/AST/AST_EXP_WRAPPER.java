package AST;

public class AST_EXP_WRAPPER extends AST_EXP
{
    public AST_EXP expr;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_WRAPPER(AST_EXP expr)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== exp -> ( exp )\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.expr = expr;
    }

    /***********************************************/
    /* The default message for an exp wrapper AST node */
    /***********************************************/
    public void PrintMe()
    {
        /************************************/
        /* AST NODE TYPE = AST EXP WRAPPER */
        /************************************/
        System.out.print("AST EXP WRAPPER\n");

        /*****************************/
        /* RECURSIVELY PRINT var ... */
        /*****************************/
        if (expr != null) expr.PrintMe();

        /*********************************/
        /* Print to AST GRAPHIZ DOT file */
        /*********************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "EXP\nWRAPPER\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expr.SerialNumber);

    }
}
