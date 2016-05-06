import java.util.List;
import java.util.ArrayList;

public class InStack{

    // generic method
    // O(N) if L is an ArrayList
    // O(N*N)  if L is a LinkedList
    public static<E> void reverse(List<E> L){
	Stack<E> stack = new NodeStack<E>();
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

    public static <E> boolean inStack(Stack<E> stack, E value){
	boolean ans = false;
	Stack<E> aux = new NodeStack<E>();
	while (!stack.isEmpty()){
	    if (value.equals(stack.top())){
		ans = true;
		break;
	    }
	    aux.push(stack.pop());
	}
	while(!aux.isEmpty())
	    stack.push(aux.pop());
	return ans;
    }

    public static void main(String [] args){
	Stack<Integer> s = new NodeStack<Integer>();
	for (int i = 0; i < 10; i++)
	   s.push(i);
	System.out.println("Testing inStack()");
	System.out.println("stack : " + s);
	
	int [] values = {-1,2,3,10};
	for (int x: values){
	    System.out.println(x + " in " + s + " : " + inStack(s,x));
	}
       
	
	ArrayList<String> L = new ArrayList<String>();
	String letters  = "abcdefg";
	for (int i = 0; i < letters.length(); i++)
	    L.add(letters.substring(i,i+1));
	System.out.println("Testing reverse() " );
	System.out.println("list : " + L);
	reverse(L);
       	System.out.println("list : " + L);
	
    }
}
