package AST;

public class AST_Exp_Call extends AST_EXP
{
    public AST_VAR var;
    public String ID;
    public AST_EXP_LIST expList;

    public AST_Exp_Call(AST_VAR var, String ID, AST_EXP_LIST expList)
    {
        if (var == null && expList == null) PrintRule("exp", "ID ( )");
        if (var == null && expList != null) PrintRule("exp", "ID ( expList )");
        if (var != null && expList == null) PrintRule("exp", "var . ID ()");
        if (var != null && expList != null) PrintRule("exp", "var . ID ( expList )");

        this.var = var;
        this.ID = ID;
        this.expList = expList;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();
        if (expList != null) expList.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "Exp\nFunc");

        if (var  != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (expList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expList.SerialNumber);
    }
}