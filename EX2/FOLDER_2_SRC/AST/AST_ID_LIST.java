package AST;

public class AST_ID_LIST extends AST_Node
{
    public String type;
    public String name;
    public AST_ID_LIST idList;

    public AST_ID_LIST(String type, String name, AST_ID_LIST idList)
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

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "ID\nLIST");

        if (idList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, idList.SerialNumber);
    }

}
