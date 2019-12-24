package AST;

import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_DEC_VAR extends AST_Dec
{
	/****************/
	/* DATA MEMBERS */
	/****************/
	public String type;
	public String name;
	public AST_Exp initialValue;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_DEC_VAR(String type, String name, AST_Exp initialValue)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		this.type = type;
		this.name = name;
		this.initialValue = initialValue;
	}

	/********************************************************/
	/* The printing message for a declaration list AST node */
	/********************************************************/
	public void PrintMe()
	{
		/********************************/
		/* AST NODE Type = AST DEC LIST */
		/********************************/
		if (initialValue != null) System.out.format("VAR-DEC(%s):%s := initialValue\n",name,type);
		if (initialValue == null) System.out.format("VAR-DEC(%s):%s                \n",name,type);

		/**************************************/
		/* RECURSIVELY PRINT initialValue ... */
		/**************************************/
		if (initialValue != null) initialValue.PrintMe();

		/**********************************/
		/* PRINT to AST GRAPHVIZ DOT file */
		/**********************************/
		AST_Graphviz.getInstance().logNode(
			SerialNumber,
			String.format("VAR\nDEC(%s)\n:%s",name,type));

		/****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
		if (initialValue != null) AST_Graphviz.getInstance().logEdge(SerialNumber,initialValue.SerialNumber);
			
	}

	public Type SemantMe()
	{
		Type t;
	
		/****************************/
		/* [1] Check If Type exists */
		/****************************/
		t = SymbolTable.getInstance().find(type);
		if (t == null)
		{
			System.out.format(">> ERROR [%d:%d] non existing type %s\n",2,2,type);
			System.exit(0);
		}
		
		/**************************************/
		/* [2] Check That Name does NOT exist */
		/**************************************/
		if (SymbolTable.getInstance().find(name) != null)
		{
			System.out.format(">> ERROR [%d:%d] variable %s already exists in scope\n",2,2,name);				
		}

		/***************************************************/
		/* [3] Enter the Function Type to the Symbol Table */
		/***************************************************/
		SymbolTable.getInstance().enter(name,t);

		/*********************************************************/
		/* [4] Return value is irrelevant for class declarations */
		/*********************************************************/
		return null;		
	}
	
}
