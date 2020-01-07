package IR;

import MIPS.*;

public class IRcommand_Jr extends IRcommand {
    IRReg reg;

    public IRcommand_Jr(IRReg reg) {
        this.reg = reg;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tjr %s\n", reg.MIPSme());
    }
}

