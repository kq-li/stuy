public class ArrayQueue<E> implements Queue<E>{

    private E[] _queue;
    private int _size, _front, _rear;
    private final static  int CAPACITY = 1000;


    // _rear == _front when full
    // otherwise _rear refers to an empty location

    public ArrayQueue(int capacity){
	_queue = (E[]) new Object[capacity];
	_size = _rear = 0;
	_front = -1;
    }

    public ArrayQueue(){
	this(CAPACITY);
    }

    public int size(){
	return _size;
    }

    public boolean isEmpty(){
	return size() == 0;
    }

    public E front() throws EmptyQueueException{
	if (isEmpty())
	    throw new EmptyQueueException("Queue is empty.");
	return _queue[_front];
    }

    public E dequeue() throws EmptyQueueException{
	E ans = front();
	_queue[_front] = null;
	_size--;
	if (!isEmpty())
	    _front = (1 + _front) % _queue.length;
	else{
	    _front = -1;
	    _rear = 0;
	}
	return ans;
    }

    public void enqueue(E value) throws FullQueueException{
	if (size() == _queue.length)
	    throw new FullQueueException("Full stack.");
	_queue[_rear] = value;
	_size++;
	if (size() == 1) _front = _rear;
	_rear = (_rear + 1) % _queue.length;
    }

    public String toString(){
	String ans ="[";
        if (size() > 0) ans += _queue[_front];
        if (size() > 1) 
            for (int i = (_front + 1) % _queue.length; i != _rear; i = (i+1) % _queue.length)
                ans += ", " + _queue[i];
        ans += "]";
        return ans;
    }

    public static void main(String [] args){
	Queue<Integer> q = new ArrayQueue<Integer>(10);
	System.out.println(q);

	for (int i = 0; i < 5 ; i++){
	    q.enqueue(i);
	    System.out.println("enqueue : " + i + " q: " + q);
	}

	while (!q.isEmpty()){
	    if (Math.random() < 0.75)
		System.out.println("dequeue " + q.dequeue() + " q: " + q);
	    else {
		int x = (int)(Math.random() * 100) ;
		q.enqueue(x);
		System.out.println("enqueue : " + x + " q: " + q);
	    }
	}

    }



}
