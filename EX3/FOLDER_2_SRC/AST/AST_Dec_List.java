package AST;

import TYPES.Type;

public class AST_Dec_List extends AST_Node
{

    public AST_Dec head;
    public AST_Dec_List tail;

    public AST_Dec_List(AST_Dec head, AST_Dec_List tail)
    {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (tail != null) PrintRule("Program", "dec Program");
        if (tail == null) PrintRule("Program", "dec");

        this.head = head;
        this.tail = tail;
    }

    public void PrintMe()
    {
        head.PrintMe();
        if (tail != null) tail.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "Program");

        AST_Graphviz.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_Graphviz.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

    public Type SemantMe() throws Exception {
        if (head != null) head.SemantMe();
        if (tail != null) tail.SemantMe();

        return null;
    }
}
