package IR;

import MIPS.*;

public class IRcommand_Declare_Data extends IRcommand {
    String name;
    int numBytes;

    public IRcommand_Declare_Data(String name, int numBytes) {
        this.name = name;
        this.numBytes = numBytes;
    }

    public void MIPSme() {
        MIPS.dataWordsWriter.printf("%s:\n\t.align 4\n\t.word %d\n", name, numBytes);
    }
}
