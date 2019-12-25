package AST;

public class AST_Params_List extends AST_Node
{
    public String type;
    public String name;
    public AST_Params_List idList;

    public AST_Params_List(String type, String name, AST_Params_List idList)
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
