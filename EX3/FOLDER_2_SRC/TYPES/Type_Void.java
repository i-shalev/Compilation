package TYPES;

// finished. simple class - only has getInstance method.
public class Type_Void extends Type
{
	private static Type_Void instance = null;
	protected Type_Void() {}
	public static Type_Void getInstance()
	{
		if (instance == null)
		{
			instance = new Type_Void();
		}
		return instance;
	}
}
