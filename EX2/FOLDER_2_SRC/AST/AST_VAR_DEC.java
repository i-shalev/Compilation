package AST;

public class AST_VAR_DEC extends AST_Node {
    public String type;
    public String name;
    public AST_EXP expr;
    public AST_NEW_EXP builder;

    /******************/
    /* CONSTRUCTOR(S) */

    /******************/
    public AST_VAR_DEC(String type, String name)
    {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("====================== varDec -> ID ID\n");

        this.type = type;
        this.name = name;
    }

    public AST_VAR_DEC(String type, String name, AST_EXP expr)
    {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("====================== varDec -> ID ID ASSIGN exp\n");

        this.type = type;
        this.name = name;
        this.expr = expr;
    }

    public AST_VAR_DEC(String type, String name, AST_NEW_EXP expr)
    {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("====================== varDec -> ID ID ASSIGN newExp\n");

        this.type = type;
        this.name = name;
        this.builder = builder;
    }

    public void PrintMe()
    {
        System.out.print("AST NODE VAR DEC\n");

        if (expr != null) expr.PrintMe();
        if (builder != null) expr.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("VAR\nDEC\n(%s %s)\n", type, name));

        if (expr != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expr.SerialNumber);
        if (builder != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, builder.SerialNumber);
    }
}
