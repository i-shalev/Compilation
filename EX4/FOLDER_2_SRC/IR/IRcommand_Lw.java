package IR;

import MIPS.*;

public class IRcommand_Lw extends IRcommand
{
    IRReg dst;
    IRReg src;
    int offset;

    public IRcommand_Lw(IRReg dst, IRReg src, int offset)
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
        MIPS.writer.printf("\tlw %s, %d(%s)\n", dst.MIPSme(), offset, src.MIPSme());
    }
}
