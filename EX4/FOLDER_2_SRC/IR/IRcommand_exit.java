package IR;

import MIPS.MIPS;

public class IRcommand_exit extends IRcommand {
    public void MIPSme() {
        // print newline
        MIPS.writer.printf("\tli $a0, 0xA\n");  // 0xA is newline char
        MIPS.writer.printf("\tli $v0, 11\n");
        MIPS.writer.printf("\tsyscall\n");
        // now really exit
        MIPS.writer.printf("\tli $v0, 10\n");
        MIPS.writer.printf("\tsyscall\n");
    }
}
