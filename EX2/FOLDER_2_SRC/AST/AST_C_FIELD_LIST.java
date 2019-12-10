package AST;

public class AST_C_FIELD_LIST extends AST_Node
{
    public AST_C_FIELD head;
    public AST_C_FIELD_LIST tail;

    public AST_C_FIELD_LIST(AST_C_FIELD head, AST_C_FIELD_LIST tail)
    {
        if (tail != null) PrintRule("cFieldList", "cField cFieldList");
        if (tail == null) PrintRule("cFieldList", "cField");

        this.head = head;
        this.tail = tail;
    }

    public void PrintMe()
    {
        if (head != null) head.PrintMe();
        if (tail != null) tail.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Class\nField\nLIST");

        if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

}
