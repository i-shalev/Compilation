package TYPES;

public class Type_Scope extends Type {
    public Type_Scope(String name) {
        this.name = name;
    }

    public static Type_Scope CLASS = new Type_Scope("Class scope");
    public static Type_Scope FUNC = new Type_Scope("Func scope");
    public static Type_Scope IF = new Type_Scope("If scope");
    public static Type_Scope WHILE = new Type_Scope("While scope");
}
