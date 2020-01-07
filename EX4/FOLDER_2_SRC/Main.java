
import java.io.*;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;
import AST.*;
import IR.*;
import MIPS.*;

public class Main {
    static public void main(String argv[]) {
        Lexer l;
        Parser p;
        Symbol s;
        AST_DEC_LIST AST;
        FileReader file_reader;
        PrintWriter file_writer;
        String inputFilename = argv[0];
        String outputFilename = argv[1];

        try {
            file_reader = new FileReader(inputFilename);
            file_writer = new PrintWriter(outputFilename);
            l = new Lexer(file_reader);
            p = new Parser(l);
            AST = (AST_DEC_LIST) p.parse().value;
            AST.PrintMe();
            AST.SemantMe();
            AST.IRme();
            IR.getInstance().MIPSme();
            AST_GRAPHVIZ.getInstance().finalizeFile();
            sir_MIPS_a_lot.getInstance().finalizeFile();
            file_writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


