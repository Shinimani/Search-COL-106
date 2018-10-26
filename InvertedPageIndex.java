
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
	
	public Myset<PageEntry> getPagesWhichContainPhrase(String[] str)
	{
		Myset<PageEntry> ans = new Myset<PageEntry>();
		
			int hash = set.getHashIndex(str[0]);
			HashEntry tempHashEntry = set.table[hash];
			while(tempHashEntry!=null)
			{
				if (tempHashEntry.key.equalsIgnoreCase(str[0]))
				{
					WordEntry temp1 = tempHashEntry.value;
					Node<Position> ptr = temp1.positionList.start;
					//ptr is the node of the first position of the first word of the phrase
					//We will check if the next word of the phrase comes after this position or not
					//One loop will run on the words of the phrase, and in the loop, we will find 
					//whether the successive position in the same page is present in the next word or not.
					int len = str.length;
					//If length is one, all the pages in the wordentry will come as answer
					if (len == 1)
					{
						while (ptr!=null)
						{
							ans.Insert(ptr.getData().getPageEntry());
							ptr = ptr.getLink();
						}
						break;
					}
					else
					//if the length is not one	
					{
						//traversal in each position of the first word
						while(ptr!=null)
						{
							//int i denotes the position of a word in the phrase, i.e. complete traversal of the phrase
							int i = 1;
							while (i<len)
							{
								//wordentry of the ith word
								WordEntry tempWord = this.set.findWord(str[i]);
								//finding whether the ith word has a WithoutConnecting index as WCIndex of the taken position + i
								//+i because it will be i positions ahead of the first word
								AVLNode tempAVLNode = tempWord.avlTree.searchWC(ptr.getData().getWCIndex() + i);
								//if we can't find that WCIndex, we move on to the next position of the first word
								if (tempAVLNode.getData() == null)
								{
									break;
		//							i++;
								}
								
								else
								{
									//if the page entry is also same, then we move to the next word of the phrase
									if (tempAVLNode.getData().getPageEntry().name == ptr.getData().getPageEntry().name)
									{
										i++;
										continue;
									}
									//if the page entry is not the same, we move to the node left of given node as there 
									//can be multiple entries of same WCIndex in different pages
									else 
									{
										if (tempAVLNode.left.getData().getWCIndex()==(ptr.getData().getWCIndex() + i))
										{
											boolean checker = false;
											while(tempAVLNode.left.getData().getWCIndex()==(ptr.getData().getWCIndex() + i))
											{
												tempAVLNode = tempAVLNode.left;
												if (tempAVLNode.getData().getPageEntry().name == ptr.getData().getPageEntry().name)
												{
													i++;
													checker = true;
													break;
												}
												else
												{
													continue;
												}
											}
											if (!checker)
											{
												break;
											}
											else continue;
										}
										else break;
											
									}

//								else ptr = ptr.get
								}
							}
							//if we came to the end of the phrase, then the page has the phrase, so we add the page entry
							if (i==len)
							{
								ans.Insert(ptr.getData().getPageEntry());
								ptr = ptr.getLink();
								continue;
							}
							//else we move to the next position of the first word
							else 
								{
									ptr = ptr.getLink();
									continue;
								}
						}
						break;
					}
				}
				else tempHashEntry = tempHashEntry.next;
			}
			
			
//			int len = str.length;
//			if (len != 1)
//			{
//				for(int i = 1;i<len;i++)
//				{
//					hash = set.getHashIndex(str[i]);
//					temp = set.table[hash];
//					while (temp!=null)
//					{
//						if (temp.key.equalsIgnoreCase(str[0]))
//						{
//							WordEntry temp1 = temp.value;
//							Node<Position> ptr = temp1.positionList.start;
//							while (ptr!=null)
//							{
//								ans.Insert(ptr.getData().getPageEntry());
//								ptr = ptr.getLink();
//							}
//							break;
//							
//						}
//						else temp = temp.next;
//						
//					}
//				}
//			}
			
			
		

		return ans;
	}
	
	
	
	

}
