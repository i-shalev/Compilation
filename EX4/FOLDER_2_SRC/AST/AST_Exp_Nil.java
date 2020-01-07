package AST;

import IR.*;
import TYPES.Type;
import TYPES.Type_Nil;

public class AST_Exp_Nil extends AST_Exp
{
    public AST_Exp_Nil()
    {
        PrintRule("exp" ,"NIL");
    }

    public void PrintMe()
    {
        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "NIL");
    }

    public Type SemantMe() {
        return Type_Nil.getInstance();
    }

    public IRReg IRMe()
    {
        IRReg reg = new IRReg.TempReg();
        IR.add(new IRcommand_Move(reg, IRReg.zero));
        return reg;
    }
}
