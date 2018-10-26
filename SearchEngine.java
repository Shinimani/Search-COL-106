import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SearchEngine {

	public InvertedPageIndex ipi;

	SearchEngine()
	{
		ipi = new InvertedPageIndex();
	}

	public void performAction(String actionMessage)
	{
		Scanner s = new Scanner(actionMessage);
		String x = s.next();
		if (x.equals("addPage"))
			{
				String y = s.next();
				PageEntry p = new PageEntry(y, ipi);
				ipi.addPage(p);
			}
			else if (x.equals("queryFindPagesWhichContainWord"))
			{
				String y = s.next();
				Myset<PageEntry> PageSet = ipi.getPagesWhichContainWord(y);
				Node<PageEntry> temp = PageSet.start();
				String outp = "";
				while (temp!=null)
				{
					outp = outp + temp.getData().name+"-" +" length "+temp.getData().TotalWords+ temp.getData().getRelevanceWord(y) +"tf-"+ temp.getData().getTermFrequency(y)+" idf-"+ temp.getData().idfWord(y) + ", ";
					temp = temp.getLink();
				}
				if (!outp.equals(""))
				{
					outp = outp.substring(0, (outp.length() - 2));
				} else outp = "not found";
				System.out.println(outp);
			}
			else if (x.equals("queryFindPositionsOfWordInAPage"))
			{
				String y = s.next();
				String z = s.next();
				MyHashTable hasht = ipi.set;
				int hash = hasht.getHashIndex(y);
				HashEntry hashe = hasht.table[hash];
				String outp = "";
				while (hashe!=null)
				{
					if (hashe.value.word.equalsIgnoreCase(y))
					{
						WordEntry WordE = hashe.value;
						Node<Position> temp = WordE.positionList.start;
						while (temp!=null)
						{
							if (temp.getData().getPageEntry().name.equals(z))
								{
									outp = outp + temp.getData().getWordIndex() + ", ";
									temp = temp.getLink();
								}
							else temp = temp.getLink();
						}
						hashe = hashe.next;
					}
					else hashe = hashe.next;
				}
				if (!outp.equals(""))
				{
					outp = outp.substring(0, (outp.length() - 2));
				} else outp = "not found";
				System.out.println(outp);


			}
		
//			else if (x.equals("queryFindPagesWhichContainPhrase"))
//			{
//				String[] str = new String[100];
//				int i = 0;
//				while (s.hasNext())
//				{
//					str[i] = s.next();
//					i++;
//				}
//				String[] arr = Arrays.copyOfRange(str, 0, i-1);
//				
//				Myset<PageEntry> ans = ipi.getPagesWhichContainWord(arr[0]);
//			}	
//queryFindPagesWhichContainAllWords
			else if (x.equals("queryFindPagesWhichContainPhrase"))
			{
				String[] str = new String[100];
				int i = 0;
				while (s.hasNext())
				{
					str[i] = s.next();
					i++;
				}
				String[] arr;
				if(i!=1)
				{
				arr = Arrays.copyOfRange(str, 0, i-1);
				}
				else 
				{
				arr = new String[1];
				arr[0] = str[0];
				}
				
				ArrayList<SearchResult> ansList = this.ipi.sortedListPhrase(this.ipi.getPagesWhichContainPhrase(arr), arr);
				String ans = "";
				for (int k = 0; k<ansList.size();k++)
				{
					if (k==0) ans = ansList.get(k).getPageEntry().name;
//						ans = ansList.get(k).getPageEntry().name+ "," + ansList.get(k).getRelevance() + "," + ansList.get(k).getPageEntry().Phrasetf(arr);
					else ans = ans + " -- " + ansList.get(k).getPageEntry().name;
//					ans = ans + " -- " + ansList.get(k).getPageEntry().name + "," + ansList.get(k).getRelevance()+ "," + ansList.get(k).getPageEntry().Phrasetf(arr);
				}
				System.out.println(ans);
			}
		
			else if (x.equals("queryFindPagesWhichContainAllWords"))
			{
				String[] str = new String[50];
				int i = 0;
				while (s.hasNext())
				{
					str[i] = s.next();
					i++;
				}
				String[] arr;
				if(i!=1)
				{
				arr = Arrays.copyOfRange(str, 0, i-1);
				}
				else 
				{
				arr = new String[1];
				arr[0] = str[0];
				}
			
				ArrayList<SearchResult> ansList = this.ipi.sortedListAND(this.ipi.getPagesAND(arr), arr);
				for (int k = 0; k<ansList.size();k++)
				{
					if (ansList.get(k).getPageEntry().getRelevanceAND(arr)==0)
					{
						ansList.remove(k);
					}
				}
				
				String ans = "";
				for (int k = 0; k<ansList.size();k++)
				{
					if (k==0) ans = ansList.get(k).getPageEntry().name;
//						ans = ansList.get(k).getPageEntry().name+ "," + ansList.get(k).getRelevance() + "," + ansList.get(k).getPageEntry().getRelevanceAND(arr);
					else ans = ans + " -- " + ansList.get(k).getPageEntry().name ;
						
//					ans = ans + " -- " + ansList.get(k).getPageEntry().name + "," + ansList.get(k).getRelevance()+ "," + ansList.get(k).getPageEntry().Phrasetf(arr);
				}
				System.out.println(ans);
			}
			else if (x.equals("queryFindPagesWhichContainAnyOfTheseWords"))
			{
				String[] str = new String[50];
				int i = 0;
				while (s.hasNext())
				{
					str[i] = s.next();
					i++;
				}
				String[] arr;
				if(i!=1)
				{
				arr = Arrays.copyOfRange(str, 0, i-1);
				}
				else 
				{
				arr = new String[1];
				arr[0] = str[0];
				}
			
				ArrayList<SearchResult> ansList = this.ipi.sortedListOR(this.ipi.getPagesOR(arr), arr);
		
				
				String ans = "";
				for (int k = 0; k<ansList.size();k++)
				{
					if (k==0)
						ans = ansList.get(k).getPageEntry().name;
//						ans = ansList.get(k).getPageEntry().name+ "," + ansList.get(k).getRelevance() + "," + ansList.get(k).getPageEntry().getRelevanceOR(arr);
					else
						ans = ans + " -- " + ansList.get(k).getPageEntry().name ;
//					ans = ans + " -- " + ansList.get(k).getPageEntry().name + "," + ansList.get(k).getRelevance();
				}
				System.out.println(ans);
			}
		
		
			

		}

}
