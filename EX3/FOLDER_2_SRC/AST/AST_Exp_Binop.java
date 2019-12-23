package AST;

import TYPES.*;

public class AST_Exp_Binop extends AST_EXP {
		int OP;
		public AST_EXP left;
		public AST_EXP right;

		public AST_Exp_Binop(AST_EXP left, AST_EXP right, int OP) {
			PrintRule("exp", "exp BINOP exp");

			this.left = left;
			this.right = right;
			this.OP = OP;
		}

		public void PrintMe() {
			String sOP="";

			if (OP == 0) {sOP = "+";}
			if (OP == 1) {sOP = "-";}
			if (OP == 2) {sOP = "*";}
			if (OP == 3) {sOP = "/";}
			if (OP == 4) {sOP = ">";}
			if (OP == 5) {sOP = "<";}
			if (OP == 6) {sOP = "=";}

			if (left != null) left.PrintMe();
			if (right != null) right.PrintMe();

			AST_GRAPHVIZ.getInstance().logNode(
					SerialNumber,
					String.format("BINOP\n(%s)", sOP));

			if (left  != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, left.SerialNumber);
			if (right != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, right.SerialNumber);
		}

	public Type SemantMe() throws Exception{
		Type t1 = null;
		Type t2 = null;
		
		if (left  != null) t1 = left.SemantMe();
		if (right != null) t2 = right.SemantMe();
		
		if (t1 != Type_Int.getInstance() || t2 != Type_Int.getInstance())
			throw new Exception("binary operation between non-integers");

		return Type_Int.getInstance();
	}
}
