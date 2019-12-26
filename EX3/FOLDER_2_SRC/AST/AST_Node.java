package AST;

import TYPES.*;

import java.rmi.server.ExportException;

public abstract class AST_Node
{
	public static boolean printDerivationRule = true; // if false, no derivation rules are printed
	public int SerialNumber;

	public AST_Node(){
		SerialNumber = AST_Node_Serial_Number.getFresh();
	}

	public void PrintMe()
	{
		System.out.print("AST NODE UNKNOWN\n");
	}

	public Type SemantMe() throws Exception { return null; }

	public class SemanticException extends Exception {
		public SemanticException() {
			super();
		}

		public SemanticException(String message) {
			super(message);
		}

	}

		public static void PrintRule(String left, String right) {
		if (printDerivationRule)
			System.out.format("======== %s -> %s\n", left, right);
	}
}
