
public class Position {
	
	private PageEntry pe;
	private int wi;
	
	Position(PageEntry p, int wordIndex)
	{
		this.pe = p;
		this.wi=wordIndex;
	}
	
	public PageEntry getPageEntry()
	{
		return pe;
	}
	
	public int getWordIndex()
	{
		return wi;
	}

}
