import java.util.Comparator;

public class ReverseComparator<E> implements Comparator<E>{
    public int compare(E a, E b) throws ClassCastException{
	return ((Comparable<E>)b).compareTo(a);
    }

}
