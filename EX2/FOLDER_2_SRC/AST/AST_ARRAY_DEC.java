package AST;

public class AST_ARRAY_DEC extends AST_Node
{
    public String left;
    public String right;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_ARRAY_DEC(String left, String right)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== arrayDec -> ARRAY ID = ID ( )\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.left = left;
        this.right = right;
    }

    /*************************************************/
    /* The printing message for a binop exp AST node */
    /*************************************************/
    public void PrintMe()
    {
        /*************************************/
        /* AST NODE TYPE = AST CLASS DEC */
        /*************************************/
        System.out.print("AST ARRAY DEC\n");

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("ARRAY\nDEC\n(%s)", left));
    }
}
