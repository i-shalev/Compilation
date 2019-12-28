import java.io.*;
import java.io.PrintWriter;

import SYMBOL_TABLE.SymbolTable;
import java_cup.runtime.Symbol;
import AST.*;

public class Main
{
	static public void main(String argv[])
	{
		Lexer l;
		Parser p;
		Symbol s;
		AST_Program AST;
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
			SymbolTable.Init();
			//	file_reader = new FileReader(inputFilename);
			//	file_writer = new PrintWriter(outputFilename);
			l = new Lexer(file_reader);
			p = new Parser(l,file_writer, printTokens);
			AST_Node.printDerivationRule = printDerivationRule;
			AST = (AST_Program) p.parse().value;
			AST.PrintMe();
			AST.SemantMe();
			file_writer.println("OK");
			file_writer.close();
			AST_Graphviz.getInstance().finalizeFile();
		}

		catch (AST_Node.SemanticException e){
			file_writer.println(String.format("ERROR(%d)", e.getLine()));
			file_writer.close();
			if (printSemanticStackTrace)
				e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			file_writer.println("XXX");
			file_writer.close();
		}
	}
}

