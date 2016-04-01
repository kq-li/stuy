public interface Queue<E> {
  // inserts element e at rear of queue
  void enqueue(E e); 

  // removes and returns element at front of queue
  E dequeue() throws EmptyQueueException; 

  // returns element at front of queue
  E front() throws EmptyQueueException; 

  // returns the length of the queue
  int size();

  // returns true if queue is empty, otherwise false
  boolean isEmpty();
}
