package AST;

public class AST_STMT_LIST extends AST_Node
{
    public AST_STMT head;
    public AST_STMT_LIST tail;

    public AST_STMT_LIST(AST_STMT head, AST_STMT_LIST tail)
    {
        if (tail != null) PrintRule("stmtList", "stmt stmtList");
        if (tail == null) PrintRule("stmtList", "stmt");

        this.head = head;
        this.tail = tail;
    }

    public void PrintMe()
    {
        if (head != null) head.PrintMe();
        if (tail != null) tail.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Statement\nLIST\n");

        if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

}
