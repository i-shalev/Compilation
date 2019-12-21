package AST;

public abstract class AST_Node
{
	public static boolean printDerivationRule = true;
	public int SerialNumber;

	public AST_Node(){
		SerialNumber = AST_Node_Serial_Number.getFresh();
	}

	public void PrintMe()
	{
		System.out.print("AST NODE UNKNOWN\n");
	}

	public static void PrintRule(String left, String right) {
		if (printDerivationRule)
			System.out.format("======== %s -> %s\n", left, right);
	}
}
