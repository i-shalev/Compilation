package AST;

import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_Array_Dec extends AST_Node
{
    public String left;
    public String right;

    public AST_Array_Dec(String left, String right)
    {
        PrintRule("arrayDec", "ARRAY ID = ID");

        this.left = left;
        this.right = right;
    }

    public void PrintMe()
    {
        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                String.format("Array\nDEC\n(%s = %s[])", left, right));
    }

    public Type SemantMe() throws Exception{
        if(!SymbolTable.isGlobalScope())
            throw new Exception("Declare of Array not in the global scope");

        if(SymbolTable.find(left) != null)
            throw new Exception("Declare of Array - invalid name");

        Type rightType = SymbolTable.findTypeName(right);
        if (rightType == null)
            throw new Exception("Declare of Array - invalid type");

        SymbolTable.enter(left, new Type_Array(rightType, left));

        return new Type_Array(rightType, left);
    }
}
