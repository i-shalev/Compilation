package AST;

import TYPES.Type;
import TYPES.Type_Void;

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
        return Type_Void.getInstance();
    }
}
