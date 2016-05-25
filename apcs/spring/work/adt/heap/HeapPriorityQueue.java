import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> implements PriorityQueue<K, V> {
  private ArrayList<Entry<K, V>> _list;
  private Comparator<K> _c;

  public HeapPriorityQueue() {
    this(new DefaultComparator<K>());
  }

  public HeapPriorityQueue(Comparator<K> c) {
    _list = new ArrayList<Entry<K, V>>();
    _c = c;
  }

  public boolean isEmpty() {
    return _list.isEmpty();
  }

  public void add(K key, V value) {
    int index = _list.size();
    int parent = (index - 1) / 2;
    Entry<K, V> entry = new Entry<K, V>(key, value);
    _list.add(entry);

    while (index != 0 && _c.compare(key, _list.get(parent).getKey()) < 0) {
      _list.set(index, _list.set(parent, entry));
      index = parent;
      parent = (index - 1) / 2;
    }
  }

  private int minChild(int index) {
    int left = 2 * index + 1;
    int right = 2 * index + 2;

    if (left >= _list.size())
      return -1;

    if (right >= _list.size())
      return left;

    if (_c.compare(_list.get(left).getKey(), _list.get(right).getKey()) <= 0)
      return left;

    return right;
  }

  public Entry<K, V> removeMin() {
    if (_list.size() <= 1) {
      return _list.remove(0);
    } else {
      Entry<K, V> entry = _list.remove(_list.size() - 1);
      _list.set(0, entry);
      int cur = 0;
      int mc = minChild(cur);

      while (mc != -1) {
        if (_c.compare(_list.get(cur).getKey(), _list.get(mc).getKey()) <= 0)
          break;

        _list.set(cur, _list.set(mc, _list.get(cur)));
        cur = mc;
        mc = minChild(cur);
      }

      return entry;
    }      
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
    pq.removeMin();
    System.out.println(pq);
  }
}


