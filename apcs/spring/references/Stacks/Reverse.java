import java.util.List;
import java.util.ArrayList;

public class Reverse{

    // generic method
    // O(N) if L is an ArrayList
    // O(N*N)  if L is a LinkedList
    public static<E> void reverse(List<E> L){
	Stack<E> stack = new ArrayStack<E>(L.size());
	/*
	  for(int i = 0; i < L.size(); i++)
	  stack.push(L.get(i));// O(1) for an ArrayList
	  // O(N) for a LinkedList
	  */
	for (E val : L)
	    stack.push(val);

	int counter = 0;
	while (!stack.isEmpty()){
	    L.set(counter,stack.pop()); // O(1) for an ArrayList
	    counter++;	                // O(N) for a LinkedList
	}
    }

    public static void main(String [] args){
	ArrayList<Integer> L = new ArrayList<Integer>();
	for (int i = 0; i < 10; i++)
	    L.add(i);
	System.out.println(L);
	reverse(L);
	System.out.println(L);

	ArrayList<String> L2 = new ArrayList<String>();
	String letters  = "abcdefg";
	for (int i = 0; i < letters.length(); i++)
	    L2.add(letters.substring(i,i+1));
	System.out.println(L2);
	reverse(L2);
	System.out.println(L2);
    }
}
