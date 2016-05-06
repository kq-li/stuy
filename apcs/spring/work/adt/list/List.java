public interface List<E> {
  boolean add(E x);
  boolean add(int index, E x);
  E get(int index);
  E set(int index, E x);
  E remove(int index);
  int size();
}
