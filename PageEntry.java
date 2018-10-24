import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

public class PageEntry {
	
	public PageIndex pageindex;
	public String name;
	
	PageEntry(String pageName)
	{
		this.pageindex = new PageIndex();
		this.name = pageName;
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
	    		  Position p = new Position(this,c);
	    		  this.pageindex.addPositionForWord(x, p);
	    	  }
	      }

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
	

}
