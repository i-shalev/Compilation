package AST;

import SYMBOL_TABLE.SymbolTable;
import TYPES.*;
import IR.*;

public class AST_Class_Dec extends AST_Node
{
    public String className;
    public String father;
    public AST_Class_Field_List cFieldList;
    public Type_Class classType;

    public AST_Class_Dec(String className, String father, AST_Class_Field_List cFieldList)
    {
        if (father != null) PrintRule("classDec", "CLASS ID EXTENDS ID { cFieldList }");
        if (father == null) PrintRule("classDec", "CLASS ID { cFieldList }");

        this.className = className;
        this.father = father;
        this.cFieldList = cFieldList;
    }

    public void PrintMe()
    {
        if (cFieldList != null) cFieldList.PrintMe();

        AST_Graphviz.getInstance().logNode(
                SerialNumber,
                String.format("Class\nDEC\n(%s)", className));

        if (cFieldList != null) AST_Graphviz.getInstance().logEdge(SerialNumber, cFieldList.SerialNumber);
    }

    public Type SemantMe() throws Exception {
        if (!SymbolTable.isGlobalScope())
            throw new SemanticException("Function declared in a non-global scope");

        Type_Class fatherType = null;

        if (father != null){
            Type t = SymbolTable.find(father);
            if (!(t instanceof Type_Class))
                throw new SemanticException("Father is not of type class");
            fatherType = (Type_Class) t;
        }

        if (SymbolTable.exists((className))) {
            throw new SemanticException("Class name already exists");
        }


        classType = new Type_Class(fatherType, className ,null);

        SymbolTable.enter(className, classType);
        SymbolTable.beginScope(Type_Scope.CLASS);

        classType.data_members = cFieldList.SemantMe();

        SymbolTable.endScope();

        return classType;
    }

    public IRReg IRMe()
    {
        for (AST_Class_Field_List it = cFieldList; it != null; it = it.tail) { it.head.IRMe(); }

        int numMethods = classType.methods.size();
        if (numMethods > 0)  // create vtable
        {
            String initLabel = IR.uniqueLabel("init_" + className + "_vtable");
            IR.globalVars.add(initLabel);
            IR.add(new IRcommand_Label(initLabel));

            // allocate vtable
            String vtableLabel = String.format("_%s_vtable", className);
            IR.add(new IRcommand_Declare_Data(vtableLabel, numMethods * 4));  // allocate vtable
            IRReg vtable = new IRReg.TempReg();
            IR.add(new IRcommand_La(vtable, vtableLabel));  // get vtable address
            IRReg tmpReg = new IRReg.TempReg();
            for (int i = 0; i < numMethods; i++)
            {
                String methodLabel = ((Type_Func)classType.methods.get(i).type).fullName;
                IR.add(new IRcommand_La(tmpReg, "_" + methodLabel));  // get method address
                IR.add(new IRcommand_Sw(tmpReg, vtable, i * 4));  // store in vtable
            }
            IR.add(new IRcommand_Jr(IRReg.ra));
        }
        return null;
    }
}
