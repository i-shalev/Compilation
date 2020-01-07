package IR;

import MIPS.MIPS;

public class IRcommand_blt extends IRcommand {
    IRReg src1;
    IRReg src2;
    String label;

    public IRcommand_blt(IRReg num1, IRReg num2, String label) {
        this.label = label;
        this.src1 = num1;
        this.src2 = num2;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tblt %s, %s, %s\n", src1.MIPSme(), src2.MIPSme(), label);
    }
}

