import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

public class PageEntry {
	
	public PageIndex pageindex;
	public String name;
	public int TotalWords;
	public InvertedPageIndex ipi;
	
	PageEntry(String pageName, InvertedPageIndex ipi)
	{
		this.pageindex = new PageIndex();
		this.name = pageName;
		this.ipi = ipi;
		// Scanner s = new Scanner(pageName);
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(pageName));
			String temp = "";
			String temp1;
			while ((temp1 = br.readLine()) != null)
			{
				temp = temp + "\n" + temp1;		
			}
			
	    temp = temp.replaceAll("\\{", " ").toLowerCase();

	      temp = temp.replaceAll("\\}", " ");

	      temp = temp.replaceAll("\\[", " ");

	      temp = temp.replaceAll("\\]", " ");

	      temp = temp.replaceAll("\\<", " ");

	      temp = temp.replaceAll("\\>", " ");

	      temp = temp.replaceAll("=", " ");

	      temp = temp.replaceAll("\\(", " ");

	      temp = temp.replaceAll("\\)", " ");

	      temp = temp.replaceAll("\\.", " ");

	      temp = temp.replaceAll("\\,", " ");

	      temp = temp.replaceAll("\\;", " ");

	      temp = temp.replaceAll("\\'", " ");

	      temp = temp.replaceAll("\"", " ");

	      temp = temp.replace("?", " ");
	      temp = temp.replaceAll("#", " ");
	      temp = temp.replaceAll("!", " ");
	      temp = temp.replaceAll("-", " ");
	      temp = temp.replaceAll(":", " ");
	      temp = temp.replaceAll("stacks", "stack");
	      temp = temp.replaceAll("structures", "structure");
	      temp = temp.replaceAll("applications", "application");
	      
	      Scanner s1 = new Scanner(temp);
	      String x="";
	      int c = 0;
	      int d = 0;
	      // System.out.println("Begin");
	      
	      while (s1.hasNext())
	      {
	    	  x = s1.next();
	    	  if ( x.equals("a") || x.equals("an")|| x.equals("the")||x.equals("they")||x.equals("these")||x.equals("this")||x.equals("for")||x.equals("is")||x.equals("are")||x.equals("was")||x.equals("of")||x.equals("or")||x.equals("and")||x.equals("does")||x.equals("will")||x.equals("whose"))
	    	  {
	    		  c++;
	    	  }
	    	  else
	    	  {
	    		  c++;
	    		  d++;
	    		  Position p = new Position(this,c,d);
	    		  this.pageindex.addPositionForWord(x, p);
	    	  }
	      }
	      
	      this.TotalWords = c;

	      // System.out.println("End");
	      // System.out.println(temp);
	      
	      
	    }
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
	}
	
	public PageIndex getPageIndex()
	{
		return this.pageindex;
	}
	
	public float idfWord(String word)
	{
		float N = this.ipi.getTotalPages();
		float nwp = this.ipi.getPagesWhichContainWord(word).size();
		return (float) Math.log(N/nwp);
	}
	
	public float getTermFrequency(String word)
	{
		WordEntry Word = this.ipi.set.findWord(word);
		int fwp = Word.getCountInPage(this.name);
		return fwp/this.TotalWords;
	}
	
	
	
	

}
