import java.util.PriorityQueue;

public class Demo {

  //O(NlogN)
  public static void pqSort(String[] list) { 
    PriorityQueue<String> temp = new PriorityQueue<String>();
    for (String x: list)
	    temp.add(x);
    for (int i = 0; i < list.length; i++) 
	    String[i] = temp.poll();

  }

  public static void main(String[] args) {
    String[] fruits = {"kiwi","pear","apple","banana","watermelon",
                       "grapes","cantalope","orange"};
    PriorityQueue<String> pq = new PriorityQueue<String>();

    for (String fruit: fruits)
	    pq.add(fruit);

    while (pq.size() != 0) {
	    System.out.println("peek: " + pq.peek());
	    System.out.println("remove: " + pq.poll());
    }
  }
}
