
//int not found exception
class ObjectNotFoundException extends Exception
{
	public ObjectNotFoundException(String message)
	{
		super(message);
	}
}

public class Myset<T> {
	public MyLinkedList<T> ll;


	//Constructor

	public Myset()
	{
		ll = new MyLinkedList<T>();
	}

	//Is empty checker
	public boolean IsEmpty()
	{
		return ll.IsEmpty();
	}
	//Is member checker
	public boolean IsMember(T o)
	{
		return ll.IsMember(o);
	}
	//Inserting int o to the beginning of the set method
	public void Insert(T o)
	{
		ll.Insert(o);
	}
	//Inserting at end
	public void InsertEnd(T o)
	{
		ll.InsertEnd(o);
	}
	//Deleting int o from the set
	public void Delete(T o) throws ObjectNotFoundException
	{
		ll.Delete(o);
	}

	public int size()
	{
		return ll.size;
	}

	public Node<T> start()
	{
		return ll.start;
	}

	public Myset<T> Union(Myset<T> a)
	{
		Myset<T> un = new Myset<T>();
		int size = ll.size;
		int as = a.size();
		if(size ==0 && as ==0) return un;

		//If size of original is more, we will apply ismember on Myset a
		if (size > as)
		{
			if (as==0)
			{

				Node<T> ptr1 = ll.start;
				for (int i = 0;i<size;i++)
				{
					un.Insert(ptr1.getData());
					ptr1=ptr1.getLink();
					un.ll.size++;
				}
				return un;
			}
			else
			{
				Node<T> ptr = ll.start;
				for (int i = 0; i<size;i++)
				{
					if (a.IsMember (ptr.getData()))
					{
						ptr=ptr.getLink();
					}
					else
					{
						un.Insert(ptr.getData());
						ptr=ptr.getLink();
						un.ll.size++;
					}
				}
				Node<T> ptr1 = a.ll.start;
				for (int i = 0;i<as;i++)
				{
					un.Insert(ptr1.getData());
					ptr1=ptr1.getLink();
					un.ll.size++;
				}
				return un;
			}
		}
		else //If size of a is greater, we apply ismember on original
		{
			if (size==0)
			{
				return a;
			}
			else
			{
				Node<T> ptr = a.ll.start;
				for (int i = 0; i<as;i++)
				{
					if (IsMember (ptr.getData()))
					{
						ptr=ptr.getLink();
					}
					else
					{
						un.Insert(ptr.getData());
						ptr=ptr.getLink();
						un.ll.size++;

					}
				}
				Node<T> ptr1 = ll.start;
				for (int i = 0;i<size;i++)
				{
					un.Insert(ptr1.getData());
					ptr1=ptr1.getLink();
					un.ll.size++;
				}
				return un;
			}
		}


	}



	public Myset<T> Intersection(Myset<T> a)
	{
		Myset<T> inter = new Myset<T>();
		int as = a.ll.size;
		if (as==0) return inter;
		if (ll.size==0) return inter;
		if (as > ll.size)
		{
			Node<T> ptr = a.ll.start;
			for (int i=0;i<as;i++)
			{
				if (IsMember(ptr.getData()))
				{
					inter.Insert(ptr.getData());
					ptr=ptr.getLink();
					(inter.ll.size)++;
				}
				else ptr=ptr.getLink();
			}
		}
		else
		{
			Node<T> ptr = ll.start;
			for (int i=0;i<ll.size;i++)
			{
				if (a.IsMember(ptr.getData()))
				{
					inter.Insert(ptr.getData());
					ptr=ptr.getLink();
					inter.ll.size++;

				}
				else ptr=ptr.getLink();
			}
		}
		return inter;

	}

	public void display()
	{
		Node<T> temp = ll.start;
		while (temp !=null)
		{
			System.out.println(temp.getData());
			temp = temp.getLink();
		}
	}










}
