package IR;

import MIPS.*;

public class IRcommand_Label extends IRcommand {
    String label;

    public IRcommand_Label(String label_name) {
        this.label = label_name;
    }

    public void MIPSme() {
        MIPS.writer.printf("\n%s:\n", label);
    }
}
