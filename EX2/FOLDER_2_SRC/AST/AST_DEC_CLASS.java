package AST;

public class AST_DEC_CLASS extends AST_DEC
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AST_DEC_CLASS classDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_DEC_CLASS(AST_DEC_CLASS classDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== dec -> classDec\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.classDec = classDec;
    }

    /******************************************************/
    /* The printing message for a program AST node */
    /******************************************************/
    public void PrintMe()
    {
        /**************************************/
        /* AST NODE TYPE = AST DEC CLASS */
        /**************************************/
        System.out.print("AST DEC CLASS\n");

        /*************************************/
        /* RECURSIVE PRINT */
        /*************************************/
        classDec.PrintMe();

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "classDec\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, classDec.SerialNumber);
    }

}
