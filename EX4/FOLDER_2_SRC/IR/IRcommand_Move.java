package IR;

import MIPS.*;

public class IRcommand_Move extends IRcommand {
    IRReg dst;
    IRReg src;

    public IRcommand_Move(IRReg dst, IRReg src) {
        this.dst = dst;
        this.src = src;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tmove %s, %s\n", dst.MIPSme(), src.MIPSme());
    }
}
