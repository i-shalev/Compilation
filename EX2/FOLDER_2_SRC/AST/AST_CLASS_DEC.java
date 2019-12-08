package AST;

public class AST_CLASS_DEC extends AST_Node
{
    public String className;
    public String inheritence;
    public AST_C_FIELD_LIST fields;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CLASS_DEC(String className, String inheritence,  AST_C_FIELD_LIST fields)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (id2 != null)
            System.out.print("====================== classDec -> CLASS ID EXTENDS ID { cFieldList }\n");
        else
            System.out.print("====================== classDec -> CLASS ID { cFieldList }\n");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.className = className;
        this.inheritence = inheritence;
        this.fields = fields;
    }

    /*************************************************/
    /* The printing message for a binop exp AST node */
    /*************************************************/
    public void PrintMe()
    {
        /*************************************/
        /* AST NODE TYPE = AST CLASS DEC */
        /*************************************/
        System.out.print("AST CLASS DEC\n");

        /*************************************/
        /* RECURSIVE PRINT */
        /*************************************/
        if (fields != null) fields.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("CLASS\nDEC\n(%s)", className));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (fields != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, fields.SerialNumber);
    }
}
