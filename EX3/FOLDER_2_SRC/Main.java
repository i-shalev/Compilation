   
import java.io.*;
import java.io.PrintWriter;

import SYMBOL_TABLE.SYMBOL_TABLE;
import java_cup.runtime.Symbol;
import AST.*;

public class Main
{
	static public void main(String argv[])
	{
		Lexer l;
		Parser p;
		Symbol s;
		AST_DEC_LIST AST;
		FileReader file_reader;
		PrintWriter file_writer;
		String inputFilename = argv[0];
		String outputFilename = argv[1];
		
		try
		{
			SYMBOL_TABLE.Init();
			file_reader = new FileReader(inputFilename);
			file_writer = new PrintWriter(outputFilename);
			l = new Lexer(file_reader);
			p = new Parser(l);
			AST = (AST_DEC_LIST) p.parse().value;
			AST.PrintMe();

			AST.SemantMe();
			file_writer.close();
			AST_GRAPHVIZ.getInstance().finalizeFile();
    	}
			     
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


