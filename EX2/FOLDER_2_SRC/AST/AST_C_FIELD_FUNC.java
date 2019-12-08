package AST;

public class AST_C_FIELD_FUNC extends AST_C_FIELD
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AST_FUNC_DEC funcDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_C_FIELD_FUNC(AST_FUNC_DEC funcDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== cField -> funcDec\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.funcDec = funcDec;
    }

    /******************************************************/
    /* The printing message for a program AST node */
    /******************************************************/
    public void PrintMe()
    {
        /**************************************/
        /* AST NODE TYPE = AST C FIELD FUNC */
        /**************************************/
        System.out.print("AST C FIELD FUNC\n");

        /*************************************/
        /* RECURSIVE PRINT */
        /*************************************/
        funcDec.PrintMe();

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "funcDec\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, funcDec.SerialNumber);
    }

}