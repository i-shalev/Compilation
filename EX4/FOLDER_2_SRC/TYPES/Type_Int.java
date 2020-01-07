package TYPES;

// finished. simple class - only has getInstance method.
public class Type_Int extends Type_Primitive
{
	private static Type_Int instance = null;
	protected Type_Int() {}
	public static Type_Int getInstance()
	{
		if (instance == null)
		{
			instance = new Type_Int();
			instance.name = "int";
		}
		return instance;
	}
}
