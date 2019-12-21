package TYPES;

// finished. simple class - only has getInstance method.
public class TYPE_NIL extends TYPE
{
    private static TYPE_NIL instance = null;
    protected TYPE_NIL() {}
    public static TYPE_NIL getInstance()
    {
        if (instance == null)
        {
            instance = new TYPE_NIL();
        }
        return instance;
    }
}
