import java.util.PriorityQueue;
import java.util.Comparator;

public class Demo{


    public static void main(String [] args){
	String [] fruits = {"apple","banana","watermelon", "pear", "grapes", "cantalope", "orange", "kiwi"};

	PriorityQueue<String> pq = new PriorityQueue<String>();

	Comparator<String> comp = new ReverseComparator<String>();
	PriorityQueue<String> pq2 = new PriorityQueue<String>(comp);
	
	System.out.println("***** adding *****");
	for (String fruit: fruits){
	    pq.add(fruit);
	    pq2.add(fruit);
	    System.out.println("add: " + fruit);
	    System.out.println("\tmin pq: " + pq);
	    System.out.println("\tmax pq: " + pq2);
	}

	
	System.out.println("***** removing *****");	
	while(pq2.size() != 0){
	    System.out.print("remove min: " + pq.poll());
	    System.out.println("\t min pq : " + pq);
	    System.out.print("remove max: " + pq2.poll());
	    System.out.println("\t max pq : " + pq2);
	}
    }
}
