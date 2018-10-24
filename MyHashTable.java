
class HashEntry
{
	WordEntry value;
	String key;
	HashEntry next;
	
	HashEntry(WordEntry value)
	{
		this.value = value;
		this.next = null;
		this.key = value.getWord();
	}
	
}

public class MyHashTable {
	
	public HashEntry[] table;
	public int noOfElements;

	
	public MyHashTable()
	{
		table = new HashEntry[101];
		for (int i = 0;i<101;i++)
		{
			table[i] = null;
		}
		
	}
	
	public void addPositionsForWord(WordEntry w)
	{
		int hash = getHashIndex(w.getWord());
		if (table[hash]==null) 
		{
			table[hash] = new HashEntry (w);
		}
		else
		{
			HashEntry entry = table[hash];
			while (entry.next!=null && !entry.key.equals(w.word))
			{
				entry=entry.next;
			}
			if (entry.key.equals(w.word))
			{
				entry.value.addPositions(w.positionList);
			}
			else 
			{ 
				entry.next = new HashEntry(w);
			}			
		}
	}
	
	
	
	
	
	
	public int getHashIndex(String str)
	{
		int l = str.length();
		int ans = 0;
		for (int i = 0;i<l;i++)
		{
			ans = ans + str.charAt(i);
		}
		ans = ans %101;
		return ans;
	}
	
	
	
	
	
	
	
	

}
