package TYPES;

// finished. simple class - only has getInstance method.
public class Type_String extends Type_Primitive
{
	private static Type_String instance = null;
	protected Type_String() {}
	public static Type_String getInstance()
	{
		if (instance == null)
		{
			instance = new Type_String();
			instance.name = "string";
		}
		return instance;
	}
}
