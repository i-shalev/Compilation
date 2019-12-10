package AST;

public class AST_CLASS_DEC extends AST_Node
{
    public String className;
    public String parent;
    public AST_C_FIELD_LIST cFieldList;

    public AST_CLASS_DEC(String className, String parent, AST_C_FIELD_LIST cFieldList)
    {
        if (parent != null) PrintRule("classDec", "CLASS ID EXTENDS ID { cFieldList }");
        if (parent == null) PrintRule("classDec", "CLASS ID { cFieldList }");

        this.className = className;
        this.parent = parent;
        this.cFieldList = cFieldList;
    }

    public void PrintMe()
    {
        if (cFieldList != null) cFieldList.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("Class\nDEC\n(%s)", className));

        if (cFieldList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cFieldList.SerialNumber);
    }
}
