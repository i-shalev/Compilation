package AST;

import TYPES.Type_List;

public class AST_Class_Field_List extends AST_Node
{
    public AST_Class_Field head;
    public AST_Class_Field_List tail;

    public AST_Class_Field_List(AST_Class_Field head, AST_Class_Field_List tail)
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

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "Class\nField\nLIST");

        if (head != null) AST_Graphviz.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_Graphviz.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

    public Type_List SemantMe() throws Exception{
            return new Type_List(head.SemantMe(), tail != null ? tail.SemantMe() : null);
        }
}
