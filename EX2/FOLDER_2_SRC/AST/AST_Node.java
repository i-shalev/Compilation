package AST;

public abstract class AST_Node
{
	public static boolean printDerivationRule = true;
	/*******************************************/
	/* The serial number is for debug purposes */
	/* In particular, it can help in creating  */
	/* a graphviz dot format of the AST ...    */
	/*******************************************/
	public int SerialNumber;

	public AST_Node(){
		SerialNumber = AST_Node_Serial_Number.getFresh();
	}

	/***********************************************/
	/* The default message for an unknown AST node */
	/***********************************************/
	public void PrintMe()
	{
		System.out.print("AST NODE UNKNOWN\n");
	}

	public static void PrintRule(String left, String right) {

		if (printDerivationRule)
			System.out.format("======== %s -> %s\n", left, right);
	}
}
