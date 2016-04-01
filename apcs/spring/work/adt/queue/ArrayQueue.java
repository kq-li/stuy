public class ArrayQueue<E> implements Queue<E> {
  private E[] _queue;
  private int _front, _size;

  public static final int CAPACITY = 1000;

  public ArrayQueue(int capacity) {
    _queue = (E[]) new Object[capacity];
    _front = 0;
    _size = 0;
  }

  public ArrayQueue() {
    this(CAPACITY);
  }

  private int arrayIndex(int index) {
    int mod = index % _queue.length;
    return (mod >= 0 ? mod : mod + _queue.length);
  }
  
  public void enqueue(E e) throws FullQueueException {
    if (_size == _queue.length)
      throw new FullQueueException("Full queue");
    
    _queue[arrayIndex(_front + _size++)] = e;
  }

  public E dequeue() throws EmptyQueueException {
    if (isEmpty())
      throw new EmptyQueueException("Queue empty");

    E ret = _queue[_front];
    _queue[_front] = null;
    _front = arrayIndex(_front + 1);
    _size--;
    return ret;
  }

  public E front() {
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
  
  public String toString() {
    String ret = "";

    if (isEmpty())
      return ret;

    for (int i = _front; i < _front + _size; i++)
      ret += _queue[i].toString() + "\n";

    return ret.substring(0, ret.length() - 1);
  }

  public static void main(String[] args) {
    ArrayQueue<Integer> Q = new ArrayQueue<Integer>();
    
    for (int i = 0; i < 20; i++)
      Q.enqueue(i);
    
    System.out.println(Q);

    for (int i = 0; i < 20; i++)
      Q.dequeue();

    System.out.println(Q);
  }
}
