package AST;

public class AST_STMT_WHILE extends AST_STMT
{
    public AST_EXP cond;
    public AST_STMT_LIST body;

    public AST_STMT_WHILE(AST_EXP cond, AST_STMT_LIST body)
    {
        PrintRule("stmt", "WHILE ( exp ) { stmtList }");
        this.cond = cond;
        this.body = body;
    }

    public void PrintMe()
    {
        if (cond != null) cond.PrintMe();
        if (body != null) body.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "WHILE\n");

        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cond.SerialNumber);
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }
}