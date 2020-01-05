package IR;

import MIPS.*;

public class IRcommand_PrintInt extends IRcommand
{
	IRReg value;
	
	public IRcommand_PrintInt(IRReg value)
	{
		this.value = value;
	}
	
	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme()
	{
		// // print_int
		MIPS.writer.printf("\tmove $a0, %s  # start of print_int\n", value.MIPSme());
		MIPS.writer.printf("\tli $v0, 1\n");
		MIPS.writer.printf("\tsyscall\n");
		// // print_char (whitespace)
		MIPS.writer.printf("\tli $a0, 32\n");
		MIPS.writer.printf("\tli $v0, 11\n");
		MIPS.writer.printf("\tsyscall  # end of print_int\n");
	}
}
