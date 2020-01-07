package AST;

import IR.*;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_Stmt_Return extends AST_Stmt
{
	public AST_Exp exp;

	public AST_Stmt_Return(AST_Exp exp)
	{

		if (exp != null) PrintRule("stmt", "RETURN exp ;");
		if (exp == null) PrintRule("stmt", "RETURN ;");
		this.exp = exp;
	}

	public void PrintMe()
	{
		if (exp != null) exp.PrintMe();

		String ret = exp != null ? "RETURN exp" : "RETURN";
		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				ret);

		if (exp != null) AST_Graphviz.getInstance().logEdge(SerialNumber, exp.SerialNumber);
	}

	public Type SemantMe() throws Exception{
		Type_Func typeFunc = SymbolTable.findFunc();

		if (typeFunc == null)
			throw new SemanticException("Return statement - not in a function");

		if (typeFunc.returnType == Type_Void.getInstance())
		{
			if (exp != null)
				throw new SemanticException("Return statement - expected void, but returned a type");
			return typeFunc;
		}
		if(exp==null)
			throw new SemanticException("Return statement - expected non void return value");
		Type expType = exp.SemantMe();
		if (typeFunc.returnType == expType || ((typeFunc.returnType instanceof Type_Array ||
				typeFunc.returnType instanceof Type_Class) && expType == Type_Nil.getInstance()) )
			return typeFunc;

		throw new SemanticException(String.format("Return statement: expected %s, but found %s",
				typeFunc.returnType.name, expType.name));
	}

	public IRReg IRMe()
	{
		Type_Func typeFunc = SymbolTable.findFunc();
		if(exp==null)
			IR.add(new IRcommand_Move(IRReg.v0,  IRReg.zero));
		else
			IR.add(new IRcommand_Move(IRReg.v0, exp.IRMe()));
		IR.add(new IRcommand_Jump(typeFunc.name + "_epilogue"));
		return null;
	}
}
