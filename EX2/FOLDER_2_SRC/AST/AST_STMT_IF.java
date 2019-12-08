package AST;

public class AST_STMT_IF extends AST_STMT
{
    /**************/
    /*  if (condition) { statements } */
    /***************/
    public AST_EXP cond;
    public AST_STMT_LIST body;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AST_STMT_IF(AST_EXP cond, AST_STMT_LIST body)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== stmt -> IF ( exp ) { stmtList } ;\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.cond = cond;
        this.body = body;
    }

    /*********************************************************/
    /* The printing message for an assign statement AST node */
    /*********************************************************/
    public void PrintMe()
    {
        /********************************************/
        /* AST NODE TYPE = AST IF STATEMENT */
        /********************************************/
        System.out.print("AST STMT IF\n");


        /***********************************/
        /* RECURSIVELY PRINT COND + BODY ... */
        /***********************************/
        if (cond != null) cond.PrintMe();
        if (body != null) body.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "IF\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cond.SerialNumber);
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }
}