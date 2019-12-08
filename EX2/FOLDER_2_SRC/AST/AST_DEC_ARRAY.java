package AST;

public class AST_DEC_ARRAY extends AST_DEC
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AST_DEC_ARRAY arrayDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_DEC_ARRAY(AST_DEC_ARRAY arrayDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== dec -> arrayDec\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.arrayDec = arrayDec;
    }

    /******************************************************/
    /* The printing message for a program AST node */
    /******************************************************/
    public void PrintMe()
    {
        /**************************************/
        /* AST NODE TYPE = AST DEC ARRAY */
        /**************************************/
        System.out.print("AST DEC ARRAY\n");

        /*************************************/
        /* RECURSIVE PRINT */
        /*************************************/
        arrayDec.PrintMe();

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "arrayDec\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, arrayDec.SerialNumber);
    }

}
