public class ArrayList<E> implements  List<E>{

    private E[] _list;
    private int _size;

    // constructor
    public ArrayList(){
	_list = (E[]) (new Object[2]);
	_size = 0;
    }


    private void resize(){
	E[] temp = (E[])(new Object[ size() * 2]);
	for(int i = 0; i < size(); i++)
	    temp[i] = _list[i];
	_list = temp;
    }

    public boolean add(E x){
	if (size() == _list.length) 
	    resize();
	_list[size()] = x;
	_size++;
	return true;
    }

    /*
    //version1 : directly use _list.
    public void add(int index, E x){
	if (index < 0  || index > size() )
	    throw new IndexOutOfBoundsException("index < 0 || index > size()");
	add(x);
	for (int i = size() - 1; i > index ; i--){
	    E temp = _list[i-1];
	    _list[i-1] = _list[i];
	    _list[i] = temp;
	}
   }
    */
    //version2 : do not directly use _list. Use set() and get()
    public void add(int index, E x){
	if (index < 0  || index > size() )
	    throw new IndexOutOfBoundsException("index < 0 || index > size()");
	add(x);
	for (int i = size() - 1; i > index ; i--){
	    set(i, set(i-1,get(i)));
	}
   }
   
   public E remove(int index){
	return null;
    }



    public E get(int index){
	if (index < 0 || index >= size())
	    throw new IndexOutOfBoundsException("index < 0 || index >= size()");
	
	return _list[index];
    }
    public E set(int index, E x){
	if (index < 0 || index >= size())
	    throw new IndexOutOfBoundsException("index < 0 || index >= size()");
	E temp = get(index);
	_list[index ] = x;
	return temp;
    }
    public int size(){
	return _size;
    }

    public String toString(){
	String ans = "[";
	for (int i = 0; i < size() ; i++)
	    ans += _list[i] + ", ";
	if (size() > 0)
	    ans = ans.substring(0, ans.length() - 2);
	ans += "]";
	return ans;
    }

}
