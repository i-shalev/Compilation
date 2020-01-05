package IR;

        import MIPS.*;

public class IRcommand_Sub extends IRcommand
{
    IRReg dst;
    IRReg num1;
    IRReg num2;

    public IRcommand_Sub(IRReg dst, IRReg num1, IRReg num2)
    {
        this.dst = dst;
        this.num1 = num1;
        this.num2 = num2;
    }
    /***************/
    /* MIPS me !!! */
    /***************/
    public void MIPSme()
    {
        MIPS.writer.printf("\tsub %s, %s, %s\n", dst.MIPSme(), num1.MIPSme() ,num2.MIPSme());
    }
}

