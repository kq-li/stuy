public class Sorts{


    public static void shuffle(Object [] x){
	int N = x.length;
	for(int i = 0; i < N; i++){
	    int r = (int)(Math.random() * (N-i)) + i;
	    Object t = x[i];
	    x[i] = x[r];
	    x[r] = t;
	}
    }
    
    

    // precondition:  x != null
    // postcondition: returns true if x is empty or is sorted
    //                in ascending order; false otherwise.
    // isSorted({1,2,2,3,4}) -> true
    // isSorted({"a","c","b"}) -> false
    // isSorted({0.2, 1.4, 1.0}) -> false
    public static boolean isSorted(Comparable [] x){
	for (int i = 0; i < x.length - 1; i++){
	    if (x[i].compareTo(x[i+1]) > 0) 
		return false;
	}
	return true;
    }
    // precondition : x != null
    // postcondtion : sorts the array in ascending order by
    //                shuffling the array until it is sorted.
    //               bogoSort({1,5,3}) -> {1,3,5}

    public static int bogoSort(Comparable [] x){
	int n = 0;
        while (!isSorted(x)) {
	    shuffle(x);
	    n++;
	}
	return n;
    }

    public static void bubbleSort(Comparable [] x){
	int N = x.length;
	for (int pass = 1; pass < N; pass++){
	    for (int i = 0; i < N - pass; i++){
		if (x[i].compareTo(x[i+1]) > 0){
		    Comparable t = x[i];
		    x[i] = x[i+1];
		    x[i+1] = t;
		}
	    }
	}
    }
    // postcondition: x is sorted in ascending order (side effect)
    //               returns which pass the function exits on 
    //               returns n if doesn't exit early
    public static int bubbleSortModified(Comparable [] x){
	int N = x.length;
	for (int pass = 1; pass < N; pass++){
	    int swaps = 0;
	    for (int i = 0; i < N - pass; i++){
		if (x[i].compareTo(x[i+1]) > 0){
		    Comparable t = x[i];
		    x[i] = x[i+1];
		    x[i+1] = t;
		    swaps++;
		}
	    }
	    if (swaps == 0) return pass;
	}
	return N;
    }
    // postcondition: sorts the data in ascending order
    //               
    public static void selectionSort(Comparable [] x){
	int N = x.length;
	for(int i = 0; i < N ; i++){
	    int minPos = i;
	    for (int j = i + 1; j < N; j++){
		if (x[minPos].compareTo(x[j]) > 0)
		    minPos = j;
	    }
	    // swap 
	    Comparable t = x[i];
	    x[i] = x[minPos];
	    x[minPos] = t;
	}
    }


    public static void insertionSort(Comparable [] x){
	int N = x.length;
	for (int i = 1; i < N; i++){
	    for (int j = i; j > 0 ; j--){
		if (x[j].compareTo(x[j-1]) < 0){
		    Comparable t = x[j];
		    x[j] = x[j-1];
		    x[j-1] = t;
		}
		else break;
	    }
	}
    }


    public static void main(String [] args){
	Integer [] x = ArrayIO.intArray(10); //{7,8,4,2,1};
	shuffle(x);
	ArrayIO.printArray(x);
	//System.out.println(bubbleSortModified(x));
	//selectionSort(x);
	insertionSort(x);
	ArrayIO.printArray(x);
	
    }
}
