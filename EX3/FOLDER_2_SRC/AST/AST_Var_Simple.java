package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.Type;

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
        return SymbolTable.find(name);
    }
}
