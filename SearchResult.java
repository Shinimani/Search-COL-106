
public class SearchResult implements Comparable<SearchResult>{
	PageEntry p;
	float r;
	SearchResult(PageEntry p, float r)
	{
		this.p = p;
		this.r = r;
	}
	
	public PageEntry getPageEntry()
	{
		return this.p;
	}
	
	public float getRelevance()
	{
		return this.r;
	}
	
	public int compareTo(SearchResult other)
	{
		if (this.r > other.r)
		{
			return 1;
		}
		else if (this.r < other.r)
		{
			return -1;
		}
		else return 0;
	}

}
