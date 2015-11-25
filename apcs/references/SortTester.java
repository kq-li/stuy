public class SortTester{

    public static void main(String [] args){
	String [] names = {"abe", "mary", "betty", "michael"};
	Integer [] ages = { 3, 19, 4, 11};
	Double [] heights = { 0.2, 3.0, 1.2, 1.1};  

	ArrayIO.printArray(names);
	ArrayIO.printArray(ages);
	ArrayIO.printArray(heights);

	Sorts.shuffle(names);
	Sorts.shuffle(ages);
	Sorts.shuffle(heigts);
	

	ArrayIO.printArray(names);
	ArrayIO.printArray(ages);
	ArrayIO.printArray(heights);

    }

}
