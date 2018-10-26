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
				String outp = "yoyoyoyo";
				while (temp!=null)
				{
					outp = outp + temp.getData().name + ", ";
					temp = temp.getLink();
				}
				if (!outp.equals("yoyoyoyo"))
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
				String[] arr = Arrays.copyOfRange(str, 0, i-1);
				
				
				//yaha se aage ka dekh lena 
				Myset<PageEntry> PageSet = ipi.getPagesWhichContainPhrase(arr);
				Node<PageEntry> temp = PageSet.start();
				String outp = "yoyoyoyo";
				while (temp!=null)
				{
					outp = outp + temp.getData().name + ", ";
					temp = temp.getLink();
				}
				if (!outp.equals("yoyoyoyo"))
				{
					outp = outp.substring(0, (outp.length() - 2));
				} else outp = "not found";
				System.out.println(outp);
			}
			

		}

}
