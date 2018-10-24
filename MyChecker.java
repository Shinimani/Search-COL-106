import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyChecker{
	public static void main ( String args [])
	{
		SearchEngine r = new SearchEngine();
		r.performAction("addPage stack_datastructure_wiki");
		r.performAction("queryFindPagesWhichContainWord stack");
		r.performAction("queryFindPositionsOfWordInAPage stack stack_datastructure_wiki");



	}

}
