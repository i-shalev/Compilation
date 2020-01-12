package IR;

import MIPS.MIPS;

public class IRcommand_bne extends IRcommand {
    String label;
    IRReg src1;
    IRReg src2;

    public IRcommand_bne(IRReg src1, IRReg src2, String label) {
        this.label = label;
        this.src1 = src1;
        this.src2 = src2;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tbne %s, %s, %s\n", src1.MIPSme(), src2.MIPSme(), label);
    }
}