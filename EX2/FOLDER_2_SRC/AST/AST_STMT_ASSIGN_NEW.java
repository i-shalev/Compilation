package AST;

public class AST_STMT_ASSIGN_NEW extends AST_STMT
{
    /***************/
    /*  var := newExp */
    /***************/
    public AST_VAR var;
    public AST_NEW_EXP newExp;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AST_STMT_ASSIGN_NEW(AST_VAR var, AST_NEW_EXP newExp)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== stmt -> var = newExp ;\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.var = var;
        this.newExp = newExp;
    }

    /*********************************************************/
    /* The printing message for an assign statement AST node */
    /*********************************************************/
    public void PrintMe()
    {
        /********************************************/
        /* AST NODE TYPE = AST ASSIGNMENT STATEMENT */
        /********************************************/
        System.out.print("AST STMT ASSIGN NEW\n");

        /***********************************/
        /* RECURSIVELY PRINT VAR + NEWEXP ... */
        /***********************************/
        if (var != null) var.PrintMe();
        if (newExp != null) newExp.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "ASSIGN\nleft := newExp\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, newExp.SerialNumber);
    }
}
