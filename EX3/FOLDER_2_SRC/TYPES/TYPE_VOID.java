package TYPES;

// finished. simple class - only has getInstance method.
public class TYPE_VOID extends TYPE
{
	private static TYPE_VOID instance = null;
	protected TYPE_VOID() {}
	public static TYPE_VOID getInstance()
	{
		if (instance == null)
		{
			instance = new TYPE_VOID();
		}
		return instance;
	}
}
