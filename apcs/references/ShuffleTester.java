public class ShuffleTester{


    public static void printArray(int [] a){
	for(int i = 0; i < a.length; i++)
	    System.out.print(a[i] + " ");
	System.out.println();
    }

    public static void shuffle(int[] a){
	for(int i = 0; i < a.length - 1; i++){
	    int index = (int)(Math.random() * (a.length - i)) + i;
	    int temp = a[i];
	    a[i] = a[index];
	    a[index] = temp;
	}
    }

    public static void printMatrix(int[][] a){
	for (int i = 0; i < a.length ; i++){
	    printArray(a[i]);
	}
    }

    public static void test(int [] a, int[][] b){
       	// initialize
	for (int i = 0; i < a.length; i++)
            a[i] = i;
	shuffle(a);
	for (int i = 0; i < a.length; i++){
	    b[a[i]][i]++;
	}

    }


    public static void main(String [] args){
       
	int M = Integer.parseInt(args[0]); // data size
	int N = Integer.parseInt(args[1]); // # of shuffles

	int[] data = new int[M];

	int[][] mat = new int[M][M];


	//printArray(data);
	
	//	shuffle(data);
	for (int i = 0; i < N; i++)
	    test(data,mat);
	printMatrix(mat);
	System.out.println("N/M: " + N/M);

    }


}
