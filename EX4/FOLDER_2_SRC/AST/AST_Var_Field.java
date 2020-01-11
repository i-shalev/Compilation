package AST;

import IR.*;
import TYPES.*;

public class AST_Var_Field extends AST_Var
{
    public AST_Var var;
    public String fieldName;
    public int numMember;

    public AST_Var_Field(AST_Var var, String fieldName)
    {
        PrintRule("var", String.format("var . ID(%s)", fieldName));

        this.var = var;
        this.fieldName = fieldName;
    }

    public void PrintMe()
    {
        if (var != null) var.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                String.format("var.field(%s)",fieldName));

        if (var != null) AST_Graphviz.getInstance().logEdge(SerialNumber, var.SerialNumber);
    }

    public Type SemantMe() throws Exception
    {
        Type varType = var.SemantMe();
        if (!(varType instanceof Type_Class))
            throw new SemanticException("Var field statement - var is not object of class");
        Type_Class t1 = ((Type_Class) varType);
//        Type t1 = ((Type_Class) varType).getVarField(fieldName);
//        if (t1 == null){
//            throw new SemanticException("Var field statement - class doesn't have that field ");
//        }
        for (int i = 0; i < t1.members.size(); i++)
        {
            Symbol member = t1.members.get(i);
            if (fieldName.equals(member.name))
            {
                numMember = i;
                return member.type;
            }
        }
        throw new SemanticException("Var field statement - class doesn't have that field ");
    }

    public IRReg IRMe()
    {
        IRReg reg = var.IRMe();
        IR.add(new IRcommand_Lw(reg, reg, 0));                                  // dereference var
        IR.add(new IRcommand_beq(reg, IRReg.zero, "exit_invalid_dereference"));  // runtime check
        IR.add(new IRcommand_Addi(reg, reg, (numMember + 1) * 4));               // access member
        return reg;
    }
}