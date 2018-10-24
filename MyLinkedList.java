
class Node<T>
{
    public T data;
    public Node<T> link;

    /*  Constructor  */
    public Node()
    {
        link = null;
        data = null;
    }
    /*  Constructor  */
    public Node(T d,Node<T> n)
    {
        data = d;
        link = n;
    }
    /*  Function to set link to next Node  */
    public void setLink(Node<T> n)
    {
        link = n;
    }
    /*  Function to set data to current Node  */
    public void setData(T d)
    {
        data = d;
    }
    /*  Function to get link to next node  */
    public Node<T> getLink()
    {
        return link;
    }
    /*  Function to get data from current Node  */
    public T getData()
    {
        return data;
    }
}


public class MyLinkedList<T> 
{
	public Node<T> start;
	public int size;

	//Constructor
	
	MyLinkedList()
	{
		this.start=null;
		this.size = 0;
	}
	
	//Is empty checker
	public boolean IsEmpty()
	{
		return start == null;
	}
	//Is member checker
	public boolean IsMember(T o)
	{
		if (size==0) {return false;}
		if (size ==1) {return (o == start.getData());}
		Node<T> ptr = new Node<T>();
		ptr = start;
		if (start.getData()==o) {return true;} else ptr=start.getLink();
		while (ptr != null)
		{
			if (ptr.getData()==o) {return true;} else ptr=ptr.getLink();
		}
		return false;
	}
	//Inserting int o to the beginning of the set method
	public void Insert(T o)
	{
		if (IsMember(o)) return;

		Node<T> n = new Node<T>(o, null);
		size++;
		if(start == null)
        {
            start = n;
        }
        else
        {
            n.setLink(start);
            start = n;
        }
	}

	//Inserting to the end of the set
	public void InsertEnd(T o)
	{
		Node<T> ptr = start;
		Node<T> n = new Node<T>(o,null);
    if (ptr==null)
    {
      start=n;
      return;
    }
    if (ptr.getLink()==null)
    {
      start.setLink(n);
      return;
    }
		while (ptr.getLink()!=null)
		{
			ptr = ptr.getLink();
		}
		ptr.setLink(n);
	}

	//Deleting Object o from the set
	public void Delete(T o) throws ObjectNotFoundException
	{
		if (size==0) {throw new ObjectNotFoundException("Empty set");}
		else
		{
			Node<T> ptr = start;
			int k = 0;

			for (int i = 0; i< size -2;i++)
			{
				if (ptr.getData()==o)
				{
					Node<T> tmp=ptr.getLink();
					ptr.setData(tmp.getData());
					ptr.setLink(tmp.getLink());
					k = 1;
					break;
				}

				ptr = ptr.getLink();
			}
			Node<T> tmp = ptr.getLink();
			if (ptr.getData()==o)
			{
				ptr.setData(tmp.getData());
				ptr.setLink(null);
				k=1;
			} else if (tmp.getData()==o)
			{
				ptr.setLink(null);
				k=1;

			}



			if (k==1) {size--;} else {throw new ObjectNotFoundException("Object was not in the set");}
		}


	}

	public T get(int i) throws IndexOutOfBoundsException
	{
		Node<T> ptr = new Node<T>();
		ptr = start;
		while (i !=0)
		{
			ptr = ptr.getLink();
			i--;
		}
		return ptr.getData();
	}
	




}

