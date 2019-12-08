package AST;

public class AST_VAR_DEC_NEW_EXP extends AST_Node
{
    public String type;
    public String name;
    public AST_NEW_EXP builder;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_VAR_DEC_EXP(String type, String name, AST_NEW_EXP expr)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== varDec -> ID ID ASSIGN newExp\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
        this.builder = builder;
    }

    /*************************************************/
    /* The printing message for a binop exp AST node */
    /*************************************************/
    public void PrintMe()
    {
        /*************************************/
        /* AST NODE TYPE = AST VAR DEC NEW EXP */
        /*************************************/
        System.out.print("AST VAR DEC NEW EXP\n");

        /*************************************/
        /* RECURSIVE PRINT */
        /*************************************/
        builder.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("VAR\nDEC\n(%s %s)\n", type, name));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, builder.SerialNumber);
    }
}
