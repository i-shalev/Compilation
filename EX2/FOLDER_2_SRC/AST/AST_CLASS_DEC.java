package AST;

public class AST_CLASS_DEC extends AST_Node
{
    public String className;
    public String inheritence;
    public AST_C_FIELD_LIST fields;

    public AST_CLASS_DEC(String className, String inheritence,  AST_C_FIELD_LIST fields)
    {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (inheritence != null) System.out.print("====================== classDec -> CLASS ID EXTENDS ID { cFieldList }\n");
        else System.out.print("====================== classDec -> CLASS ID { cFieldList }\n");

        this.className = className;
        this.inheritence = inheritence;
        this.fields = fields;
    }

    public void PrintMe()
    {
        System.out.print("AST CLASS DEC\n");

        if (fields != null) fields.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("CLASS\nDEC\n(%s)\n", className));

        if (fields != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, fields.SerialNumber);
    }
}
