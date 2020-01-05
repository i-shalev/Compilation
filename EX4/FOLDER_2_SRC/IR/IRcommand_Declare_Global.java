package IR;

import MIPS.*;

public class IRcommand_Declare_Global extends IRcommand
{
    String name;
    IRReg value;

    public IRcommand_Declare_Global(String name, IRReg value)
    {
        this.name = name;
        this.value = value;
    }

    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.dataWriter.printf("global_%s:\t.word 0\n", name);
        MIPS.writer.printf("\tsw %s, global_%s\n", value.MIPSme(), name);
    }
}
