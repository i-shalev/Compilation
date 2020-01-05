package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.Type;
import TYPES.Type_Int;
import TYPES.Type_Scope;

public class AST_Stmt_If extends AST_Stmt
{
	public AST_Exp cond;
	public AST_Stmt_List body;

	public AST_Stmt_If(AST_Exp cond, AST_Stmt_List body)
	{
		PrintRule("stmt", "IF ( exp ) { stmtList }");

		this.cond = cond;
		this.body = body;
	}

	public void PrintMe()
	{
		if (cond != null) cond.PrintMe();
		if (body != null) body.PrintMe();

		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				"IF\n");

		if (cond != null) AST_Graphviz.getInstance().logEdge(SerialNumber, cond.SerialNumber);
		if (body != null) AST_Graphviz.getInstance().logEdge(SerialNumber, body.SerialNumber);
	}

	public Type SemantMe() throws Exception{
		if(cond.SemantMe() != Type_Int.getInstance())
			throw new SemanticException("While statement - mismatch condition type");
		SymbolTable.beginScope(Type_Scope.IF);
		body.SemantMe();
		SymbolTable.endScope();
		return null;
	}
}