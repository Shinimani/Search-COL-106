
public class Position {
	
	private PageEntry pe;
	private int wi;
	private int indexWithoutConnector;
	
	Position(PageEntry p, int wordIndex, int indexWC)
	{
		this.pe = p;
		this.wi=wordIndex;
		this.indexWithoutConnector = indexWC;
	}
	
	public PageEntry getPageEntry()
	{
		return pe;
	}
	
	public int getWordIndex()
	{
		return wi;
	}


	public int getWCIndex()
	{
		return indexWithoutConnector;
	}

}
