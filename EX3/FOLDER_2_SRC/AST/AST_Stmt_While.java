package AST;

import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_Stmt_While extends AST_Stmt
{
	public AST_Exp cond;
	public AST_Stmt_List body;

	public AST_Stmt_While(AST_Exp cond, AST_Stmt_List body)
	{
		PrintRule("stmt", "WHILE ( exp ) { stmtList }");
		this.cond = cond;
		this.body = body;
	}

	public void PrintMe()
	{
		if (cond != null) cond.PrintMe();
		if (body != null) body.PrintMe();

		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				"WHILE\n");

		AST_Graphviz.getInstance().logEdge(SerialNumber, cond.SerialNumber);
		AST_Graphviz.getInstance().logEdge(SerialNumber, body.SerialNumber);
	}

	public Type SemantMe() throws Exception{
		if(cond.SemantMe() != Type_Int.getInstance())
			throw new Exception("While statement - mismatch condition type");
		SymbolTable.beginScope();
		body.SemantMe();
		SymbolTable.endScope();
		return null;
	}
}