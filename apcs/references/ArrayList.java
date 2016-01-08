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
