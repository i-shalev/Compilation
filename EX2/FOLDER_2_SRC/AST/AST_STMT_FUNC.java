package AST;

public class AST_STMT_FUNC extends AST_STMT
{
    public AST_VAR var;
    public String ID;
    public AST_EXP_LIST expList;

    public AST_STMT_FUNC(AST_VAR var, String ID, AST_EXP_LIST expList)
    {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (var == null && expList == null) PrintRule("stmt", "ID ( ) ;");
        if (var == null && expList != null) PrintRule("stmt", "ID ( expList ) ;");
        if (var != null && expList == null) PrintRule("stmt", "var . ID ( ) ;");
        if (var != null && expList != null) PrintRule("stmt", "var . ID ( expList ) ;");

        this.var = var;
        this.ID = ID;
        this.expList = expList;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();
        if (expList != null) expList.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Statement\nFunction Call");

        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (expList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expList.SerialNumber);
    }
}
