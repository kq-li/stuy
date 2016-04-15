public interface Deque<E>{
    
   
    public int size();
    public boolean isEmpty();
 
    
    public E getFirst() throws EmptyDequeException;
    public E getLast() throws EmptyDequeException;
    
    public void addFirst(E val);
    public void addLast(E val);
    
   
    public E removeFirst() throws EmptyDequeException;
    public E removeLast()  throws EmptyDequeException;

    public boolean offerFirst(E val);
    public boolean offerLast(E val);
    
    public E pollFirst();
    public E pollLast();
    
    public E peekFirst();
    public E peekLast();

    
}
