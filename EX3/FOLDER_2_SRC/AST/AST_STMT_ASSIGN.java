package AST;

import TYPES.*;

public class AST_STMT_ASSIGN extends AST_Stmt
{
	/***************/
	/*  var := exp */
	/***************/
	public AST_Exp_Var var;
	public AST_Exp exp;

	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_ASSIGN(AST_Exp_Var var, AST_Exp exp)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== stmt -> var ASSIGN exp SEMICOLON\n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.var = var;
		this.exp = exp;
	}

	/*********************************************************/
	/* The printing message for an assign statement AST node */
	/*********************************************************/
	public void PrintMe()
	{
		/********************************************/
		/* AST NODE TYPE = AST ASSIGNMENT STATEMENT */
		/********************************************/
		System.out.print("AST NODE ASSIGN STMT\n");

		/***********************************/
		/* RECURSIVELY PRINT VAR + EXP ... */
		/***********************************/
		if (var != null) var.PrintMe();
		if (exp != null) exp.PrintMe();

		/***************************************/
		/* PRINT Node to AST GRAPHVIZ DOT file */
		/***************************************/
		AST_Graphviz.getInstance().logNode(
			SerialNumber,
			"ASSIGN\nleft := right\n");
		
		/****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
		AST_Graphviz.getInstance().logEdge(SerialNumber,var.SerialNumber);
		AST_Graphviz.getInstance().logEdge(SerialNumber,exp.SerialNumber);
	}
	public Type SemantMe()
	{
		Type t1 = null;
		Type t2 = null;

		// ### example
		// var: "x"
		// expression: y + 7
		if (var != null) t1 = var.SemantMe();
		if (exp != null) t2 = exp.SemantMe();
		
		if (t1 != t2)
		{
			System.out.format(">> ERROR [%d:%d] type mismatch for var := exp\n",6,6);				
		}
		return null;
	}
}
