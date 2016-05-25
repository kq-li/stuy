import java.util.Comparator;

public class StringLengthComparator implements Comparator {
    public int compare(Object a, Object b) throws ClassCastException{
	return ((String)a).length() - ((String)b).length();
    }

}
