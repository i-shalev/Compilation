package AST;

public class AST_STMT_ASSIGN extends AST_Stmt
{
	public AST_Var var;
	public AST_Exp exp;

	public AST_STMT_ASSIGN(AST_Var var, AST_Exp exp)
	{
		PrintRule("stmt", "var := exp ;");
		this.var = var;
		this.exp = exp;
	}

	public void PrintMe()
	{
		if (var != null) var.PrintMe();
		if (exp != null) exp.PrintMe();

		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				"Assign\nvar := exp");

		if (var != null) AST_Graphviz.getInstance().logEdge(SerialNumber, var.SerialNumber);
		if (exp != null) AST_Graphviz.getInstance().logEdge(SerialNumber, exp.SerialNumber);
	}
}
