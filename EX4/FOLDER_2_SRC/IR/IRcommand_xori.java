package IR;

import MIPS.MIPS;

public class IRcommand_xori extends IRcommand
{
    IRReg dst;
    IRReg num;
    int imm;

    public IRcommand_xori(IRReg dst, IRReg num1, int imm)
    {
        this.dst = dst;
        this.num = num1;
        this.imm = imm;
    }
    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\txori %s, %s, %d\n", dst.MIPSme(), num.MIPSme() ,imm);
    }
}

