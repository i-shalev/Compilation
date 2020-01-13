package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.*;

public class AST_Var_Dec extends AST_Class_Field {

    public String typeName;
    public String name;
    public AST_Exp exp;
//    public AST_New_Exp newExp;

    public AST_Var_Dec(String typeName, String name)
    {
        PrintRule("varDec", "ID ID");
        this.typeName = typeName;
        this.name = name;
    }

    public AST_Var_Dec(String typeName, String name, AST_Exp exp)
    {
        PrintRule("varDec", "ID ID := exp ;");
        this.typeName = typeName;
        this.name = name;
        this.exp = exp;
    }
//
//    public AST_VAR_DEC(String typeName, String name, AST_New_Exp newExp)
//    {
//        PrintRule("varDec", "ID ID := newExp ;");
//        this.typeName = typeName;
//        this.name = name;
//        this.newExp = newExp;
//    }

    public void PrintMe()
    {
        if (exp != null) exp.PrintMe();
//        if (newExp != null) newExp.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                String.format("Variable\nDEC\n(%s %s)", typeName, name));

        if (exp != null) AST_Graphviz.getInstance().logEdge(SerialNumber, exp.SerialNumber);
//        if (newExp != null) AST_Graphviz.getInstance().logEdge(SerialNumber, newExp.SerialNumber);
    }

    public Type SemantMe() throws Exception {
        Type varType = SymbolTable.find(typeName);

        if (!(varType instanceof Type_Object))
            throw new SemanticException("Invalid variable type");

        if (SymbolTable.findInScope(name) != null)
            throw new SemanticException("Name already used in this scope");

        if (SymbolTable.isDirectlyInScope(Type_Scope.CLASS)) { // class member
            if (exp != null && !(exp instanceof AST_Exp_Int || exp instanceof AST_Exp_String
                    || exp instanceof AST_Exp_Nil))
                throw new SemanticException("Must initialize with simple expression in a class");

            // Now check that we are not shadowing

            Type_Class father = SymbolTable.findClass().father;
            if (father != null)
                if (father.getVarField(name) != null || father.getFuncField(name) != null)
                    throw new SemanticException("Variable already defined in ancestor");
                    
            
        }

        // Ended class checks, now we check the assignment is valid, if there is one

        if (exp != null) {
            Type expType = exp.SemantMe();

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
        }

        SymbolTable.enter(name, varType);
        if (SymbolTable.isDirectlyInScope(Type_Scope.CLASS)){  
          Type_Class c1 = SymbolTable.findClass();
          c1.data_members = Type_List.add(new Type_Var_Dec(varType,name),c1.data_members);
        }
        
        return new Type_Var_Dec(varType, name);
    }
}