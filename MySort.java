import java.util.*;
public class MySort<Sortable extends Comparable<Sortable>>
{

	public void sort(ArrayList<Sortable> arr) 
	{ 
			int n = arr.size(); 

			// One by one move boundary of unsorted subarray 
			for (int i = 0; i < n-1; i++) 
			{ 
					// Find the minimum element in unsorted array 
					int min_idx = i; 
					for (int j = i+1; j < n; j++) 
							if (arr.get(j).compareTo(arr.get(min_idx))>0) 
									min_idx = j; 

					// Swap the found minimum element with the first 
					// element 
					Sortable temp = arr.get(min_idx); 
					arr.set(min_idx, arr.get(i)); 
					arr.set(i, temp); 
			} 
	} 
	 ArrayList<Sortable> sortThisList(Myset<Sortable> listOfSortableEntries)
	 {
	 	ArrayList<Sortable> listOfEntries = listOfSortableEntries.convertToArrayList();
	 	sort(listOfEntries);
	 	return listOfEntries;
	 
	 }
//	 
//	 public static void main (String args[])
//	 {
//		 
//	 }
	
}	
	// 
  //   public int partition(ArrayList<Sortable> arr, int low, int high)
  //   {
  //   	Sortable pivot = arr.get(high);
  //   	int i = (low - 1);
  //   	for (int j = low; j<high;j++)
  //   	{
  //   		if ((arr.get(j)).compareTo(pivot) <= 0 )
  //   		{
  //   			i++;
  //   			Sortable temp = arr.get(i);
  //   			arr.set(i, arr.get(j));
  //   			arr.set(j, temp);
  //   		}
  //   	}
	// 
  //   	Sortable temp = arr.get(i+1);
  //   	arr.set(i+1, arr.get(high));
  //   	arr.set(high, temp);
  //   	return i+1;
  //   }
	// 
  //   public void sorthelper(ArrayList<Sortable> arr, int low, int high)
  //   {
  //   	if (low < high)
  //   	{
  //   		int pi = partition(arr, low, high);
  //   		sorthelper(arr, low, pi - 1);
  //   		sorthelper(arr, pi+1, high);
  //   	}
  //   }
	// 
	// 


