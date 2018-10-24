
public class InvertedPageIndex {
	
	public MyHashTable set;

	InvertedPageIndex()
	{
		this.set = new MyHashTable();
	}
	
	
	public void addPage(PageEntry p)
	{
		MyLinkedList<WordEntry> mll = p.getPageIndex().wordList;
		Node<WordEntry> ptr = mll.start;
		while (ptr!=null)
		{
			this.set.addPositionsForWord(ptr.getData());
			ptr=ptr.getLink();
		}
		
	}
	
	public Myset<PageEntry> getPagesWhichContainWord(String str)
	{
		Myset<PageEntry> ans = new Myset<PageEntry>();
		int hash = set.getHashIndex(str);
		HashEntry temp = set.table[hash];
		while(temp!=null)
		{
			if (temp.key.equalsIgnoreCase(str))
			{
				WordEntry temp1 = temp.value;
				Node<Position> ptr = temp1.positionList.start;
				while (ptr!=null)
				{
					ans.Insert(ptr.getData().getPageEntry());
					ptr = ptr.getLink();
				}
				temp = temp.next;
			}
			else temp = temp.next;
		}
		return ans;
	}
	
	
	
	

}
