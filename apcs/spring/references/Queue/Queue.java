public interface Queue<E>{
    
    public void enqueue(E element);
    public E dequeue() throws EmptyQueueException;
    public E front() throws EmptyQueueException;
    public int size();
    public boolean isEmpty();
}
