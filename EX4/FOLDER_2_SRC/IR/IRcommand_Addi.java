package IR;

import MIPS.*;

public class IRcommand_Addi extends IRcommand {
    IRReg dst;
    IRReg num;
    int imm;

    public IRcommand_Addi(IRReg dst, IRReg num, int imm) {
        this.dst = dst;
        this.num = num;
        this.imm = imm;
    }

    public void MIPSme() {
        MIPS.writer.printf("\taddi %s, %s, %d\n", dst.MIPSme(), num.MIPSme(), imm);
    }
}
