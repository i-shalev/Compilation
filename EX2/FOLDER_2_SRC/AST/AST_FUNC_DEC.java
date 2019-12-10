package AST;

public class AST_FUNC_DEC extends AST_Node
{
    public String retType;
    public String name;
    public AST_ID_LIST variables;
    public AST_STMT_LIST statements;

    public AST_FUNC_DEC(String retType, String name, AST_ID_LIST variables, AST_STMT_LIST statements)
    {
        if (variables != null) PrintRule("funcDec", "ID ID ( idList ) { stmtList }");
        if (variables == null) PrintRule("funcDec", "ID ID ( ) { stmtList }");

        this.retType = retType;
        this.name = name;
        this.variables = variables;
        this.statements = statements;
    }

    public void PrintMe()
    {
        if (variables != null) variables.PrintMe();
        if (statements != null) statements.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("Function\nDEC\n(%s %s)", retType, name));

        if (variables != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, variables.SerialNumber);
        if (statements != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, statements.SerialNumber);
    }
}
