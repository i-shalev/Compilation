package TYPES;

public class Type_Array extends Type_Object
{
    public Type elementType;

    public Type_Array(Type elementType, String name)
    {
        this.name = name;
        this.elementType = elementType;
    }
}