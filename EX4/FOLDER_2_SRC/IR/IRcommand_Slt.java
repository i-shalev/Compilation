package IR;

import MIPS.*;

public class IRcommand_Slt extends IRcommand
{
    IRReg dst;
    IRReg src1;
    IRReg src2;

    public IRcommand_Slt(IRReg dst, IRReg num1, IRReg num2)
    {
        this.dst = dst;
        this.src1 = num1;
        this.src2 = num2;
    }
    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\tslt %s, %s, %s\n", dst.MIPSme(), src1.MIPSme() , src2.MIPSme());
    }
}

