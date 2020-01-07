package IR;

import MIPS.*;

public class IRcommand_Jalr extends IRcommand {
    IRReg reg;

    public IRcommand_Jalr(IRReg reg) {
        this.reg = reg;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tjalr %s\n", reg.MIPSme());
    }
}

