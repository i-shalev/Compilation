package AST;

import TYPES.*;

public class AST_Exp_Int extends AST_Exp
{
	public int value;

	public AST_Exp_Int(int value)
	{
		PrintRule("exp", String.format("INT(%d)", value));

		this.value = value;
	}

	public void PrintMe()
	{
		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				String.format("INT(%d)", value));
	}

	public Type SemantMe()
	{
		return Type_Int.getInstance();
	}
}
