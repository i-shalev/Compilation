package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.Type;
import TYPES.Type_Class;
import TYPES.Type_Scope;

public class AST_Var_Simple extends AST_Var
{
    public String name;

    public AST_Var_Simple(String name)
    {
        PrintRule("var", String.format("ID(%s)", name));
        this.name = name;
    }

    public void PrintMe()
    {
        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                String.format("Simple\nVar\n(%s)", name));
    }

    public Type SemantMe() throws Exception{
        Type scopeVar = SymbolTable.findInScope(name);

        // First check the current scope
        if (scopeVar != null)
            return scopeVar;

        // If we are in a class, look for it in class or in ancestors of the class
        if (SymbolTable.isInScope(Type_Scope.CLASS)) {
            Type_Class classType = SymbolTable.findClass();
            Type classVar = classType.getVarField(name);
            if (classVar != null)
                return classVar;
        }

        // Still not found, look in the global scope
        Type ret = SymbolTable.find(name);
        if (ret == null)
            throw new SemanticException("Variable not found: " + name);
        return ret;
    }
}
