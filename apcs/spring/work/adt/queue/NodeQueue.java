public class NodeQueue<E> implements Queue<E> {
  private Node<E> _front, _rear;
  private int _size;

  public NodeQueue() {
    _front = null;
    _rear = null;
    _size = 0;
  }

  public void enqueue(E e) {
    Node<E> node = new Node<E>(e, null);

    if (isEmpty()) 
      _front = node;
    else 
      _rear.setNext(node);

    _rear = node;
    _size++;
  }

  public E dequeue() throws EmptyQueueException {
    E ret = front();
    _front = _front.setNext(null);
    _size--;

    if (isEmpty())
      _rear = null;
    
    return ret;
  }

  public E front() throws EmptyQueueException {
    if (isEmpty())
      throw new EmptyQueueException("Queue empty");

    return _front.getValue();
  }

  public int size() {
    return _size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void printQueue() {
    for (Node<E> node = _front; node != null; node = node.getNext())
      if (node.getValue() == null)
        System.out.print("null ");
      else
        System.out.print(node.getValue() + " ");

    System.out.println();
  }

  public String toString() {
    String ret = "";

    if (isEmpty())
      return ret;

    for (Node<E> node = _front; node != null; node = node.getNext())
      ret += node.getValue() + " ";

    return ret;
  }

  public static void main(String[] args) {
    NodeQueue<Integer> Q = new NodeQueue<Integer>();
    Q.printQueue();
    Q.enqueue(3);
    Q.printQueue();
    Q.enqueue(4);
    Q.printQueue();
    Q.dequeue();
    Q.printQueue();
    Q.dequeue();
    Q.printQueue();
  }
}
