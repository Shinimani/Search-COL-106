
public class MyChecker{
	public static void main ( String args [])
	{
		SearchEngine r = new SearchEngine();

		r.performAction("addPage stacklighting");
		r.performAction("addPage stackmagazine");
		r.performAction("addPage stack_cprogramming");
		r.performAction("addPage stack_datastructure_wiki");
		r.performAction("addPage stack_oracle");
		r.performAction("queryFindPagesWhichContainWord stack");
		r.performAction("queryFindPositionsOfWordInAPage stack stack_datastructure_wiki");
		System.out.println(r.ipi.set.table[r.ipi.set.getHashIndex("stack")].next.next.value.word);
		r.ipi.set.table[r.ipi.set.getHashIndex("stack")].next.next.value.avlTree.inorder();
		System.out.println();
		r.performAction("queryFindPagesWhichContainPhrase meet stack first");
		System.out.println(r.ipi.set.findWord("stack").getCountInPage("stackmagazine"));




	}

}
