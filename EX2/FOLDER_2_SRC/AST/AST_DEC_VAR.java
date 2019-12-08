package AST;

public class AST_DEC_VAR extends AST_DEC
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AST_VAR_DEC varDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_DEC_VAR(AST_VAR_DEC varDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== dec -> varDec\n");

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
        /* AST NODE TYPE = AST DEC VAR */
        /**************************************/
        System.out.print("AST DEC VAR\n");

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
