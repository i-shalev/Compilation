package IR;

import MIPS.*;

public class IRcommand_Sb extends IRcommand {
    IRReg dst;
    IRReg src;
    int offset;

    public IRcommand_Sb(IRReg src, IRReg dst, int offset) {
        this.src = src;
        this.dst = dst;
        this.offset = offset;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tsb %s, %d(%s)\n", src.MIPSme(), offset, dst.MIPSme());
    }
}
