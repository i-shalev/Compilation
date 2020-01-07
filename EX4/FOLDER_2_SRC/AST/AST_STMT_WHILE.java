package AST;

import IR.*;
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
			throw new SemanticException("While statement - mismatch condition type");
		SymbolTable.beginScope(Type_Scope.WHILE);
		body.SemantMe();
		SymbolTable.endScope();
		return null;
	}

	public IRReg IRMe()
	{
		String whileCondLabel = IR.uniqueLabel("while_cond");
		String whileEndLabel = IR.uniqueLabel("while_end");
		IR.add(new IRcommand_Label(whileCondLabel));
		IR.add(new IRcommand_Beqz(cond.IRMe(), whileEndLabel));
		body.IRMe();
		IR.add(new IRcommand_Jump(whileCondLabel));
		IR.add(new IRcommand_Label(whileEndLabel));
		return null;
	}
}