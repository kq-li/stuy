public interface Heap<E> {
  public int size();
  public E peek();
  public boolean add(E e);
  public E poll();
}
