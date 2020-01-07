package TYPES;

// finished. simple class - only has getInstance method.
public class Type_Nil extends Type
{
    private static Type_Nil instance = null;
    protected Type_Nil() {}
    public static Type_Nil getInstance()
    {
        if (instance == null)
        {
            instance = new Type_Nil();
        }
        return instance;
    }
}
