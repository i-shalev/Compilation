package AST;

import IR.*;
import SYMBOL_TABLE.SymbolTable;
import TYPES.Type;

// TODO: check if necessary
public class AST_Exp_Var extends AST_Exp
{
	public AST_Var var;

	public AST_Exp_Var(AST_Var var)
	{
		PrintRule("exp", "var");

		this.var = var;
	}

	public void PrintMe()
	{
		if (var != null) var.PrintMe();

		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				"Exp\nVar");

		AST_Graphviz.getInstance().logEdge(SerialNumber, var.SerialNumber);

	}

	public Type SemantMe() throws Exception{
		return var.SemantMe();
	}

	public IRReg IRMe()
	{
		IRReg reg = var.IRMe();
		IR.add(new IRcommand_Lw(reg, reg,0)); // dereference
		return reg;
	}
}
