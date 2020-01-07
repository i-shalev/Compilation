package AST;

import IR.IRReg;
import TYPES.*;
import SYMBOL_TABLE.*;

import javax.lang.model.type.PrimitiveType;

public class AST_Array_Dec extends AST_Node
{
    public String arrayName;
    public String typeName;

    public AST_Array_Dec(String arrayName, String typeName)
    {
        PrintRule("arrayDec", "ARRAY ID = ID [ ]");

        this.arrayName = arrayName;
        this.typeName = typeName;
    }

    public void PrintMe()
    {
        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                String.format("Array\nDEC\n(%s = %s[])", arrayName, typeName));
    }

    public Type SemantMe() throws Exception{
        if(!SymbolTable.isGlobalScope())
            throw new SemanticException("Array declaration not in the global scope");

        if(SymbolTable.find(arrayName) != null)
            throw new SemanticException("Array declaration - invalid name");

        Type arrayType = SymbolTable.find(typeName);
        if (!(arrayType instanceof Type_Object))
            throw new SemanticException("Array declaration - invalid type");

        SymbolTable.enter(arrayName, new Type_Array(arrayType, arrayName));

        return new Type_Array(arrayType, arrayName);
    }

    public IRReg IRMe()
    {
        return null;  // nothing to do here
    }
}
