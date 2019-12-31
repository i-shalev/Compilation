package TYPES;

public class Type_List extends Type
{
	public Type head;
	public Type_List next;
	public Type_List(Type head, Type_List next)
	{
		this.head = head;
		this.next = next;
	}
  public static Type_List add(Type newHead,Type_List dataMembers){
    return new Type_List(newHead,dataMembers);
  }
  public void print(){
    if(head!=null)
      System.out.print(head.name+"->");
    if(next!=null)
      next.print();
  }
}
