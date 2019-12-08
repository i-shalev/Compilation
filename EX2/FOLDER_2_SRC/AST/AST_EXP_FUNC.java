package AST;

public class AST_EXP_FUNC extends AST_EXP
{
    public AST_VAR var;
    public String ID;
    public AST_EXP_LIST expList;



    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_FUNC(AST_VAR var, String ID, AST_EXP_LIST expList)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/

        if (var == null && expList == null) System.out.print("====================== exp -> ID ( )\n");
        else if (var == null) System.out.print("====================== exp -> ID ( expList )\n");
        else if (expList == null) System.out.print("====================== exp -> var . ID ( )\n");
        else System.out.print("====================== exp -> var . ID ( expList )\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.var = var;
        this.ID = ID;
        this.expList = expList;
    }

    /*************************************************/
    /* The printing message for a binop exp AST node */
    /*************************************************/
    public void PrintMe()
    {
        /*************************************/
        /* AST NODE TYPE = AST EXP FUNC */
        /*************************************/
        System.out.print("AST EXP FUNC\n");

        /**************************************/
        /* RECURSIVELY PRINT var + expList ... */
        /**************************************/
        if (var != null) var.PrintMe();
        if (expList != null) expList.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "EXP\nFUNC\n");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (var  != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (expList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expList.SerialNumber);
    }
}