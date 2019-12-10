package AST;

public class AST_STMT_IF extends AST_STMT
{
    public AST_EXP cond;
    public AST_STMT_LIST body;

    public AST_STMT_IF(AST_EXP cond, AST_STMT_LIST body)
    {
        PrintRule("stmt", "IF ( exp ) { stmtList }");

        this.cond = cond;
        this.body = body;
    }

    public void PrintMe()
    {
        if (cond != null) cond.PrintMe();
        if (body != null) body.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "IF\n");

        if (cond != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cond.SerialNumber);
        if (body != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }
}