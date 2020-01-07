package IR;

import MIPS.*;

public class IRcommand_PrintString extends IRcommand {
    IRReg value;

    public IRcommand_PrintString(IRReg value) {
        this.value = value;
    }

    public void MIPSme() {
        MIPS.writer.printf("\tmove $a0, %s  # start of print_string\n", value.MIPSme());
        MIPS.writer.printf("\tli $v0, 4\n");
        MIPS.writer.printf("\tsyscall  # end of print_string\n");
    }
}
