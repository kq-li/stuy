public class Entry<K, V> {
  private K _key;
  private V _value;

  public Entry(K key, V value) {
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
