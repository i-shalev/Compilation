package AST;

import IR.IRReg;
import TYPES.Type;

public class AST_Stmt_Var_Dec extends AST_Stmt
{
    public AST_Var_Dec varDec;

    public AST_Stmt_Var_Dec(AST_Var_Dec varDec)
    {
        PrintRule("stmt", "varDec");
        this.varDec = varDec;
    }

    public void PrintMe()
    {
        if (varDec != null) varDec.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "Statement");

        if (varDec != null) AST_Graphviz.getInstance().logEdge(SerialNumber, varDec.SerialNumber);
    }

    public Type SemantMe() throws Exception {
        return varDec.SemantMe();
    }
    public IRReg IRMe()
    {
        return varDec.IRMe();
    }
}