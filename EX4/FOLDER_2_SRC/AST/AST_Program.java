package AST;

import IR.IRReg;
import TYPES.Type;

public class AST_Program extends AST_Node
{

    public AST_Dec head;
    public AST_Program tail;

    public AST_Program(AST_Dec head, AST_Program tail)
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

    public IRReg IRMe() {
        if (head != null)
            head.IRMe();
        if (tail != null)
            tail.IRMe();
        return null;
    }
}
