
public class PageIndex {
	
	public MyLinkedList<WordEntry> wordList;
	
	PageIndex()
	{
		this.wordList = new MyLinkedList<WordEntry>();
	}
	
	PageIndex(MyLinkedList<WordEntry> wordList)
	{
		this.wordList = wordList;
	}
	
	public void addPositionForWord(String str, Position p)
	{
		Node<WordEntry> ptr = this.wordList.start;
		while (ptr!=null)
		{
			if (ptr.getData().getWord().equalsIgnoreCase(str))
			{
				ptr.getData().addPosition(p);
				return;
			}
			else ptr=ptr.getLink();
		}
		WordEntry newword = new WordEntry(str);
		newword.addPosition(p);
		this.wordList.Insert(newword);
		
	}
	
	public MyLinkedList<WordEntry> getWordEntries()
	{
		return this.wordList;
	}
	
	
	
	
	
	

}
