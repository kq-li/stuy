import java.util.List;
import java.util.ArrayList;

public class ArrayListStack<E> implements Stack<E> {
  private ArrayList<E> _stack;

  public ArrayListStack() {
    _stack = new ArrayList<E>();
  }
  
  public void push(E element) {
    _stack.add(element);
  }

  public E pop() {
    if (isEmpty())
      throw new EmptyStackException("Stack empty");

    return _stack.remove(_stack.size() - 1);
  }

  public E top() {
    if (isEmpty())
      throw new EmptyStackException("Stack empty");

    return _stack.get(_stack.size() - 1);
  }

  public int size() {
    return _stack.size();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public String toString() {
    String ret = "";

    if (isEmpty())
      return ret;
    
    for (E e : _stack)
      ret += e.toString() + "\n";

    return ret.substring(0, ret.length() - 1);
  }

  public static void main(String[] args) {
    Stack<Integer> S = new ArrayListStack<Integer>();
    
    for (int i = 0; i < 20; i++)
      S.push(i);

    System.out.println(S);
  }
}
