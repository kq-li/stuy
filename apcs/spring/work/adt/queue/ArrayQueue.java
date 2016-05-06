public class ArrayQueue<E> implements Queue<E> {
  private E[] _queue;
  private int _front, _rear, _size;

  public static final int CAPACITY = 1000;

  public ArrayQueue(int capacity) {
    _queue = (E[]) new Object[capacity];
    _front = 0;
    _rear = 0;
    _size = 0;
  }

  public ArrayQueue() {
    this(CAPACITY);
  }

  private int arrayIndex(int n) {
    return modPos(n, _queue.length);
  }
  
  private int modPos(int a, int b) {
    return (a % b >= 0 ? a % b : a % b + b);
  }
  
  public void enqueue(E e) throws FullQueueException {
    if (size() == _queue.length)
      throw new FullQueueException("Full queue");
    
    _queue[_rear] = e;
    _rear = arrayIndex(_rear + 1);
    _size++;
  }

  public E dequeue() throws EmptyQueueException {
    E ret = front();
    _queue[_front] = null;
    _front = arrayIndex(_front + 1);
    _size--;
    return ret;
  }

  public E front() throws EmptyQueueException {
    if (isEmpty())
      throw new EmptyQueueException("Queue empty");

    return _queue[_front];
  }
  
  public int size() {
    return _size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  private void printQueue() {
    for (int i = 0; i < _queue.length; i++)
      if (_queue[i] == null)
        System.out.print("null ");
      else
        System.out.print(_queue[i].toString() + " ");

    System.out.println();
  }
  
  public String toString() {
    String ret = "";

    if (isEmpty())
      return ret;

    for (int i = 0; i < size(); i++) 
      ret += _queue[arrayIndex(i + _front)].toString() + " ";

    return ret.substring(0, ret.length() - 1);
  }

  public static void main(String[] args) {
    ArrayQueue<Integer> Q = new ArrayQueue<Integer>(4);
    Q.enqueue(4);
    Q.enqueue(5);
    Q.enqueue(7);
    Q.enqueue(8);
    Q.dequeue();
    Q.enqueue(9);
    System.out.println(Q);
  }
}
