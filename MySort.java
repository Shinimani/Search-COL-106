import java.util.*;
public class MySort<Sortable extends Comparable<Sortable>>
{
	
	

    public int partition(ArrayList<Sortable> arr, int low, int high)
    {
    	Sortable pivot = arr.get(high);
    	int i = (low - 1);
    	for (int j = low; j<high;j++)
    	{
    		if ((arr.get(j)).compareTo(pivot) <= 0 )
    		{
    			i++;
    			Sortable temp = arr.get(i);
    			arr.set(i, arr.get(j));
    			arr.set(j, temp);
    		}
    	}
    	
    	Sortable temp = arr.get(i+1);
    	arr.set(i+1, arr.get(high));
    	arr.set(high, temp);
    	return i+1;
    }
    
    public void sorthelper(ArrayList<Sortable> arr, int low, int high)
    {
    	if (low < high)
    	{
    		int pi = partition(arr, low, high);
    		sorthelper(arr, low, pi - 1);
    		sorthelper(arr, pi+1, high);
    	}
    }
    
	
	ArrayList<Sortable> sortThisList(Myset<Sortable> listOfSortableEntries)
	{
		ArrayList<Sortable> listOfEntries = listOfSortableEntries.convertToArrayList();
		sorthelper(listOfEntries, 0, listOfSortableEntries.size()-1);
		return listOfEntries;
				
	}
}
