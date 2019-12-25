package AST;

public class AST_PARAMS_LIST extends AST_Node
{
    public String type;
    public String name;
    public AST_PARAMS_LIST idList;

    public AST_PARAMS_LIST(String type, String name, AST_PARAMS_LIST idList)
    {
        if (idList != null) PrintRule("idList", "ID ID , idList");
        if (idList == null) PrintRule("idList", "ID ID");

        this.type = type;
        this.name = name;
        this.idList = idList;
    }

    public void PrintMe()
    {
        if (idList != null) idList.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                "ID\nLIST");

        if (idList != null) AST_Graphviz.getInstance().logEdge(SerialNumber, idList.SerialNumber);
    }

}
