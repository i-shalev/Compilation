package IR;

import MIPS.*;

public class IRcommand_String_Literal extends IRcommand
{
    String str;
    String label;

    public IRcommand_String_Literal(String str, String label)
    {
        this.label = label;
        this.str = str;
    }

    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.dataWriter.printf("%s: .asciiz %s\n", label, str);
    }
}
