package AST;

import IR.*;
import TYPES.*;

public class AST_Exp_String extends AST_Exp
{
	public String str;
	public String label;

	public AST_Exp_String(String str)
	{
		PrintRule("exp", String.format("STRING(%s)", str));

		this.str = str;
	}

	public void PrintMe()
	{
		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				String.format("STRING(%s)", str));
	}

	public Type SemantMe()
	{
		label = IR.uniqueLabel("string_literal");
		return Type_String.getInstance();
	}

	public IRReg IRMe()
	{
		IRReg reg = new IRReg.TempReg();
		IR.add(new IRcommand_String_Literal(str, label));
		IR.add(new IRcommand_La(reg, label));
		return reg;
	}
}
