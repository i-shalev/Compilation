package IR;

import MIPS.*;

public class IRcommand_Sw extends IRcommand {
    IRReg dst;
    IRReg src;
    int offset;

    public IRcommand_Sw(IRReg dst, IRReg src, int offset) {
        this.dst = dst;
        this.src = src;
        this.offset = offset;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tsw %s, %d(%s)\n", src.MIPSme(), offset, dst.MIPSme());
    }
}
