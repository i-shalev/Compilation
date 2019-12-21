package AST;

import TYPES.*;

public class AST_EXP_STRING extends AST_EXP
{
	public String value;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_STRING(String value)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		System.out.format("====================== exp -> STRING( %s )\n", value);
		this.value = value;
	}

	/******************************************************/
	/* The printing message for a STRING EXP AST node */
	/******************************************************/
	public void PrintMe()
	{
		/*******************************/
		/* AST NODE Type = AST STRING EXP */
		/*******************************/
		System.out.format("AST NODE STRING( %s )\n",value);

		/***************************************/
		/* PRINT Node to AST GRAPHVIZ DOT file */
		/***************************************/
		AST_GRAPHVIZ.getInstance().logNode(
			SerialNumber,
			String.format("STRING\n%s",value.replace('"','\'')));
	}
	public Type SemantMe()
	{
		return Type_String.getInstance();
	}
}
