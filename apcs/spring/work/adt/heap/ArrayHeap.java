import java.util.ArrayList;

public class ArrayHeap<E> {
  private static int find(int n, ArrayList<Integer> L) {
    for (int i = 0; i < L.size(); i++) 
      if (L.get(i) == n)
        return i;

    return -1;
  }

  public static void add(int v, ArrayList<Integer> heap) {
    int index = heap.size();
    int parent = (index - 1) / 2;
    heap.add(v);

    while (index != 0 && v < heap.get(parent)) {
      heap.set(index, heap.set(parent, v));
      index = parent;
      parent = (index - 1) / 2;
    }
  }

  public static void remove(int v, ArrayList<Integer> heap) {
    if (heap.size() == 1) {
      heap.remove(v);
    } else {
      int index = find(v, heap);
      heap.set(index, heap.remove(heap.size() - 1));

      while (true) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int cur = heap.get(index);

        if ((left >= heap.size() || right >= heap.size()) ||
            (cur <= left && cur <= right))
          break;

        if (heap.get(left) < heap.get(right)) {
          heap.set(left, heap.set(index, heap.get(left)));
          index = left;
        } else {
          heap.set(right, heap.set(index, heap.get(right)));
          index = right;
        }
      }
    }
  }

  public static int minChildPos(int pos, ArrayList<Integer> heap) {
    int left = 2 * pos + 1;
    int right = 2 * pos + 2;

    if (left >= heap.size())
      return -1;

    if (right >= heap.size())
      return left;

    if (heap.get(left) <= heap.get(right))
      return left;

    return right;
  }

  public static int removeMin(ArrayList<Integer> heap) {
    int ret = heap.get(0);
    int x = heap.remove(heap.size() - 1);

    if (heap.size() > 0) {
      heap.set(0, x);
      int pos = 0;
      int mcPos = minChildPos(pos, heap);

      while (mcPos != -1) {
        if (heap.get(pos) <= heap.get(mcPos))
          break;

        heap.set(pos, heap.set(mcPos, heap.get(pos)));
        pos = mcPos;
        mcPos = minChildPos(pos, heap);
      }
    }

    return ret;
  }
                   
  public static void main(String[] args) {
    ArrayList<Integer> heap = new ArrayList<Integer>();
    int[] values = {4, 8, 7, 9, 10, 8, 11};

    for (int i : values)
      heap.add(i);

    System.out.println(heap);
    add(3, heap);
    System.out.println(heap);
    add(5, heap);
    System.out.println(heap);
    remove(10, heap);
    System.out.println(heap);
    remove(3, heap);
    System.out.println(heap);
    removeMin(heap);
    System.out.println(heap);
  }
}
