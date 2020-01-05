package IR;

import MIPS.*;

public class IRcommand_Jump extends IRcommand
{
    String label;

    public IRcommand_Jump(String label)
    {
        this.label = label;
    }
    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\tj %s\n", label);
    }
}
