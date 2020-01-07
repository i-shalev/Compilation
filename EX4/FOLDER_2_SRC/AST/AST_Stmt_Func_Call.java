package AST;

import IR.IRReg;
import TYPES.Type;

public class AST_Stmt_Func_Call extends AST_Stmt
{
    public AST_Exp_Func_Call funcCall;

    public AST_Stmt_Func_Call(AST_Exp_Func_Call funcCall)
    {
        PrintRule("stmt", "expCall");

        this.funcCall = funcCall;
    }

    public void PrintMe()
    {
        if (funcCall != null) funcCall.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "Statement\nFunction Call");

        if (funcCall != null) AST_Graphviz.getInstance().logEdge(SerialNumber, funcCall.SerialNumber);
    }

    public Type SemantMe() throws Exception {
        return funcCall.SemantMe();
    }
    public IRReg IRMe()
    {
        return funcCall.IRMe();
    }
}
