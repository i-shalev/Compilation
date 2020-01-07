package AST;

import IR.IRReg;
import SYMBOL_TABLE.SymbolTable;
import TYPES.*;

public class AST_Params_List extends AST_Node
{
    public String paramTypeName;
    public String paramName;
    public AST_Params_List nextParam;

    public AST_Params_List(String paramTypeName, String paramName, AST_Params_List nextParam)
    {
        if (nextParam != null) PrintRule("idList", "ID ID , idList");
        if (nextParam == null) PrintRule("idList", "ID ID");

        this.paramTypeName = paramTypeName;
        this.paramName = paramName;
        this.nextParam = nextParam;
    }

    public void PrintMe()
    {
        if (nextParam != null) nextParam.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "Params\nLIST");

        if (nextParam != null) AST_Graphviz.getInstance().logEdge(SerialNumber, nextParam.SerialNumber);
    }

    public Type_List SemantDeclaration() throws Exception
    {
        Type paramType = SymbolTable.find(paramTypeName);
        if (!(paramType instanceof Type_Object))
            throw new SemanticException("Parameter type not defined or not a type");
        return new Type_List(paramType, nextParam != null ? nextParam.SemantDeclaration() : null);
    }

    public Type SemantMe() throws Exception {
        Type paramType = SymbolTable.find(paramTypeName);
        if (!(paramType instanceof Type_Object))
            throw new SemanticException("Parameter type not defined or not a type");
        if (SymbolTable.findInScope(paramName) != null)
            throw new SemanticException("Two parameters with the same name");
        SymbolTable.enter(paramName, paramType);
        if (nextParam != null)
            nextParam.SemantMe();

        return null;
    }
    public IRReg IRMe()
    {
        return null;
    }
}
