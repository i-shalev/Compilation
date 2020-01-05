package IR;

import MIPS.*;

public class IRcommand_Sll extends IRcommand
{
    IRReg dst;
    IRReg src;
    int   imm;

    public IRcommand_Sll(IRReg dst, IRReg src, int imm)
    {
        this.dst = dst;
        this.src = src;
        this.imm = imm;
    }
    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\tsll %s, %s, %d\n", dst.MIPSme(), src.MIPSme() , imm);
    }
}

