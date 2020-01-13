package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.*;

public class AST_Stmt_Assign extends AST_Stmt
{
    public AST_Var var;
    public AST_Exp exp;

    public AST_Stmt_Assign(AST_Var var, AST_Exp exp)
    {
        PrintRule("stmt", "var := exp ;");
        this.var = var;
        this.exp = exp;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();
        if (exp != null) exp.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "Assign\nvar := exp");

        if (var != null) AST_Graphviz.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (exp != null) AST_Graphviz.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }

    public Type SemantMe() throws Exception {
        Type varType = var.SemantMe();
        Type expType = exp.SemantMe();

        if (varType == null || expType == null)
            throw new SemanticException("Variable not declared");

        if (expType instanceof Type_Nil && !(varType instanceof Type_Class || varType instanceof Type_Array))
            throw new SemanticException("Can't assign nil to non-class or array");

        if (!(expType instanceof Type_Object || expType instanceof Type_Nil))
            throw new SemanticException("Can't assign a non-object/NIL");

        if (varType instanceof Type_Int && !(expType instanceof Type_Int))
            throw new SemanticException("Can't assign non-int to int");

        if (varType instanceof Type_String && !(expType instanceof Type_String))
            throw new SemanticException("Can't assign non-string to string");

        if (varType instanceof Type_Class && !(expType instanceof Type_Class || expType instanceof Type_Nil))
            throw new SemanticException("Can't assign non-class/NIL to class");

        if (varType instanceof Type_Class && expType instanceof Type_Class &&
                !((Type_Class)expType).isInheritsFrom(varType.name))
            throw new SemanticException("Can't assign not-inherited class to a class");

        if (varType instanceof Type_Array) {
            if (!(expType instanceof Type_Nil)) { // If yes, we are finished
                if (exp instanceof AST_New_Exp && ((Type_Array) varType).elementType != expType)
                    throw new SemanticException("Initialize array with wrong type");
                if (!(exp instanceof AST_New_Exp) && varType != expType)
                    throw new SemanticException("Array assignment between different array types");
            }
        }

        return null;
    }
}
