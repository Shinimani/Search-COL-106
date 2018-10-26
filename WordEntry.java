
public class WordEntry {
	
	public String word;
	public MyLinkedList<Position> positionList;
	public AVLTree avlTree;
	
	WordEntry(String word)
	{
		this.word = word;
		this.positionList = new MyLinkedList<Position>();
		this.avlTree = new AVLTree();
	}
	
	public void addPosition(Position position)
	{
		this.positionList.Insert(position);
		this.avlTree.insert(position);
	}
	
	public void addPositions(MyLinkedList<Position> positions)
	{
		Node<Position> ptr = positions.start;
		while (ptr!=null)
		{
			if (!this.positionList.IsMember(ptr.getData()))
			{
				addPosition(ptr.getData());
				ptr=ptr.getLink();			
			}
			else ptr=ptr.getLink();
		}
	}
	
	public String getWord()
	{
		return this.word;
	}
	
	public MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return this.positionList;
	}
	
	//to make it
	public int getCountInPage(String pageName)
	{
		Node<Position> ptr = this.positionList.start;
		int ans = 0;
		while (ptr!=null)
		{
			if (ptr.getData().getPageEntry().name.equals(pageName)) ans++;
			ptr = ptr.getLink();
		}
		return ans;
	}

	//	public float getTermFrequency(String word)
//	{
//		float fw = this.getCountInPage(word);
//		Node<Position> ptr = this.positionList.start;
//		PageEntry pe = new PageEntry(word);
//		float wp = pe.TotalWords;
//		return (fw/wp);
//	}
	
	
	
	

}
