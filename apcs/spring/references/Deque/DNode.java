public class DNode<E>{

    private E _value;
    private DNode<E> _next, _previous;

   
    public DNode (E val, DNode<E> previous, DNode<E> next){
	_value = val;
	_previous = previous;
	_next = next;
    }

    public E getValue(){
	return _value;
    }

    public DNode<E> getNext(){
	return _next;
    }

    public DNode<E> getPrevious(){
	return _previous;
    }

    public DNode<E> setNext(DNode<E> t){
	DNode<E> ans = _next;
	_next = t;
	return ans;
    }
    public DNode<E> setPrevious(DNode<E> t){  
	DNode<E> ans = _previous;
	_previous = t;
	return ans;
    }

    public E setValue(E newValue){
	E ans = _value;
	_value = newValue;
	return ans;
    }


    public String toString(){
	return _value.toString();
    }

}
