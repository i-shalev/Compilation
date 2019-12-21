package TYPES;

public class Type_List
{
	public Type head;
	public Type_List next;
	public Type_List(Type head, Type_List next)
	{
		this.head = head;
		this.next = next;
	}
}
