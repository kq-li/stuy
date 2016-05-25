import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> implements PriorityQueue<K, V> {
  private static class HeapEntry<K, V> implements Entry<K, V> {
    private K _key;
    private V _value;

    public HeapEntry(K key, V value) {
      _key = key;
      _value = value;
    }

    public K getKey() {
      return _key;
    }

    public V getValue() {
      return _value;
    }

    public String toString() {
      return "{" + _key + ":" + _value + "}";
    }
  }

  private ArrayList<HeapEntry<K, V>> _list;
  private Comparator<K> _c;

  public HeapPriorityQueue() {
    this(new DefaultComparator<K>());
  }

  public HeapPriorityQueue(Comparator<K> c) {
    _list = new ArrayList<HeapEntry<K, V>>();
    _c = c;
  }

  public boolean isEmpty() {
    return _list.isEmpty();
  }

  public void add(K key, V value) {
    int index = _list.size();
    int parent = (index - 1) / 2;
    HeapEntry<K, V> entry = new HeapEntry<K, V>(key, value);
    _list.add(entry);

    while (index != 0 && _c.compare(key, _list.get(parent).getKey()) < 0) {
      _list.set(index, _list.set(parent, entry));
      index = parent;
      parent = (index - 1) / 2;
    }
  }

  public Entry<K, V> removeMin() {
    return null;
  }

  public Entry<K, V> peekMin() {
    return null;
  }

  public String toString() {
    return _list.toString();
  }

  public static void main(String[] args) {
    PriorityQueue<Integer, String> pq = new HeapPriorityQueue<Integer, String>();

    // the smallest key has a highest priority 
    pq.add(1, "apple");
    pq.add(5, "kiwi");
    pq.add(3, "cantaloupe");
    pq.add(1, "banana");
    pq.add(2, "orange");
    pq.add(2, "grapes");
    pq.add(1, "watermelon");
	
    System.out.println(pq);    
  }
}


