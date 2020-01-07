package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.*;

public class AST_Class_Dec extends AST_Node
{
    public String className;
    public String father;
    public AST_Class_Field_List cFieldList;

    public AST_Class_Dec(String className, String father, AST_Class_Field_List cFieldList)
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

    public Type SemantMe() throws Exception {
        if (!SymbolTable.isGlobalScope())
            throw new SemanticException("Function declared in a non-global scope");

        Type_Class fatherType = null;

        if (father != null){
            Type t = SymbolTable.find(father);
            if (!(t instanceof Type_Class))
                throw new SemanticException("Father is not of type class");
            fatherType = (Type_Class) t;
        }

        if (SymbolTable.exists((className))) {
            throw new SemanticException("Class name already exists");
        }


        Type_Class classType = new Type_Class(fatherType, className ,null);
        SymbolTable.enter(className, classType);
        SymbolTable.beginScope(Type_Scope.CLASS);

        classType.data_members = cFieldList.SemantMe();

        SymbolTable.endScope();

        return classType;
    }
}
