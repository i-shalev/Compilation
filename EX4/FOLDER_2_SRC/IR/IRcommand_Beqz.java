package IR;

import MIPS.*;

public class IRcommand_Beqz extends IRcommand
{
    IRReg value;
    String label;

    public IRcommand_Beqz(IRReg value, String label)
    {
        this.value = value;
        this.label = label;
    }

    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\tbeqz %s, %s\n", value.MIPSme(), label);
    }
}
