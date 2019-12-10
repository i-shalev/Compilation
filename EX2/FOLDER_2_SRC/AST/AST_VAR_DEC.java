package AST;

public class AST_VAR_DEC extends AST_Node {

    public String type;
    public String name;
    public AST_EXP exp;
    public AST_NEW_EXP newExp;

    public AST_VAR_DEC(String type, String name)
    {
        PrintRule("varDec", "ID ID");
        this.type = type;
        this.name = name;
    }

    public AST_VAR_DEC(String type, String name, AST_EXP exp)
    {
        PrintRule("varDec", "ID ID := exp ;");
        this.type = type;
        this.name = name;
        this.exp = exp;
    }

    public AST_VAR_DEC(String type, String name, AST_NEW_EXP newExp)
    {
        PrintRule("varDec", "ID ID := newExp ;");
        this.type = type;
        this.name = name;
        this.newExp = newExp;
    }

    public void PrintMe()
    {
        if (exp != null) exp.PrintMe();
        if (newExp != null) newExp.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("Variable\nDEC\n(%s %s)", type, name));

        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
        if (newExp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, newExp.SerialNumber);
    }
}
