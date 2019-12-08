package AST;

public class AST_VAR_DEC_EXP extends AST_Node
{
    public String type;
    public String name;
    public AST_EXP expr;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_VAR_DEC_EXP(String type, String name, AST_EXP expr)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (expr != null)
            System.out.print("====================== varDec -> ID ID ASSIGN exp\n");
        else
            System.out.print("====================== varDec -> ID ID\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
        this.expr = expr;
    }

    /*************************************************/
    /* The printing message for a binop exp AST node */
    /*************************************************/
    public void PrintMe()
    {
        /*************************************/
        /* AST NODE TYPE = AST VAR DEC EXP */
        /*************************************/
        System.out.print("AST VAR DEC EXP\n");

        /*************************************/
        /* RECURSIVE PRINT */
        /*************************************/
        if (expr != null)
            expr.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("VAR\nDEC\n(%s %s)\n", type, name));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (expr != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expr.SerialNumber);
    }
}
