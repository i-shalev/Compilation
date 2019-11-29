@echo off
cd ./FOLDER_0_JFlex
call jflex LEX_FILE.lex
move Lexer.java ../FOLDER_2_SRC/Lexer.java
pause