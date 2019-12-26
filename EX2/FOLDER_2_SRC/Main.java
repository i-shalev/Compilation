   
import java.io.*;
import java.io.PrintWriter;
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

		boolean printDerivationRule = true;
		boolean printTokens = true;

		try
		{
			file_reader = new FileReader(inputFilename);
			file_writer = new PrintWriter(outputFilename);
			l = new Lexer(file_reader);
			p = new Parser(l, file_writer, printTokens);
			AST_Node.printDerivationRule = printDerivationRule;
			AST = (AST_Program) p.parse().value;
			AST.PrintMe();
			file_writer.println("OK");
			file_writer.close();
			AST_GRAPHVIZ.getInstance().finalizeFile(); // Creates AST graphics
    	}
			     
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


