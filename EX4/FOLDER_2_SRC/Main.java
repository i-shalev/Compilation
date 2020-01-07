import IR.*;
import java.io.*;

import SYMBOL_TABLE.SymbolTable;
import java_cup.runtime.Symbol;
import AST.*;
import MIPS.*;


public class Main
{

    static public void main(String argv[]) {
        Lexer l;
        Symbol s;
        FileReader file_reader;
        PrintWriter file_writer;
        String inputFilename = argv[0];
        String outputFilename = argv[1];

        boolean printDerivationRule = false;
        boolean printTokens = false;
        boolean printSemanticStackTrace = false;

        try{
            file_reader = new FileReader(inputFilename);
            file_writer = new PrintWriter(outputFilename);
        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }
        try
        {
            l = new Lexer(file_reader);
            Parser parser = new Parser(l, file_writer, printTokens);

            AST_Program AST = (AST_Program) parser.parse().value;

            SymbolTable.Init();
            AST.SemantMe();
            AST_Node.printDerivationRule = printDerivationRule;

            file_writer.close();
            // SymbolTable.toGraphviz("./FOLDER_5_OUTPUT/SymbolTable.txt");

            IR.init();
            AST.IRMe();
            IR.MIPSme();
            AST_Graphviz.getInstance().finalizeFile();
            MIPS.toFile(argv[1]);

            RegAlloc.allocate(argv[1]);
        }
        catch (AST_Node.SemanticException e)
        {
            file_writer.println(String.format("[Semantic Error] [Line %d] : %s", e.getLine(), e.getMessage()));
            file_writer.close();
            if (printSemanticStackTrace)
                e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        finally {
            file_writer.close();
            try {
                file_reader.close();
            } catch (IOException ignored) {
            }
        }
    }
}