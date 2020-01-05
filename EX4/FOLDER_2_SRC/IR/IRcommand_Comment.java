package IR;

import MIPS.*;

public class IRcommand_Comment extends IRcommand
{
    String str;

    public IRcommand_Comment(String str)
    {
        this.str = str;
    }
    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\t# %s\n", str);
    }
}

