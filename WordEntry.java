
public class WordEntry {
	
	public String word;
	public MyLinkedList<Position> positionList;
	
	WordEntry(String word)
	{
		this.word = word;
		this.positionList = new MyLinkedList<Position>();
	}
	
	public void addPosition(Position position)
	{
		this.positionList.Insert(position);
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
	public float getTermFrequency(String word)
	{
		return 0;
	}
	
	
	
	

}
