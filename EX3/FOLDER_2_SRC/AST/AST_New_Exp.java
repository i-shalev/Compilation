package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.Type;
import TYPES.Type_Array;
import TYPES.Type_Class;
import TYPES.Type_Int;

public class AST_New_Exp extends AST_Exp
{
    public String typeName;
    public AST_Exp exp;

    public AST_New_Exp(String typeName, AST_Exp exp)
    {

        if (exp != null) PrintRule("newExp", String.format("NEW ID(%s) [ exp ]", typeName));
        if (exp == null) PrintRule("newExp", String.format("NEW ID(%s)", typeName));

        this.typeName = typeName;
        this.exp = exp;
    }

    public void PrintMe()
    {
        if (exp != null) exp.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "NEW\nExp\n");

        if (exp != null) AST_Graphviz.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }

    public Type SemantMe() throws Exception {
        Type newType = SymbolTable.find(typeName);

        if (!(newType instanceof Type_Class || newType instanceof Type_Array))
            throw new SemanticException("Initialize not on an array or a class");

        if (exp != null) { // Array init
            Type expType = exp.SemantMe();
            if (!(expType instanceof Type_Int))
                throw new SemanticException("Array size declared with a non-int");
        }

        return newType;
    }
}
