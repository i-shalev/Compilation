package IR;

import MIPS.*;

public class IRcommand_La extends IRcommand {
    IRReg dst;
    String val;

    public IRcommand_La(IRReg dst, String val) {
        this.dst = dst;
        this.val = val;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tla %s, %s\n", dst.MIPSme(), val);
    }
}
