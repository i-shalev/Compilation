package AST;

import TYPES.Type_List;

public class AST_Exp_List extends AST_Node
{
	public AST_Exp head;
	public AST_Exp_List tail;

	public AST_Exp_List(AST_Exp head, AST_Exp_List tail)
	{
		if (tail != null) PrintRule("expList", "exp expList");
		if (tail == null) PrintRule("expList", "exp");

		this.head = head;
		this.tail = tail;
	}

	public void PrintMe()
	{
		if (head != null) head.PrintMe();
		if (tail != null) tail.PrintMe();

		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				"Exp\nLIST\n");

		if (head != null) AST_Graphviz.getInstance().logEdge(SerialNumber, head.SerialNumber);
		if (tail != null) AST_Graphviz.getInstance().logEdge(SerialNumber, tail.SerialNumber);
	}

	public Type_List SemantMe() throws Exception {
		return new Type_List(head.SemantMe(), tail != null ? tail.SemantMe() : null);
	}
}
