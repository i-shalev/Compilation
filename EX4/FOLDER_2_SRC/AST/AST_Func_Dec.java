package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.*;
import IR.*;

public class AST_Func_Dec extends AST_Class_Field
{
    public String retType;
    public String funcName;
    public AST_Params_List params;
    public AST_Stmt_List statements;
    public Type_Func funcType;

    public AST_Func_Dec(String retType, String funcName, AST_Params_List params, AST_Stmt_List statements)
    {
        if (params != null) PrintRule("funcDec", "ID ID ( idList ) { stmtList }");
        if (params == null) PrintRule("funcDec", "ID ID ( ) { stmtList }");

        this.retType = retType;
        this.funcName = funcName;
        this.params = params;
        this.statements = statements;
    }

    public void PrintMe()
    {
        if (params != null) params.PrintMe();
        if (statements != null) statements.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                String.format("Function\nDEC\n(%s %s)", retType, funcName));

        if (params != null) AST_Graphviz.getInstance().logEdge(SerialNumber, params.SerialNumber);
        if (statements != null) AST_Graphviz.getInstance().logEdge(SerialNumber, statements.SerialNumber);
    }

    public Type_Func SemantDeclaration() throws Exception {
        Type ret = SymbolTable.find(retType);
        
        if (!(ret instanceof Type_Object || ret instanceof Type_Void))
            throw new SemanticException("Return type is invalid");

        if (!(SymbolTable.isGlobalScope() || SymbolTable.isInScope(Type_Scope.CLASS)))
            throw new SemanticException("Declaration is invalid");

        // Get function parameters type
        Type_List paramsTypes = params != null ? params.SemantDeclaration() : null;

        Type_Func func = new Type_Func(ret, funcName, paramsTypes, params);

        if (SymbolTable.findInScope(funcName) != null)
            throw new SemanticException("Function already declared in scope");

        if (SymbolTable.isInScope(Type_Scope.CLASS)) { // function is a class method
            Type_Class currClass = SymbolTable.findClass(); // get current class

            Type_Class father = currClass.father;
            if (father != null) {
                Type_Func fatherFunc = father.getFuncField(funcName);
                if (fatherFunc != null && !func.isOverride(fatherFunc))
                    throw new SemanticException("Function overloading is not allowed");
            }
        }

        return func;
    }

    public Type SemantMe() throws Exception {
        Type_Func funcType = SemantDeclaration();
        SymbolTable.enter(funcName, funcType);
        if (SymbolTable.isDirectlyInScope(Type_Scope.CLASS)){
          Type_Class c1 = SymbolTable.findClass();
          c1.data_members = Type_List.add(funcType,c1.data_members);


            boolean isFound = false;
            for (Symbol symbol : c1.methods)
            {
                if (funcName.equals(symbol.name))
                {
                    symbol.type = funcType; //update override method
                    isFound = true;
                }
            }
            if (!isFound) { // need to add the func if we didn't
                c1.methods.add(new Symbol(funcName, funcType));
            }
        }
        SymbolTable.beginScope(Type_Scope.FUNC);
        if (params != null) params.SemantMe();
        if (statements != null) { statements.SemantMe(); }
        SymbolTable.endScope();
        this.funcType=funcType;
        return funcType;
    }

    public IRReg IRMe()
    {
        boolean isMain = funcName.equals("main") && funcType.returnType == Type_Void.getInstance() && funcType.params2.size() == 0;
        int numLocals = funcType.locals.size();

        String funcNameLabel = IR.uniqueLabel("func_name");
        IR.add(new IRcommand_String_Literal(String.format("\"%s\"", funcName), funcNameLabel));

        IR.add(new IRcommand_Label("_" + funcType.name));
        if (isMain) {
            for (String initLabel : IR.globalVars) {
                IR.add(new IRcommand_Jal(initLabel));
            }
        }

        // prologue
        IR.add(new IRcommand_Addi(IRReg.sp, IRReg.sp, -3 * 4));
        IR.add(new IRcommand_Sw(IRReg.fp, IRReg.sp, 0));  // save fp
        IR.add(new IRcommand_Sw(IRReg.ra, IRReg.sp, 4));  // save ra
        IR.add(new IRcommand_La(IRReg.ra, funcNameLabel));  // get function name
        IR.add(new IRcommand_Sw(IRReg.ra, IRReg.sp, 8));  // save function name
        IR.add(new IRcommand_Move(IRReg.fp, IRReg.sp));  // update to new fp
        IR.add(new IRcommand_Addi(IRReg.sp, IRReg.sp, -(numLocals + 8) * 4));  // allocate stack
        IR.add(new IRcommand_Jal("store_tmp_regs"));

        statements.IRMe();

        // epilogue
        IR.add(new IRcommand_Label(funcType.name + "_epilogue"));
        if (isMain) {
            IR.add(new IRcommand_exit());
        }
        IR.add(new IRcommand_Jal("retrieve_tmp_regs"));
        IR.add(new IRcommand_Addi(IRReg.sp, IRReg.sp, (numLocals + 8) * 4));  // deallocate stack
        IR.add(new IRcommand_Lw(IRReg.ra, IRReg.sp, 4));  // retrieve ra
        IR.add(new IRcommand_Lw(IRReg.fp, IRReg.sp, 0));  // retrieve fp
        IR.add(new IRcommand_Addi(IRReg.sp, IRReg.sp, 3 * 4));
        IR.add(new IRcommand_Jr(IRReg.ra));  // return

        return null;
    }
}
