import java.util.ArrayList;



public class InsertionSort{

    public static void shuffle(ArrayList<String> list){
	int N = list.size();
	for (int i = 0; i < N ; i++){
	    int j = (int) (Math.random() * (N - i)) + i;
	    list.set(i,list.set(j,list.get(i)));
	    //swap(list,i,j);
	}
    }


    public static void swap(ArrayList<String> x, int i, int j){
	String temp = x.get(i);
	x.set(i,x.get(j));
	x.set(j,temp);
    }
    // version 1
    public static void insertionSortV1(ArrayList<String> L){
	for (int i = 1; i < L.size(); i++){
	    int pos = i;
	    String current = L.get(pos);
	    for (int j = pos - 1; j >= 0; j--){
		if (current.compareTo(L.get(j)) < 0){
		    L.set(pos,L.set(j,L.get(pos)));
		    pos--;
		}
		else break;
	    }
	}
    }

    // version 2
    public static void insertionSortV2(ArrayList<String> data){
	int N = data.size();
	for (int i = 1; i < N; i++){
	    System.out.println(" start pass: " + i + " " + data);
	    System.out.println(" walk down " + data.get(i));
	    transfer(data,i);
	}
    }

    public static void transfer(ArrayList <String> data, int i){
	if (i <= 0) return;
	if (data.get(i-1).compareTo(data.get(i)) > 0){
	    data.set(i,data.set(i-1,data.get(i)));
	    System.out.println(data);
	    transfer(data,i-1);
	}
    }



    // version 1:
    public static void insertionSortV1(DLinkedList L){
	int N = L.size();
	DNode pivot = L.getFirst().getNext();
	for (int i = 1; i < N; i++){
	    DNode pivotNext = pivot.getNext();
	    DNode prev = pivot.getPrevious();
	    while (L.hasPrevious(prev) && 
		   pivot.getValue().compareTo(prev.getValue()) < 0){
		prev = prev.getPrevious();
	    }
	    L.remove(pivot);
	    L.addAfter(prev,pivot);
	    //pivot = pivot.getNext(); ** Mistake **
	    pivot = pivotNext;
	} 
    }

    //version 2:
    public static void insertionSortV2(DLinkedList L){
	DNode curr = L.getFirst().getNext();
	while (L.hasNext(curr)){
	    DNode prev = curr.getPrevious();
	    DNode next = curr.getNext();
	    while(L.hasPrevious(prev)){
		if (prev.getValue().compareTo(curr.getValue()) > 0){
		    prev = prev.getPrevious();
		}
		else break;
	    }
	    L.remove(curr);
	    L.addAfter(prev,curr);
	    curr = next;
	}
    }



   
    public static void main(String [] args){
	int N = Integer.parseInt(args[0]);
	ArrayList<String> L = new ArrayList<String> ();
	for (int i = 0; i < N; i++)
	    L.add(i + "");
	shuffle(L);
	System.out.println("Random : " + L);
	DLinkedList DL = new DLinkedList();

	// copy the data from the arraylist into the doubly linked list
	// version 1 : read the data from the arraylist start at index 0.
	//	for (int i = 0; i < L.size() ; i++){
	//        DL.addLast(L.get(i));
	//}
	// version 2: read the data from the arraylist start at the end
	for(int i = 1; i < L.size() + 1; i++){
	    DL.addFirst(L.get(L.size() - i));
	}

        insertionSortV2(DL);
	System.out.println("Random DL: " + DL);
	//       	System.out.println("Sorted : " + L);
    }


}
  
