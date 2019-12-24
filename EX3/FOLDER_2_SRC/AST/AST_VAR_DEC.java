package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.*;

public class AST_VAR_DEC extends AST_Node {

    public String type;
    public String name;
    public AST_Exp exp;
    public AST_New_Exp newExp;

    public AST_VAR_DEC(String type, String name)
    {
        PrintRule("varDec", "ID ID");
        this.type = type;
        this.name = name;
    }

    public AST_VAR_DEC(String type, String name, AST_Exp exp)
    {
        PrintRule("varDec", "ID ID := exp ;");
        this.type = type;
        this.name = name;
        this.exp = exp;
    }

    public AST_VAR_DEC(String type, String name, AST_New_Exp newExp)
    {
        PrintRule("varDec", "ID ID := newExp ;");
        this.type = type;
        this.name = name;
        this.newExp = newExp;
    }

    public void PrintMe()
    {
        if (exp != null) exp.PrintMe();
        if (newExp != null) newExp.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                String.format("Variable\nDEC\n(%s %s)", type, name));

        if (exp != null) AST_Graphviz.getInstance().logEdge(SerialNumber, exp.SerialNumber);
        if (newExp != null) AST_Graphviz.getInstance().logEdge(SerialNumber, newExp.SerialNumber);
    }

    public Type SemantMe() throws Exception {
        Type varType = SymbolTable.find(type);

        if (!(varType instanceof Type_Primitive || varType instanceof Type_Class))
            throw new Exception("Invalid variable type");

        if (SymbolTable.findInScope(name) != null){
            throw new Exception("Named already used in this scope");
        }

        SymbolTable.enter(name, varType);

        // TODO: finish this - change to camelCase only when you finish
        if (exp != null) {
            Type expType = exp.SemantMe();

            if (varType instanceof Type_Primitive && !(varType instanceof Type_Primitive)) // primitive assignment
                throw new Exception("Assigning non-primitive to a primitive");

            if (varType instanceof Type_Class && expType instanceof Type_Class) {
                if (((Type_Class) expType).isInheritsFrom(varType.name))
                    return new Type_Class_Var_Dec(varType, name);
            }
            if (varType instanceof Type_Class && expType instanceof Type_Nil)
                return new Type_Class_Var_Dec(varType, name);

            if (varType instanceof Type_Class && ((Type_Class) varType).isInheritsFrom(expType.name))
                return new Type_Class_Var_Dec(varType, name);

            // ...
        }

        if (newExp != null) {

        }

        return null;
    }
}