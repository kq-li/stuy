public interface Stack<E> {
  // place an element on top of the stack
  void push(E element) throws FullStackException;

  // remove and return the element on top of the stack
  E pop() throws EmptyStackException;

  // return the element on top of the stack without changing the stack
  E top() throws EmptyStackException;

  // return the size of the stack
  int size();

  // returns true if the stack is empty, otherwise false
  boolean isEmpty();
}
