package IR;

import MIPS.*;

public class IRcommand_Get_Global extends IRcommand {
    String name;
    IRReg dst;

    public IRcommand_Get_Global(String name, IRReg dst) {
        this.name = name;
        this.dst = dst;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tla %s, global_%s\n", dst.MIPSme(), name);
    }
}
