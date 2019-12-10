package AST;

public class AST_STMT_RETURN extends AST_STMT
{
    public AST_EXP exp;

    public AST_STMT_RETURN(AST_EXP exp)
    {

        if (exp != null) PrintRule("stmt", "RETURN exp ;");
        if (exp == null) PrintRule("stmt", "RETURN ;");
        this.exp = exp;
    }

    public void PrintMe()
    {
        if (exp != null) exp.PrintMe();

        String ret = exp != null ? "RETURN exp" : "RETURN";
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                ret);

        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }
}
