public class NodeDeque<E> implements Deque<E>{

    private DNode<E> _head,_tail;
    private int _size;

    // Uses two dummy (sentinel) nodes
    public NodeDeque(){
	_head = new DNode(null,null,null);
	_tail = new DNode(null,_head, null);
	_head.setNext(_tail);
	_size = 0;
    }

    public E getFirst() throws EmptyDequeException{
	if (isEmpty())			      
	    throw new EmptyDequeException("empty deque.");
	return _head.getNext().getValue();
    }

    public E getLast() throws EmptyDequeException{}
    
    public void addFirst(E val){
	DNode<E> oldFirst = _head.getNext();
	DNode<E> newFirst = new DNode(val,_head,oldFirst);
	_head.setNext(newFirst);
	oldFirst.setPrevious(newFirst);
	_size++;
    }

    public void addLast(E val){}
    
   
    public E removeFirst() throws EmptyDequeException{
	E ans = getFirst();
	DNode<E> oldFirst = _head.getNext();
	DNode<E> newFirst = oldFirst.getNext();
	_head.setNext(newFirst);
	newFirst.setPrevious(_head);
	oldFirst.setNext(null);
	oldFirst.setPrevious(null);
	_size--;
	return ans;
    }
    public E removeLast()  throws EmptyDequeException{}



    public boolean offerFirst(E val){
	addFirst(val);
	return true;
    }

    public E pollFirst(){
	if (isEmpty()) return null;
	return removeFirst();
    }

    public E peekFirst(){
	if (isEmpty()) return null;
	return getFirst();
    }
    
    public int size(){
	return _size;
    }

    public boolean isEmpty(){
	return size() ==  0;
    }





}
