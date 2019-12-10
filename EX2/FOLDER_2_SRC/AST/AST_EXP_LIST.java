package AST;

public class AST_EXP_LIST extends AST_EXP
{
    public AST_EXP head;
    public AST_EXP_LIST tail;

    public AST_EXP_LIST(AST_EXP head, AST_EXP_LIST tail)
    {
        if (tail != null) PrintRule("expList", "exp expList");
        if (tail == null) PrintRule("expList", "exp");

        this.head = head;
        this.tail = tail;
    }

    public void PrintMe()
    {
        if (head != null) head.PrintMe();
        if (tail != null) tail.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Exp\nLIST\n");

        if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }
}
