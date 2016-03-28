public class ArrayStack<E> implements Stack<E> {
  private E[] _stack;
  private int _capacity;
  private int _top;

  public static final int CAPACITY = 1000;

  public ArrayStack(int capacity) {
    _stack = (E[]) new Object[capacity];
    _capacity = capacity;
    _top = -1;
  }

  public void push(E element) throws FullStackException {
    if (size() == _capacity) 
      throw new FullStackException();
    
    _stack[++_top] = element;
  }
  
  public E pop() throws EmptyStackException {
    if (isEmpty())
      throw new EmptyStackException();

    E ret = top();
    _stack[_top--] = null;
    return ret;
  }

  public E top() throws EmptyStackException {
    if (isEmpty())
      throw new EmptyStackException();
    
    return _stack[_top];
  }

  public int size() {
    return _top + 1;
  }

  public boolean isEmpty() {
    return (size() == 0);
  }

  public String toString() {
    String ret = "";

    if (isEmpty())
      return ret;

    for (int i = _top; i >= 0; i--)
      ret += _stack[i] + "\n";
    
    return ret.substring(0, ret.length() - 1);
  }  
  
  public static void main(String[] args) {
    ArrayStack<Integer> S = new ArrayStack<Integer>(ArrayStack.CAPACITY);

    for (int i = 0; i < 20; i++) {
      try {
        S.push(i);
      } catch (FullStackException e) {
        e.printStackTrace();
      }
    }
    
    System.out.println(S);
  }
}
