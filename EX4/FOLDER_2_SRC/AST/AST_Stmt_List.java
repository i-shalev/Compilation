package AST;

import IR.IRReg;
import TYPES.Type;

public class AST_Stmt_List extends AST_Node
{
	public AST_Stmt head;
	public AST_Stmt_List tail;

	public AST_Stmt_List(AST_Stmt head, AST_Stmt_List tail)
	{
		if (tail != null) PrintRule("stmtList", "stmt stmtList");
		if (tail == null) PrintRule("stmtList", "stmt");

		this.head = head;
		this.tail = tail;
	}

	public void PrintMe()
	{
		if (head != null) head.PrintMe();
		if (tail != null) tail.PrintMe();

		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				"Statement\nLIST\n");

		if (head != null) AST_Graphviz.getInstance().logEdge(SerialNumber, head.SerialNumber);
		if (tail != null) AST_Graphviz.getInstance().logEdge(SerialNumber, tail.SerialNumber);
	}

	public Type SemantMe() throws Exception{
		if (head != null){
			head.SemantMe();
   		}
		if (tail != null)
			tail.SemantMe();
		return null;
	}
	public IRReg toIR()
	{
		if (head != null){
			head.IRMe();
		}
		if (tail != null)
			tail.IRMe();
		return null;
	}

}
