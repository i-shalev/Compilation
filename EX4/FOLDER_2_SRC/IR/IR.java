package IR;


import MIPS.MIPS;

import java.util.*;

public class IR {
    public static final Deque<String> globalVars = new ArrayDeque<>();
    private static final Deque<IRcommand> commands = new ArrayDeque<>();
    private static int uniqueLabelCounter = 0;

    public static void add(IRcommand cmd) {
        commands.add(cmd);
    }

    public void MIPSme() {
        for (IRcommand cmd : commands)
            cmd.MIPSme();
    }

    public static String uniqueLabel(String label) {
        return String.format("_%d_%s", uniqueLabelCounter++, label);
    }

    private IR() {
    }

    public static void init() {
        MIPS.writer.println(".text");
        MIPS.writer.println(".globl main");
        MIPS.writer.println("main:");
        // TODO: find a way to init globals here instead of _main
        MIPS.writer.println("\t j _main");

        IR.add(new IRcommand_Label("store_tmp_regs"));
        IR.add(new IRcommand_Sw(IRReg.t0, IRReg.fp, -1 * 4));  // save t0
        IR.add(new IRcommand_Sw(IRReg.t1, IRReg.fp, -2 * 4));  // save t1
        IR.add(new IRcommand_Sw(IRReg.t2, IRReg.fp, -3 * 4));  // save t2
        IR.add(new IRcommand_Sw(IRReg.t3, IRReg.fp, -4 * 4));  // save t3
        IR.add(new IRcommand_Sw(IRReg.t4, IRReg.fp, -5 * 4));  // save t4
        IR.add(new IRcommand_Sw(IRReg.t5, IRReg.fp, -6 * 4));  // save t5
        IR.add(new IRcommand_Sw(IRReg.t6, IRReg.fp, -7 * 4));  // save t6
        IR.add(new IRcommand_Sw(IRReg.t7, IRReg.fp, -8 * 4));  // save t7
        IR.add(new IRcommand_Jr(IRReg.ra));

        IR.add(new IRcommand_Label("retrieve_tmp_regs"));
        IR.add(new IRcommand_Lw(IRReg.t0, IRReg.fp, -1 * 4));  // retrieve t0
        IR.add(new IRcommand_Lw(IRReg.t1, IRReg.fp, -2 * 4));  // retrieve t1
        IR.add(new IRcommand_Lw(IRReg.t2, IRReg.fp, -3 * 4));  // retrieve t2
        IR.add(new IRcommand_Lw(IRReg.t3, IRReg.fp, -4 * 4));  // retrieve t3
        IR.add(new IRcommand_Lw(IRReg.t4, IRReg.fp, -5 * 4));  // retrieve t4
        IR.add(new IRcommand_Lw(IRReg.t5, IRReg.fp, -6 * 4));  // retrieve t5
        IR.add(new IRcommand_Lw(IRReg.t6, IRReg.fp, -7 * 4));  // retrieve t6
        IR.add(new IRcommand_Lw(IRReg.t7, IRReg.fp, -8 * 4));  // retrieve t7
        IR.add(new IRcommand_Jr(IRReg.ra));

        // exit function for invalid dereference
        IR.add(new IRcommand_Label("exit_invalid_dereference"));
        IR.add(new IRcommand_La(IRReg.a0, "string_invalid_ptr_dref"));
        IR.add(new IRcommand_PrintString(IRReg.a0));
        IR.add(new IRcommand_exit());

        // exit function for invalid array index
        IR.add(new IRcommand_Label("exit_access_violation"));
        IR.add(new IRcommand_La(IRReg.a0, "string_access_violation"));
        IR.add(new IRcommand_PrintString(IRReg.a0));
        IR.add(new IRcommand_exit());

        // exit function for division by zero
        IR.add(new IRcommand_Label("exit_division_by_zero"));
        IR.add(new IRcommand_La(IRReg.a0, "string_illegal_div_by_0"));
        IR.add(new IRcommand_PrintString(IRReg.a0));
        IR.add(new IRcommand_exit());

        // strings for runtime checks
        MIPS.dataWriter.println(".data");
        MIPS.dataWriter.println("string_access_violation: .asciiz \"Access Violation\"");
        MIPS.dataWriter.println("string_illegal_div_by_0: .asciiz \"Division By Zero\"");
        MIPS.dataWriter.println("string_invalid_ptr_dref: .asciiz \"Invalid Pointer Dereference\"");
    }
}
