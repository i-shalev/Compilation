package AST;

public class AST_STMT_FUNC extends AST_STMT
{
    /***************/
    /*  var.ID(exp1, exp2, expr3...) */
    /***************/
    public AST_VAR var;
    public AST_EXP_LIST expList;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AST_STMT_FUNC(AST_VAR var, AST_EXP_LIST expList)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (var == null && expList == null) System.out.print("====================== stmt -> ID ( ) ;\n");
        else if (var == null) System.out.print("====================== stmt -> ID ( expList ) ;\n");
        else if (expList == null) System.out.print("====================== stmt -> var . ID ( ) ;\n");
        else System.out.print("====================== stmt -> var . ID ( expList ) ;\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.var = var;
        this.expList = expList;
    }

    /*********************************************************/
    /* The printing message for an assign statement AST node */
    /*********************************************************/
    public void PrintMe()
    {
        /********************************************/
        /* AST NODE TYPE = AST FUNCTION STATEMENT */
        /********************************************/
        System.out.print("AST STMT FUNC\n");

        /***********************************/
        /* RECURSIVELY PRINT VAR + EXP ... */
        /***********************************/
        if (var != null) var.PrintMe();
        if (expList != null) expList.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "STMT\nFUNC\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (expList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expList.SerialNumber);
    }
}
