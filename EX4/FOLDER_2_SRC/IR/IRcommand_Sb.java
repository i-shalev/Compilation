package IR;

import MIPS.*;

public class IRcommand_Sb extends IRcommand
{
    IRReg dst;
    IRReg src;
    int offset;

    public IRcommand_Sb(IRReg dst, IRReg src, int offset)
    {
        this.dst = dst;
        this.src = src;
        this.offset = offset;
    }

    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\tsb %s, %d(%s)\n", src.MIPSme(), offset, dst.MIPSme());
    }
}
