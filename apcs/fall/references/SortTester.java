public class SortTester{


    public static int factorial(int n ){
	if ( n < 2) return 1;
	return n * factorial(n - 1);

    }

    


    public static void main(String [] args){
	String [] n = {"abe","mary", "betty","michael"};
	Integer [] x = {1,5,13,4};
	Double [] y = {1.0, 0.5, 2.1, 3.5};
	
	ArrayIO.printArray(n);
	System.out.println(Sorts.isSorted(n));
	ArrayIO.printArray(x);
	System.out.println(Sorts.isSorted(x));
	ArrayIO.printArray(y);
	System.out.println(Sorts.isSorted(y));


    }


}
