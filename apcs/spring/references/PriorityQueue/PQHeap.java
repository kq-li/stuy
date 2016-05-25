import java.util.ArrayList;
import java.util.Comparator;

public class PQHeap<K, V> implements PriorityQueue<K,V>{

    private ArrayList<MyEntry<K,V>> _list;    
    private Comparator<K> _c;



    // private inner class
   
    private static class MyEntry<K, V> implements Entry<K,V>{
	private K _key;
	private V _value;


	public MyEntry(K key, V value){
	    _key = key;
	    _value = value;
	}
	public K getKey() { return _key;}
	public V getValue() { return _value;}


	public String toString(){
	    return "{" + _key + " : " + _value + "}" ;
	}
    }



    public PQHeap(){
	_list = new ArrayList<MyEntry<K,V>>();
	_c = new DefaultComparator<K>();
    }

    public PQHeap(Comparator<K> c){
	this();
	_c = c;
    }

    public boolean isEmpty(){
	return _list.isEmpty();
    }
   
    // adds in order : O(logN)
    public void add(K key, V value){
	_list.add(new MyEntry(key,value));
       	int pos = _list.size() - 1;
	while (pos > 0){
	    int parentPos = (pos - 1) / 2;
	    if( _c.compare(_list.get(pos).getKey(), _list.get((pos - 1)/ 2).getKey()) >= 0) break; 
	    _list.set(pos, _list.set(parentPos,_list.get(pos)));
	    pos = parentPos;
	}
    }
    
    // O(logN)
    public Entry<K,V> removeMin(){
	Entry<K,V> ans = _list.get(0);
        return ans;
    }

    // return -1 if  pos has no children
    // otherwise returns the minmum child
    private int minChildPos(int pos){
	return -1;
    }



    // O(1)
    public Entry<K,V> peekMin(){
	return _list.get(0);
    }


    public String toString(){
	return _list.toString();
    }

   



    public static void main(String [] args){
	PriorityQueue<Integer,String> pq = new PQHeap<Integer,String>();

	// the smallest key has a highest priority 
	pq.add(1,"apple");
	pq.add(5,"kiwi");
	pq.add(3, "cantalope");
	pq.add(1,"banana");
	pq.add(2, "orange");
	pq.add(2, "grapes");
	pq.add(1, "watermelon");
	
	System.out.println(pq);

    }


}
