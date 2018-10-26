import java.util.ArrayList;

public class InvertedPageIndex {
	
	public MyHashTable set;
	public int TotalPages;

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
		this.TotalPages++;
		
	}
	
	public int getTotalPages()
	{
		return this.TotalPages;
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
	
	
//	
//	public int countOfPhraseInPage(String[] str, PageEntry page)
//	{
//		int ans = 0;
//		WordEntry firstWord = this.set.findWord(str[0]);
//		if (str.length == 1)
//		{
//			ans = firstWord.getCountInPage(page.name);
//		}
//		else
//		{
//			Node<Position> ptr = firstWord.positionList.start;
//			while (ptr != null)
//			
//			{
//				if(ptr.getData().getPageEntry().name.equals(page.name))
//				{
////					int i = 1;
//					boolean flag = false;
//					for (int i = 1; i < str.length - 1;i++)
//					{
//						WordEntry tempWord = this.set.findWord(str[i]);
//						
//						AVLNode tempAVLNode = tempWord.avlTree.searchWC(ptr.getData().getWCIndex() + i);
//						//if we can't find that WCIndex, we move on to the next position of the first word
//						if (!tempWord.avlTree.searchWCboolean(ptr.getData().getWCIndex() + i))
//						{
//							break;
//						}
//						else
//						{
//							//if the page entry is also same, then we move to the next word of the phrase
//							if (tempAVLNode.getData().getPageEntry().name == ptr.getData().getPageEntry().name)
//							{
////								i++;
//								if (i==str.length-1)
//								{
//									flag = true;
//								}
//								continue;
//							}
//							//if the page entry is not the same, we move to the node left of given node as there 
//							//can be multiple entries of same WCIndex in different pages
//							else 
//							{
//								if (tempAVLNode.left.getData().getWCIndex()==(ptr.getData().getWCIndex() + i))
//								{
//									boolean checker = false;
//									while(tempAVLNode.left.getData().getWCIndex()==(ptr.getData().getWCIndex() + i))
//									{
//										tempAVLNode = tempAVLNode.left;
//										if (tempAVLNode.getData().getPageEntry().name == ptr.getData().getPageEntry().name)
//										{
////											i++;
//											if (i==str.length-1)
//											{
//												flag = true;
//											}
//											checker = true;
//											break;
//										}
//										else
//										{
//											continue;
//										}
//									}
//									if (!checker)
//									{
//										break;
//									}
//									else continue;
//								}
//								else break;
//									
//							}
//	
//						}
//					}
//					//if we came to the end of the phrase, then the page has the phrase, so we add the page entry
//					if (flag)
//					{
//						ans++;
//						ptr = ptr.getLink();
//						continue;
//				}
////				//else we move to the next position of the first word
//				else 
//					{
//						ptr = ptr.getLink();
//						continue;
//					}
//					
//					
//					
//				}
//			}
//		}
//		return ans;
//	}
//	
	
	public int countOfPhraseInPage(String[] str, PageEntry page)
	{
		int ans = 0;
		WordEntry firstWord = this.set.findWord(str[0]);
		Node<Position> firstWordPosition = firstWord.positionList.start;
		while(firstWordPosition != null)
		{
			if (!firstWordPosition.getData().getPageEntry().name.equals(page.name))
			{
				firstWordPosition = firstWordPosition.getLink();
			}
			else
			{
				boolean flag = false;
				for (int i = 0;i<str.length;i++)
				{
					WordEntry tempWord = this.set.findWord(str[i]);
					AVLNode tempAVL = tempWord.avlTree.searchWC(firstWordPosition.getData().getWCIndex() + i);
					if (tempAVL!=null)
					{
						if (tempAVL.getData().getPageEntry().name.equals(firstWordPosition.getData().getPageEntry().name))
						{
							if (i == str.length - 1)
							flag = true;
						}
						else while(tempAVL!=null && tempAVL.getData().getWCIndex()==firstWordPosition.getData().getWCIndex() + i)
						{
							if (tempAVL.getData().getPageEntry().name.equals(firstWordPosition.getData().getPageEntry().name))
							{
								if (i == str.length - 1)
								flag = true;
							} else tempAVL = tempAVL.left;
						}
					}
					else break;
				}
				if (flag) 
				{
					ans++;
					firstWordPosition = firstWordPosition.getLink();
				}
				else
				{
					firstWordPosition = firstWordPosition.getLink();
				}
			}
		}
		return ans;
	}
	
	public Myset<PageEntry> getPagesWhichContainPhrase(String[] str)
	{
		Myset<PageEntry> ans = new Myset<PageEntry>();
		WordEntry firstword = this.set.findWord(str[0]);
		Node<Position> ptr = firstword.positionList.start;
		while (ptr!=null)
		{
			if (this.countOfPhraseInPage(str, ptr.getData().getPageEntry()) != 0)
			{
				ans.Insert(ptr.getData().getPageEntry());
			}
			ptr = ptr.getLink();
		}
		return ans;
	}
//	public Myset<PageEntry> getPagesWhichContainPhrase(String[] str)
//	{
//		Myset<PageEntry> ans = new Myset<PageEntry>();
//			
//			WordEntry firstword = this.set.findWord(str[0]);
//			Node<Position> ptr = firstword.positionList.start;
//					//ptr is the node of the first position of the first word of the phrase
//					//We will check if the next word of the phrase comes after this position or not
//					//One loop will run on the words of the phrase, and in the loop, we will find 
//					//whether the successive position in the same page is present in the next word or not.
//					int len = str.length;
//					//If length is one, all the pages in the wordentry will come as answer
//					if (len == 1)
//					{
//						while (ptr!=null)
//						{
//							ans.Insert(ptr.getData().getPageEntry());
//							ptr = ptr.getLink();
//						}
//					}
//					else
//					//if the length is not one	
//					{
//						//traversal in each position of the first word
//						while(ptr!=null)
//						{
//							//int i denotes the position of a word in the phrase, i.e. complete traversal of the phrase
//							int i = 1;
//							while (i<len)
//							{
//								//wordentry of the ith word
//								WordEntry tempWord = this.set.findWord(str[i]);
//								//finding whether the ith word has a WithoutConnecting index as WCIndex of the taken position + i
//								//+i because it will be i positions ahead of the first word
//								AVLNode tempAVLNode = tempWord.avlTree.searchWC(ptr.getData().getWCIndex() + i);
//								//if we can't find that WCIndex, we move on to the next position of the first word
//								if (!tempWord.avlTree.searchWCboolean(ptr.getData().getWCIndex() + i))
//								{
////									i++;
//									break;	
//
//								}
//								
//								else
//								{
//									//if the page entry is also same, then we move to the next word of the phrase
//									if (tempAVLNode.getData().getPageEntry().name == ptr.getData().getPageEntry().name)
//									{
//										i++;
//										continue;
//									}
//									//if the page entry is not the same, we move to the node left of given node as there 
//									//can be multiple entries of same WCIndex in different pages
//									else 
//									{
//										if (tempAVLNode.left.getData().getWCIndex()==(ptr.getData().getWCIndex() + i))
//										{
//											boolean checker = false;
//											while(tempAVLNode.left.getData().getWCIndex()==(ptr.getData().getWCIndex() + i))
//											{
//												tempAVLNode = tempAVLNode.left;
//												if (tempAVLNode.getData().getPageEntry().name == ptr.getData().getPageEntry().name)
//												{
//													i++;
//													checker = true;
//													break;
//												}
//												else
//												{
//													i++;
//													continue;
//												}
//											}
//											if (!checker)
//											{
//												break;
//											}
//											else {continue;}
//										}
//										else {i++; break;}
//											
//									}
//
////								else ptr = ptr.get
//								}
//							}
//							//if we came to the end of the phrase, then the page has the phrase, so we add the page entry
//							if (i==len)
//							{
//								ans.Insert(ptr.getData().getPageEntry());
//								ptr = ptr.getLink();
//								continue;
//							}
//							//else we move to the next position of the first word
//							else 
//								{
//									ptr = ptr.getLink();
////									continue;
//								}
//						}
//					}
//					return ans;
//				}
//
	
	public Myset<PageEntry> getPagesAND(String[] str)
	{
		Myset<PageEntry> ans = new Myset<PageEntry>();
		ans = this.getPagesWhichContainWord(str[0]);
		if (str.length>1)
		{
			for(int i =0;i<str.length;i++)
			{
				if (i==0)
				{
					ans = this.getPagesWhichContainWord(str[0]);
				}
				else
				{
					ans = ans.Intersection(this.getPagesWhichContainWord(str[i]));
				}
			}
		}
		return ans;
	}
	
	public Myset<PageEntry> getPagesOR(String[] str)
	{
		Myset<PageEntry> ans = new Myset<PageEntry>();
		for(int i =0;i<str.length;i++)
		{
			if (i==0)
			{
				ans = this.getPagesWhichContainWord(str[0]);
			}
			else
			{
				ans = ans.Union(this.getPagesWhichContainWord(str[i]));
			}
		}
		return ans;
	}
	
	
	public ArrayList<SearchResult> sortedListOR(Myset<PageEntry> pageSet, String[] str)
	{
		Myset<SearchResult> searchSet = new Myset<SearchResult>();
		Node<PageEntry> ptr = pageSet.ll.start;
		while (ptr!=null)
		{
			float rel = ptr.getData().getRelevanceOR(str);
			SearchResult temp = new SearchResult(ptr.getData(),rel);
			searchSet.Insert(temp);
			ptr = ptr.getLink();
		}
		MySort s = new MySort();
		return s.sortThisList(searchSet);
	}
	
	public ArrayList<SearchResult> sortedListAND(Myset<PageEntry> pageSet, String[] str)
	{
		Myset<SearchResult> searchSet = new Myset<SearchResult>();
		Node<PageEntry> ptr = pageSet.ll.start;
		while (ptr!=null)
		{
			float rel = ptr.getData().getRelevanceAND(str);
			SearchResult temp = new SearchResult(ptr.getData(),rel);
			searchSet.Insert(temp);
			ptr = ptr.getLink();
		}
		MySort s = new MySort();
		return s.sortThisList(searchSet);
	}
	
	public ArrayList<SearchResult> sortedListPhrase(Myset<PageEntry> pageSet, String[] str)
	{
		Myset<SearchResult> searchSet = new Myset<SearchResult>();
		Node<PageEntry> ptr = pageSet.ll.start;
		while (ptr!=null)
		{
			float rel = ptr.getData().getRelavancePhrase(str);
			SearchResult temp = new SearchResult(ptr.getData(),rel);
			searchSet.Insert(temp);
			ptr = ptr.getLink();
		}
		MySort s = new MySort();
		return s.sortThisList(searchSet);
	}
	
	
	
	
	
	

}
