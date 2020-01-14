package AST;

import IR.*;
import TYPES.*;

public class AST_Exp_Binop extends AST_Exp {
	int OP;
	public AST_Exp left;
	public AST_Exp right;
	public boolean isStringsExpressions;

	public AST_Exp_Binop(AST_Exp left, AST_Exp right, int OP) {
		PrintRule("exp", "exp BINOP exp");

		this.left = left;
		this.right = right;
		this.OP = OP;
		this.isStringsExpressions = false;
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

		AST_Graphviz.getInstance().logNode(
				SerialNumber,
				String.format("BINOP\n(%s)", sOP));

		if (left  != null) AST_Graphviz.getInstance().logEdge(SerialNumber, left.SerialNumber);
		if (right != null) AST_Graphviz.getInstance().logEdge(SerialNumber, right.SerialNumber);
	}

	public Type SemantMe() throws Exception{
		Type t1 = null;
		Type t2 = null;
		
		if (left  != null)
			t1 = left.SemantMe();
		if (right != null)
			t2 = right.SemantMe();

    	if (t1 == Type_Int.getInstance() && t2 == Type_Int.getInstance() && OP==3 && right instanceof AST_Exp_Int && ((AST_Exp_Int)right).isZero())
            throw new SemanticException("Cannot divide by zero");
		if (t1 == Type_Int.getInstance() && t2 == Type_Int.getInstance())
		{
			return Type_Int.getInstance();
		}

		if (OP == 0 && t1 == Type_String.getInstance() && t2 == Type_String.getInstance()) // 0 is '+'
		{
			isStringsExpressions = true;
			return Type_String.getInstance();
		}

		if (OP == 6) { // 6 is '='
			if(t1 == Type_String.getInstance() && t2 == Type_String.getInstance())
				isStringsExpressions = true;
		    if (t1 == t2)
		        return Type_Int.getInstance();

		    if (t1 == Type_Nil.getInstance() || t2 == Type_Nil.getInstance()) {
                // If one is null, the other can be any class or array
		        if (t1 instanceof Type_Class || t1 instanceof Type_Array)
		            return Type_Int.getInstance();
                if (t2 instanceof Type_Class || t2 instanceof Type_Array)
                    return Type_Int.getInstance();
            }

            if (t1 instanceof Type_Class && t2 instanceof Type_Class) {
		        if (((Type_Class)t1).isInheritsFrom(t2.name))
		            return Type_Int.getInstance();
                if (((Type_Class)t2).isInheritsFrom(t1.name))
                    return Type_Int.getInstance();
            }
        }

        throw new SemanticException("Illegal binary operation");
	}

	@Override
	public IRReg IRMe() {
		IRReg leftReg = left.IRMe();
		IRReg rightReg = right.IRMe();

		if(isStringsExpressions)
		{
			IRReg dst = new IRReg.TempReg();

			switch(this.OP)
			{
				case 0:
					IRReg tempReg = new IRReg.TempReg();
					IRReg charReg = new IRReg.TempReg();

					// strlen for left string
					String strlenLoopLabel = IR.uniqueLabel("strlen_loop");
					String strlenEndLabel = IR.uniqueLabel("strlen_end");
					IR.add(new IRcommand_Move(tempReg, leftReg));
					IR.add(new IRcommand_Label(strlenLoopLabel));
					IR.add(new IRcommand_Lb(charReg, tempReg, 0));
					IR.add(new IRcommand_beq(charReg, IRReg.zero, strlenEndLabel));
					IR.add(new IRcommand_Addi(tempReg, tempReg, 1));
					IR.add(new IRcommand_Jump(strlenLoopLabel));
					IR.add(new IRcommand_Label(strlenEndLabel));
					IR.add(new IRcommand_Sub(IRReg.a0, tempReg, leftReg));

					// strlen for right string
					strlenLoopLabel = IR.uniqueLabel("strlen_loop");
					strlenEndLabel = IR.uniqueLabel("strlen_end");
					IR.add(new IRcommand_Move(tempReg, rightReg));
					IR.add(new IRcommand_Label(strlenLoopLabel));
					IR.add(new IRcommand_Lb(charReg, tempReg, 0));
					IR.add(new IRcommand_beq(charReg, IRReg.zero, strlenEndLabel));
					IR.add(new IRcommand_Addi(tempReg, tempReg, 1));
					IR.add(new IRcommand_Jump(strlenLoopLabel));
					IR.add(new IRcommand_Label(strlenEndLabel));
					IR.add(new IRcommand_Sub(tempReg, tempReg, rightReg));
					IR.add(new IRcommand_Add(IRReg.a0, IRReg.a0, tempReg));

					// malloc
					IR.add(new IRcommand_Addi(IRReg.a0, IRReg.a0, 1));  // null terminated
					IR.add(new IRcommand_sbrk());
					IR.add(new IRcommand_Move(tempReg, IRReg.v0));
					IR.add(new IRcommand_Move(dst, IRReg.v0));

					// copy left string
					String strcpyLoopLabel = IR.uniqueLabel("strcpy_loop");
					String strcpyEndLabel = IR.uniqueLabel("strcpy_end");
					IR.add(new IRcommand_Label(strcpyLoopLabel));
					IR.add(new IRcommand_Lb(charReg, leftReg, 0));
					IR.add(new IRcommand_beq(charReg, IRReg.zero, strcpyEndLabel));
					IR.add(new IRcommand_Sb(charReg, tempReg, 0));
					IR.add(new IRcommand_Addi(tempReg, tempReg, 1));
					IR.add(new IRcommand_Addi(leftReg, leftReg, 1));
					IR.add(new IRcommand_Jump(strcpyLoopLabel));
					IR.add(new IRcommand_Label(strcpyEndLabel));

					// copy right string
					strcpyLoopLabel = IR.uniqueLabel("strcpy_loop");
					strcpyEndLabel = IR.uniqueLabel("strcpy_end");
					IR.add(new IRcommand_Label(strcpyLoopLabel));
					IR.add(new IRcommand_Lb(charReg, rightReg, 0));
					IR.add(new IRcommand_beq(charReg, IRReg.zero, strcpyEndLabel));
					IR.add(new IRcommand_Sb(charReg, tempReg, 0));
					IR.add(new IRcommand_Addi(tempReg, tempReg, 1));
					IR.add(new IRcommand_Addi(rightReg, rightReg, 1));
					IR.add(new IRcommand_Jump(strcpyLoopLabel));
					IR.add(new IRcommand_Label(strcpyEndLabel));

					// null terminating
					IR.add(new IRcommand_Sb(IRReg.zero, tempReg, 0));
					return dst;

				case 6:
					String strcmpLoopLabel = IR.uniqueLabel("strcmp_loop");
					String strcmpFalseLabel = IR.uniqueLabel("strcmp_false");
					String strcmpEndLabel = IR.uniqueLabel("strcmp_end");

					// TODO maybe should be different 0 <-> 1
					IR.add(new IRcommand_Li(dst, 0));  // assume equality
					IR.add(new IRcommand_Label(strcmpLoopLabel));
					IRReg leftVal = new IRReg.TempReg();
					IRReg rightVal = new IRReg.TempReg();
					IR.add(new IRcommand_Lb(leftVal, leftReg, 0));
					IR.add(new IRcommand_Lb(rightVal, rightReg, 0));
					IR.add(new IRcommand_bne(leftVal, rightVal, strcmpFalseLabel));
					IR.add(new IRcommand_beq(leftVal, IRReg.zero, strcmpEndLabel));
					IR.add(new IRcommand_Addi(leftReg, leftReg, 1));
					IR.add(new IRcommand_Addi(rightReg, rightReg, 1));
					IR.add(new IRcommand_Jump(strcmpLoopLabel));
					IR.add(new IRcommand_Label(strcmpFalseLabel));
					IR.add(new IRcommand_Li(dst, 1));
					IR.add(new IRcommand_Label(strcmpEndLabel));
					return dst;
			}
			return dst;
		}
		else
		{
			switch (this.OP)
			{
				case 0:
					IR.add(new IRcommand_Add(leftReg, leftReg, rightReg));
					break;
				case 1:
					IR.add(new IRcommand_Sub(leftReg, leftReg, rightReg));
					break;
				case 2:
					IR.add(new IRcommand_Mul(leftReg, leftReg, rightReg));
					break;
				case 3:
					IR.add(new IRcommand_beq(rightReg, IRReg.zero, "exit_division_by_zero"));
					IR.add(new IRcommand_Div(leftReg, leftReg, rightReg));
					break;
				case 4:
//					IR.add(new IR.sub(leftReg, IRReg.zero, leftReg));  // make negative
//					IR.add(new IR.sub(rightReg, IRReg.zero, rightReg));  // make negative
//					IR.add(new IRcommand_Slt(leftReg, leftReg, rightReg));
					IR.add(new IRcommand_Slt(leftReg, rightReg, leftReg));
					break;
				case 5:
					IR.add(new IRcommand_Slt(leftReg, leftReg, rightReg));
					break;
				case 6:
					IR.add(new IRcommand_Sub(leftReg, leftReg, rightReg));
					IR.add(new IRcommand_Sltu(leftReg, IRReg.zero, leftReg));  // 1 if not equal
					IR.add(new IRcommand_xori(leftReg, leftReg, 1));  // 0 becomes 1, 1 becomes 0
					break;
			}

			String overflowEndLabel = IR.uniqueLabel("int_underflow_end");
			IR.add(new IRcommand_Li(rightReg, -32768));  // -2^15
			IR.add(new IRcommand_bgt(leftReg, rightReg, overflowEndLabel));
			IR.add(new IRcommand_Move(leftReg, rightReg));
			IR.add(new IRcommand_Label(overflowEndLabel));

			overflowEndLabel = IR.uniqueLabel("int_overflow_end");
			IR.add(new IRcommand_Li(rightReg, 32767));  // 2^15 - 1
			IR.add(new IRcommand_blt(leftReg, rightReg, overflowEndLabel));
			IR.add(new IRcommand_Move(leftReg, rightReg));
			IR.add(new IRcommand_Label(overflowEndLabel));

			return leftReg;
		}
	}
}
