package AST;

import IR.*;
import SYMBOL_TABLE.SymbolTable;
import TYPES.*;

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

        if (!(newType instanceof Type_Class || newType instanceof Type_Array || newType == Type_Int.getInstance() || newType == Type_String.getInstance()))
            throw new SemanticException("Initialize not on an array or a class.");

        if (exp != null) { // Array init
            Type expType = exp.SemantMe();
            if (!(expType instanceof Type_Int))
                throw new SemanticException("Array size declared with a non-int");
        }

        return newType;
    }

    public IRReg IRMe()
    {
        if(exp != null)     // Array init
        {
            IRReg sizeReg = exp.IRMe();
            IR.add(new IRcommand_Move(IRReg.a0, sizeReg));              // copy array size
            IR.add(new IRcommand_Addi(IRReg.a0, IRReg.a0, 1));    // add place to remember the size - first element is size
            IR.add(new IRcommand_Sll(IRReg.a0, IRReg.a0, 4));     // convert to size in bytes - shift 4 to left is to mul 2^4
            IR.add(new IRcommand_sbrk());                               // allocate heap memory, v0 contain the result
            IR.add(new IRcommand_Sw(sizeReg, IRReg.v0, 0));      // store size as first element
        }
        // TODO: add the Class init
        else                // Class init
        {

        }
        return IRReg.v0;
    }
}
