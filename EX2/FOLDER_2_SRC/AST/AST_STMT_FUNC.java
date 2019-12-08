package AST;

public class AST_STMT_FUNC extends AST_STMT
{
    /*********************************/
    /*  var.ID(exp1, exp2, expr3...) */
    /*********************************/
    public AST_VAR var;
    public String ID;
    public AST_EXP_LIST expList;

    public AST_STMT_FUNC(AST_VAR var, String ID, AST_EXP_LIST expList)
    {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (var == null && expList == null) System.out.print("====================== stmt -> ID ( ) ;\n");
        else if (var == null) System.out.print("====================== stmt -> ID ( expList ) ;\n");
        else if (expList == null) System.out.print("====================== stmt -> var . ID ( ) ;\n");
        else System.out.print("====================== stmt -> var . ID ( expList ) ;\n");

        this.var = var;
        this.ID = ID;
        this.expList = expList;
    }

    public void PrintMe()
    {
        /********************************************/
        /* AST NODE TYPE = AST FUNCTION STATEMENT */
        /********************************************/
        System.out.print("AST STMT FUNC\n");

        if (var != null) var.PrintMe();
        if (expList != null) expList.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "STMT\nFUNC\n");

        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (expList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expList.SerialNumber);
    }
}
