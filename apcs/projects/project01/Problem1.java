public class Problem1 {


  public static void main(String[] args) {

    double linkProb = Double.parseDouble(args[0]) / 100;    // probability of using a link
    double jumpProb = Double.parseDouble(args[1]) / 100;    // probability of jumping randomly
      
    int N = StdIn.readInt();           // number of pages
    int[][] counts = new int[N][N];    // counts[i][j] = # links from page i to page j
    int[] outDegree = new int[N];      // outDegree[i] = # links from page i to anywhere

    // Accumulate link counts.  
    while (!StdIn.isEmpty())  {
      int i = StdIn.readInt(); 
      int j = StdIn.readInt(); 
      outDegree[i]++; 
      counts[i][j]++; 
    } 
    StdOut.println(N + " " + N); 


    // Print probability distribution for row i. 
    for (int i = 0; i < N; i++)  {

      // Print probability for column j. 
      for (int j = 0; j < N; j++) {
        double p = linkProb * counts[i][j] / outDegree[i] + jumpProb / N; 
        StdOut.printf("%7.5f ", p); 
      }
      StdOut.println(); 
    } 
  } 
} 

