package IR;

import MIPS.*;

public class IRcommand_Li extends IRcommand
{
    IRReg dst;
    int   val;

    public IRcommand_Li(IRReg dst, IRReg src, int val)
    {
        this.dst = dst;
        this.val = val;
    }
    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\tli %s, %d\n", dst.MIPSme(), val);
    }
}
