public interface TokenNames {
  /* terminals */
  public static final int EOF = 0;
  public static final int error = 1;
  public static final int PLUS = 2;
  public static final int MINUS = 3;
  public static final int TIMES = 4;
  public static final int DIVIDE = 5;
  public static final int LPAREN = 6;
  public static final int RPAREN = 7;
  public static final int SEMICOLON = 8;
  public static final int NUMBER = 9;
  public static final int ID = 10;
  public static final int CLASS = 11;
  public static final int NIL = 12;
  public static final int ARRAY = 13;
  public static final int WHILE = 14;
  public static final int EXTENDS = 15;
  public static final int RETURN = 16;
  public static final int NEW = 17;
  public static final int IF = 18;
  public static final int LBRACE = 19;
  public static final int RBRACE = 20;
  public static final int LBRACK = 21;
  public static final int RBRACK = 22;
  public static final int COMMA = 23;
  public static final int DOT = 24;
  public static final int ELLIPSIS = 25;
  public static final int ASSIGN = 26;
  public static final int EQ = 27;
  public static final int LT = 28;
  public static final int GT = 29;
  public static final int INT = 30;
  public static final int STRING = 31;
  public static final int COMMENT = 32;

  public static String toString(int token){
    switch(token){
      case EOF: return "EOF";
      case PLUS: return "PLUS";
      case MINUS: return "MINUS";
      case TIMES: return "TIMES";
      case DIVIDE: return "DIVIDE";
      case LPAREN: return "LPAREN";
      case RPAREN: return "RPAREN";
      case SEMICOLON: return "SEMICOLON";
      case NUMBER: return "NUMBER";
      case ID: return "ID";
      case CLASS: return "CLASS";
      case NIL: return "NIL";
      case ARRAY: return "ARRAY";
      case WHILE: return "WHILE";
      case EXTENDS: return "EXTENDS";
      case RETURN: return "RETURN";
      case NEW: return "NEW";
      case IF: return "IF";
      case LBRACE: return "LBRACE";
      case RBRACE: return "RBRACE";
      case LBRACK: return "LBRACK";
      case RBRACK: return "RBRACK";
      case COMMA: return "COMMA";
      case DOT: return "DOT";
      case ELLIPSIS: return "ELLIPSIS";
      case ASSIGN: return "ASSIGN";
      case EQ: return "EQ";
      case LT: return "LT";
      case GT: return "GT";
      case INT: return "INT";
      case STRING: return "STRING";
      case COMMENT: return "COMMENT";
      default: return "ERROR";

    }
  }



}

