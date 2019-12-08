package AST;

public class AST_ID_LIST extends AST_Node
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public String type;
    public String name;
    public AST_ID_LIST idList;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_ID_LIST(String type, String name, AST_ID_LIST idList)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (idList != null) System.out.print("====================== idList -> ID ID COMMA idList\n");
        if (idList == null) System.out.print("====================== idList -> ID ID\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
        this.idList = idList;
    }

    /******************************************************/
    /* The printing message for a statement list AST node */
    /******************************************************/
    public void PrintMe()
    {
        /**************************************/
        /* AST NODE TYPE = AST ID LIST */
        /**************************************/
        System.out.print("AST ID LIST\n");

        /*************************************/
        /* RECURSIVELY PRINT IDLIST ... */
        /*************************************/
        if (idList != null) idList.PrintMe();

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "ID\nLIST\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (idList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, idList.SerialNumber);
    }

}
