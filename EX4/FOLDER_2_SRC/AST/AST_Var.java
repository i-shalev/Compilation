package AST;

public abstract class AST_Var extends AST_Exp
{
    public int local = -1;
    public int param = -1;
    public int member = -1;
}