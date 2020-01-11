package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.Symbol;
import TYPES.Type;
import TYPES.Type_Class;
import TYPES.Type_Scope;

import java.util.List;

public class AST_Var_Simple extends AST_Var
{
    public String name;
    public int local = -1;
    public int param = -1;
    public int member = -1;


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

        if (SymbolTable.findFunc() != null) {
            for (int i = SymbolTable.findFunc().currMaxLocals - 1; i >= 0; i--)
            {
                if (name.equals(SymbolTable.findFunc().locals.get(i).name))
                {
                    local = i;
                    break;
                }
            }
            List<Symbol> funcParams = SymbolTable.findFunc().params2;
            for (int i = 0; i < funcParams.size(); i++)
            {
                if (name.equals(funcParams.get(i).name))
                {
                    param = i;
                }
            }
        }

        if(SymbolTable.findClass() != null)
        {
            List<Symbol> classMembers = SymbolTable.findClass().members;
            for (int i = 0; i < classMembers.size(); i++)
            {
                if (name.equals(classMembers.get(i).name))
                {
                    member = i;
                }
            }
        }

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
