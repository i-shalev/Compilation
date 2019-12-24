package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.*;

public class AST_CLASS_DEC extends AST_Node
{
    public String className;
    public String father;
    public AST_Class_Field_List cFieldList;

    public AST_CLASS_DEC(String className, String father, AST_Class_Field_List cFieldList)
    {
        if (father != null) PrintRule("classDec", "CLASS ID EXTENDS ID { cFieldList }");
        if (father == null) PrintRule("classDec", "CLASS ID { cFieldList }");

        this.className = className;
        this.father = father;
        this.cFieldList = cFieldList;
    }

    public void PrintMe()
    {
        if (cFieldList != null) cFieldList.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                String.format("Class\nDEC\n(%s)", className));

        if (cFieldList != null) AST_Graphviz.getInstance().logEdge(SerialNumber, cFieldList.SerialNumber);
    }

    public Type SemantMe() throws Exception{
        if (!SymbolTable.isGlobalScope())
            throw new Exception("Function declared in a non-global scope");

        Type_Class fatherType = null;

        if (father != null){
            Type t = SymbolTable.find(father);
            if (!(t instanceof Type_Class))
                throw new Exception("Father is not of type class");
            fatherType = (Type_Class) t;
        }

        if (SymbolTable.exists((className))) {
            throw new Exception("Class name already exists");
        }

        Type_Class classType = new Type_Class(fatherType, className ,cFieldList.SemantMe());
        SymbolTable.enter(className, classType);

        SymbolTable.beginScope(); // Begin function scope

        // Now we need to check that the functions and variables are not declared already in predecessors

        while (fatherType != null) {
            // Iterate over its class variables and methods
            for (Type_List it = fatherType.data_members; it != null; it = it.next) {
                Type curr = SymbolTable.findInScope(it.name);

                // if curr is not null, it is Type_Var_Dec or Type_Func
                if (curr instanceof Type_Var_Dec) {  // We found a variable with the same name in an ancestor
                    throw new Exception("Shadowing is not allowed");
                }
                // TODO: verify override definition
                if (curr instanceof Type_Func) { // Need to verify it's overloading
                    if (!(curr.equals(it)))
                        throw new Exception("Overloading is not allowed");
                }
            }

            fatherType = fatherType.father; // Move up
        }

        SymbolTable.endScope(); // End

        return classType;
    }
}
