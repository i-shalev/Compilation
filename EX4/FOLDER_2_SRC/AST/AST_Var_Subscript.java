package AST;

import IR.*;
import TYPES.*;

public class AST_Var_Subscript extends AST_Var
{
    public AST_Var var;
    public AST_Exp subscript;

    public AST_Var_Subscript(AST_Var var, AST_Exp subscript)
    {
        PrintRule("var", "var [ exp ]");
        this.var = var;
        this.subscript = subscript;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();
        if (subscript != null) subscript.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "var[exp]");

        if (var != null) AST_Graphviz.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (subscript != null) AST_Graphviz.getInstance().logEdge(SerialNumber, subscript.SerialNumber);
    }

    public Type SemantMe() throws Exception{
        if (subscript.SemantMe() != Type_Int.getInstance())
            throw new SemanticException("Var subscript - index is not an int");
        Type arrType = var.SemantMe();
        if (!(arrType instanceof Type_Array))
            throw new SemanticException("Var subscript - var is not an array");
        return ((Type_Array)arrType).elementType;
    }

    public IRReg IRMe()
    {
        IRReg indexReg = subscript.IRMe();                      // get the address of the index
        IRReg baseReg = var.IRMe();                             // get the address of the beggining of the array
        IR.add(new IRcommand_Lw(baseReg, baseReg, 0));  // dereference array

        // pointer dereference
        IR.add(new IRcommand_beq(baseReg, IRReg.zero, "exit_invalid_dereference"));  // runtime check

        // boundary check
        IRReg sizeReg = new IRReg.TempReg();
        IR.add(new IRcommand_Lw(sizeReg, baseReg, 0));                          // first element is size
        IR.add(new IRcommand_bgt(indexReg, sizeReg, "exit_access_violation"));   // check if the index is bigger the size
        IR.add(new IRcommand_beq(sizeReg, indexReg, "exit_access_violation"));   // check if the index is bigger the size

        // calculate element address
        IR.add(new IRcommand_Addi(indexReg, indexReg, 1));                      // first element is size
        IR.add(new IRcommand_Sll(indexReg, indexReg, 4));  // convert to index in bytes
        IR.add(new IRcommand_Add(baseReg, baseReg, indexReg));  // calculate address of element
        return baseReg;
    }
}
