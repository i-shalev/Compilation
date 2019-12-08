package AST;

public class AST_STMT_VAR_DEC extends AST_EXP
{
    public AST_VAR_DEC varDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_STMT_VAR_DEC(AST_VAR_DEC varDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== stmt -> varDec\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.varDec = varDec;
    }

    /***********************************************/
    /* The default message for an exp var AST node */
    /***********************************************/
    public void PrintMe()
    {
        /************************************/
        /* AST NODE TYPE = AST STMT VAR DEC */
        /************************************/
        System.out.print("AST STMT VAR DEC\n");

        /*****************************/
        /* RECURSIVELY PRINT varDec ... */
        /*****************************/
        varDec.PrintMe();

        /*********************************/
        /* Print to AST GRAPHIZ DOT file */
        /*********************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "STMT\nDEC\nVAR\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDec.SerialNumber);

    }
}
