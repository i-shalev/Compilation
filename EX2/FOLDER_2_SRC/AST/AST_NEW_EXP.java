package AST;

public class AST_NEW_EXP extends AST_Node
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public String type;
    public AST_EXP exp;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_NEW_EXP(String type, AST_EXP exp)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/

        if (exp != null) System.out.format("====================== newExp -> NEW ID(%s) [ exp ]\n", type);
        if (exp == null) System.out.format("====================== newExp -> NEW ID(%s)\n", type);

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.type = type;
        this.exp = exp;
    }

    /******************************************************/
    /* The printing message for a program AST node */
    /******************************************************/
    public void PrintMe()
    {
        /**************************************/
        /* AST NODE TYPE = AST NEW EXP */
        /**************************************/
        System.out.print("AST NEW EXP\n");

        /*************************************/
        /* RECURSIVELY PRINT EXP */
        /*************************************/
        if (exp != null) exp.PrintMe();

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "NEW\nEXP\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }

}
