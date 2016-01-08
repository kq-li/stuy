import java.lang.IndexOutOfBoundsException;

public class ArrayList<E> implements List<E> {
  private int _size;
  private E[] _list;

  public ArrayList() {
    this._list = (E[]) (new Object[2]);
    this._size = 0;
  }

  private void resize() {
    E[] temp = (E[]) (new Object[this._size * 2]);
    for (int i = 0; i < this._size; i++)
      temp[i] = this._list[i];
    this._list = temp;
  }

  public boolean add(E x) {
    while (this._size >= this._list.length)
      this.resize();
    this._list[this._size] = x;
    this._size++;
    return true;
  }

  public boolean add(int index, E x) {
    if (index < 0 || index >= this._size)
      throw new IndexOutOfBoundsException();
    this.add(x);
    for (int i = this._size - 1; i > index; i--)
      this.set(i, this.set(i - 1, this.get(i)));
    return true;
  }
  
  public E get(int index) {
    if (index < 0 || index >= this._size)
      throw new IndexOutOfBoundsException();
    return this._list[index];
  }
  
  public E set(int index, E x) {
    if (index < 0 || index >= this._size)
      throw new IndexOutOfBoundsException();
    this._list[index] = x;
    return x;
  }

  public int size() {
    return this._size;
  }

  public String toString() {
    String ret = "[";
    for (int i = 0; i < this._size - 1; i++)
      ret += this._list[i] + ", ";
    ret += this._list[this._size - 1] + "]";
    return ret;
  }
}
