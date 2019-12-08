package AST;

public class AST_C_FIELD_VAR extends AST_C_FIELD
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AST_VAR_DEC varDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_C_FIELD_VAR(AST_VAR_DEC varDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== cField -> varDec\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.varDec = varDec;
    }

    /******************************************************/
    /* The printing message for a program AST node */
    /******************************************************/
    public void PrintMe()
    {
        /**************************************/
        /* AST NODE TYPE = AST C FIELD VAR */
        /**************************************/
        System.out.print("AST C FIELD VAR\n");

        /*************************************/
        /* RECURSIVE PRINT */
        /*************************************/
        varDec.PrintMe();

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "varDec\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDec.SerialNumber);
    }

}
