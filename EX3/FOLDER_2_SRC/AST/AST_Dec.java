package AST;

import TYPES.*;

public abstract class AST_Dec extends AST_Node
{
	public AST_Var_Dec varDec;
	public AST_ARRAY_DEC arrayDec;
	public AST_CLASS_DEC classDec;
	public AST_FUNC_DEC funcDec;

	public AST_Dec(AST_Var_Dec varDec)
	{
		PrintRule("dec", "varDec");
		this.varDec = varDec;
	}

	public AST_Dec(AST_FUNC_DEC funcDec)
	{
		PrintRule("dec", "funcDec");
		this.funcDec = funcDec;
	}

	public AST_Dec(AST_CLASS_DEC classDec)
	{
		PrintRule("dec", "classDec");
		this.classDec = classDec;
	}

	public AST_Dec(AST_ARRAY_DEC arrayDec)
	{
		PrintRule("dec", "arrayDec");
		this.arrayDec = arrayDec;
	}

	public void PrintMe()
	{
		if (varDec != null) varDec.PrintMe();
		if (funcDec != null) funcDec.PrintMe();
		if (classDec != null) classDec.PrintMe();
		if (arrayDec != null) arrayDec.PrintMe();

		AST_GRAPHVIZ.getInstance().logNode(
				SerialNumber,
				"Declaration");

		if (varDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDec.SerialNumber);
		if (funcDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, funcDec.SerialNumber);
		if (classDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, classDec.SerialNumber);
		if (arrayDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, arrayDec.SerialNumber);
	}

	public Type SemantMe() throws Exception
	{
		if (varDec != null) varDec.SemantMe();
		if (funcDec != null) funcDec.SemantMe();
		if (classDec != null) classDec.SemantMe();
		if (arrayDec != null) arrayDec.SemantMe();

		return null;
	}
}