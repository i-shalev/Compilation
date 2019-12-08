package AST;

public class AST_EXP_NIL extends AST_EXP
{

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_NIL()
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== exp -> NIL\n");
    }

    /************************************************/
    /* The printing message for an NIL EXP AST node */
    /************************************************/
    public void PrintMe()
    {
        /*******************************/
        /* AST NODE TYPE = AST EXP NIL */
        /*******************************/
        System.out.print("AST EXP NIL\n");

        /*********************************/
        /* Print to AST GRAPHIZ DOT file */
        /*********************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "NIL\n");
    }
}
