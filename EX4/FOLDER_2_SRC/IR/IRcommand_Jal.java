package IR;

import MIPS.*;

public class IRcommand_Jal extends IRcommand
{
    String label;

    public IRcommand_Jal(String label)
    {
        this.label = label;
    }
    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\tjal %s\n", label);
    }
}

