package IR;

import MIPS.*;

public class IRcommand_Lb extends IRcommand
{
    IRReg dst;
    IRReg src;
    int   offset;

    public IRcommand_Lb(IRReg dst, IRReg src, int offset)
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
        MIPS.writer.printf("\tlb %s, %d(%s)\n", dst.MIPSme(), offset, src.MIPSme());
    }
}
