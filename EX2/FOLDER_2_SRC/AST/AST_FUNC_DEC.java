package AST;

public class AST_FUNC_DEC extends AST_Node
{
    public String retType
    public String name;
    public AST_ID_LIST variables;
    public AST_STMT_LIST statements;


    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_FUNC_DEC(String retType, String name, AST_ID_LIST variables, AST_STMT_LIST statements)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (variables != null)
            System.out.print("====================== funcDec -> ID ID ( idList ) { stmtList }\n");
        else
            System.out.print("====================== funcDec -> ID ID ( ) { stmtList }\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.retType = retType;
        this.name = name;
        this.variables = variables;
        this.statements = statements;
    }

    /*************************************************/
    /* The printing message for a binop exp AST node */
    /*************************************************/
    public void PrintMe()
    {
        /*************************************/
        /* AST NODE TYPE = AST FUNC DEC */
        /*************************************/
        System.out.print("AST FUNC DEC\n");

        /*************************************/
        /* RECURSIVE PRINT */
        /*************************************/
        if (variables != null)
            variables.PrintMe();
        statements.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("FUNC\nDEC\n(%s %s)", retType, name));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (variables != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, variables.SerialNumber);
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, statements.SerialNumber);
    }
}
