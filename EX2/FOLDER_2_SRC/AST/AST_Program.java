package AST;

public class AST_Program extends AST_Node
{

    public AST_DEC head;
    public AST_Program tail;

    public AST_Program(AST_DEC head, AST_Program tail)
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

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Program");

        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

}
