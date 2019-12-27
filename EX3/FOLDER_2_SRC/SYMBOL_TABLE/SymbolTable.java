package SYMBOL_TABLE;
import java.io.PrintWriter;
import TYPES.*;

public class SymbolTable {
    private static int hashArraySize = 13;
    private static Entry[] table = new Entry[hashArraySize];
    private static Entry top; // the last element we entered
    private static int top_index = 0;

    private static int hash(String s) {
        if (s.charAt(0) == 'l') return 1;
        if (s.charAt(0) == 'm') return 1;
        if (s.charAt(0) == 'r') return 3;
        if (s.charAt(0) == 'i') return 6;
        if (s.charAt(0) == 'd') return 6;
        if (s.charAt(0) == 'k') return 6;
        if (s.charAt(0) == 'f') return 6;
        if (s.charAt(0) == 'S') return 6;
        return 12; // default
    }

    public static void enter(String name, Type t) {
        // the symbol table is the hash table from lecture 5 slide 20.
        int hashIdx = hash(name); // this is the index in the hash table
        Entry next = table[hashIdx]; // we will insert the new element in the beginning of the list
        Entry e = new Entry(name, t, hashIdx, next, top, top_index++);
        top = e; // update top
        table[hashIdx] = e;
        PrintMe();

    }

    // Returns the type of name if defined, or null if undefined
    public static boolean exists(String name) {
        return (find(name) != null);
    }

    // Returns the type of name if defined, or null if undefined
    public static Type find(String name) {
        Entry e;

        for (e = table[hash(name)]; e != null; e = e.next) {
            if (name.equals(e.name)) {
                return e.type;
            }
        }

        return null;
    }


    // TODO: and relevant find functions

    // Returns the current function, or null if not in a function
    public static Type_Func findFunc() {
        Entry e = top;
        while (e != null && !(e.type instanceof Type_Func)) {
            e = e.prevtop;
        }
        return e != null ? (Type_Func) e.type : null;
    }

    // Returns the current class, or null if not in a class
    public static Type_Class findClass() {
        Entry e = top;
        while (e != null && !(e.type instanceof Type_Class)) {
            e = e.prevtop;
        }
        return e != null ? (Type_Class) e.type : null;
    }

    // Returns a Type object that is named typeName, if defined and it is not a Type_Func. Otherwise, returns null
    public static Type findTypeName(String typeName) {
        Entry e = top;
        while (e != null && !(e.type.name.equals(typeName))) {
            e = e.prevtop;
        }
        if (e != null && e.type instanceof Type_Func)
            return null;
        return e != null ? e.type : null;
    }

    // Checks if name appears in the current scope, returns its Type is yes. Otherwise, returns null
    public static Type findInScope(String name) {
        Entry entry = top;
        int i = top_index - 1;
        while (entry != null && !(entry.type instanceof Type_Scope)) {
            i--;
            entry = entry.prevtop;
        }
        for (entry = table[hash(name)]; entry != null && entry.index > i; entry = entry.next) {
            if (name.equals(entry.name)) {
                return entry.type;
            }
        }
        return null;
    }

    // Checks if we are currently in the global scope
    public static boolean isGlobalScope()
    {
        Entry e = top;
        while (e != null && !(e.type instanceof Type_Scope))
        {
            e = e.prevtop;
        }
        return e == null; // no Type in table
    }

    // Checks if we are in the scope which its open bound is given as a parameter
    public static boolean isInScope(Type_Scope scopeType)
    {
        Entry e = top;
        while (e != null && e.type != scopeType)
        {
            e = e.prevtop;
        }
        return e != null; // if e.type == Type.CLASS we're in class scope
    }

    public static void Init() {

        /* We want to allow to create vars from class type iff the class is defined.
           int and strings are defined by default */
        enter("int", Type_Int.getInstance());
        enter("string", Type_String.getInstance());

        // Enter lib functions
        Type_Func printIntFunc = new Type_Func(Type_Void.getInstance(), "PrintInt",
                new Type_List(Type_Int.getInstance(), null));

        Type_Func printStringFunc = new Type_Func(Type_Void.getInstance(), "PrintString",
                new Type_List(Type_String.getInstance(), null));

        Type_Func printTraceFunc = new Type_Func(Type_Void.getInstance(), "PrintTrace",
                null);

        enter("PrintInt", printIntFunc);
        enter("PrintString", printStringFunc);
        enter("PrintTrace", printTraceFunc);
    }


    //TODO: end of our editing scope, delete the comment when done

    public static void beginScope(Type_Scope scope) {
        enter(scope.name, scope);
        PrintMe();
    }

    public static void beginScope() {
        /************************************************************************/
        /* Though <SCOPE-BOUNDARY> entries are present inside the symbol table, */
        /* they are not really types. In order to be ablt to debug print them,  */
        /* a special Type_Scope was developed for them. This     */
        /* class only contain their type name which is the bottom sign: _|_     */
        /************************************************************************/
        enter(
                "SCOPE-BOUNDARY",
                new Type_Scope("NONE"));

        PrintMe();
    }

    public static void endScope() {
        /**************************************************************************/
        /* Pop elements from the symbol table stack until a SCOPE-BOUNDARY is hit */
        /**************************************************************************/
        while (!top.name.equals("Class scope") &&
                !top.name.equals("Func scope") &&
                !top.name.equals("If scope") &&
                !top.name.equals("While scope"))
        {
            table[top.index] = top.next;
            top_index = top_index - 1;
            top = top.prevtop;
        }
        /**************************************/
        /* Pop the SCOPE-BOUNDARY sign itself */
        /**************************************/
        table[top.index] = top.next;
        top_index = top_index - 1;
        top = top.prevtop;

        /*********************************************/
        /* Print the symbol table after every change */
        /*********************************************/
        PrintMe();
    }

    //Don't touch - Print me
    public static int n = 0;

    public static void PrintMe() {
        int i = 0;
        int j = 0;
        String dirname = "./FOLDER_5_OUTPUT/";
        String filename = String.format("SYMBOL_TABLE_%d_IN_GRAPHVIZ_DOT_FORMAT.txt", n++);

        try {
            /*******************************************/
            /* [1] Open Graphviz text file for writing */
            /*******************************************/
            PrintWriter fileWriter = new PrintWriter(dirname + filename);

            /*********************************/
            /* [2] Write Graphviz dot prolog */
            /*********************************/
            fileWriter.print("digraph structs {\n");
            fileWriter.print("rankdir = LR\n");
            fileWriter.print("node [shape=record];\n");

            /*******************************/
            /* [3] Write Hash Table Itself */
            /*******************************/
            fileWriter.print("hashTable [label=\"");
            for (i = 0; i < hashArraySize - 1; i++) {
                fileWriter.format("<f%d>\n%d\n|", i, i);
            }
            fileWriter.format("<f%d>\n%d\n\"];\n", hashArraySize - 1, hashArraySize - 1);

            /****************************************************************************/
            /* [4] Loop over hash table array and print all linked lists per array cell */
            /****************************************************************************/
            for (i = 0; i < hashArraySize; i++) {
                if (table[i] != null) {
                    /*****************************************************/
                    /* [4a] Print hash table array[i] -> entry(i,0) edge */
                    /*****************************************************/
                    fileWriter.format("hashTable:f%d -> node_%d_0:f0;\n", i, i);
                }
                j = 0;
                for (Entry it = table[i]; it != null; it = it.next) {
                    /*******************************/
                    /* [4b] Print entry(i,it) node */
                    /*******************************/
                    fileWriter.format("node_%d_%d ", i, j);
                    fileWriter.format("[label=\"<f0>%s|<f1>%s|<f2>prevtop=%d|<f3>next\"];\n",
                            it.name,
                            it.type.name,
                            it.prevtop_index);

                    if (it.next != null) {
                        /***************************************************/
                        /* [4c] Print entry(i,it) -> entry(i,it.next) edge */
                        /***************************************************/
                        fileWriter.format(
                                "node_%d_%d -> node_%d_%d [style=invis,weight=10];\n",
                                i, j, i, j + 1);
                        fileWriter.format(
                                "node_%d_%d:f3 -> node_%d_%d:f0;\n",
                                i, j, i, j + 1);
                    }
                    j++;
                }
            }
            fileWriter.print("}\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Don't touch - symbol table singleton
    private static SymbolTable instance = null;

    protected SymbolTable() {
    }

    public static SymbolTable getInstance() {
        if (instance == null) {
            /*******************************/
            /* [0] The instance itself ... */
            /*******************************/
            instance = new SymbolTable();

            /*****************************************/
            /* [1] Enter primitive types int, string */
            /*****************************************/
            instance.enter("int", Type_Int.getInstance());
            instance.enter("string", Type_String.getInstance());

            /*************************************/
            /* [2] How should we handle void ??? */
            /*************************************/

            /***************************************/
            /* [3] Enter library function PrintInt */
            /***************************************/
            instance.enter(
                    "PrintInt",
                    new Type_Func(
                            Type_Void.getInstance(),
                            "PrintInt",
                            new Type_List(
                                    Type_Int.getInstance(),
                                    null)));

        }
        return instance;
    }
}
