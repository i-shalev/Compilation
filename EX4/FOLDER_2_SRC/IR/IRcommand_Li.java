package IR;

import MIPS.*;

public class IRcommand_Li extends IRcommand {
    IRReg dst;
    int val;

    public IRcommand_Li(IRReg dst, int val) {
        this.dst = dst;
        this.val = val;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tli %s, %d\n", dst.MIPSme(), val);
    }
}
