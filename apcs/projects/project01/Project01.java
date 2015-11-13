import java.util.Arrays;

public class Project01 {  
  public static void main(String[] args) {
    if (args.length == 0 || args[0].equals("-h") || "-h-n".indexOf(args[0]) == -1) {
      StdOut.printf("Usage: java Project01.java [flags]\n");
      StdOut.printf("Flags:\n");
      StdOut.printf("\t-h\t\t\t\t\t\tDisplay this message (default)\n");
      StdOut.printf("\t-n [problem-number] [args] [pipe file]\t\tOutput solution to problem-number\n");
    } else if (args[0].equals("-n")) {
      String[] pargs = Arrays.copyOfRange(args, 2, args.length);
      switch (Integer.parseInt(args[1])) {
      case 1:
        problem01(pargs);
        break;
      case 2:
        problem02(pargs);
        break;
      case 3:
        problem03(pargs);
        break;
      case 4:
        problem04(pargs);
        break;
      case 5:
        problem05(pargs);
        break;
      case 6:
        problem06(pargs);
        break;
      case 7:
        problem07(pargs);
        break;
      case 8:
        problem08(pargs);
        break;
      case 9:
        problem09(pargs);
        break;
      case 10:
        problem10(pargs);
        break;
      case 11:
        problem11(pargs);
        break;
      case 12:
        problem12(pargs);
        break;
      case 13:
        problem13(pargs);
        break;
      case 14:
        problem14(pargs);
        break;
      case 15:
        problem15(pargs);
        break;
      case 16:
        problem16(pargs);
        break;
      case 17:
        problem17(pargs);
        break;
      case 18:
        problem18(pargs);
        break;
      case 19:
        problem19(pargs);
        break;
      }
    }
  }

  public static void problem01(String[] args) {
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

  public static void problem02(String[] args) {
    int N = StdIn.readInt();           // number of pages
    int[][] counts = new int[N][N];    // counts[i][j] = # links from page i to page j
    int[] outDegree = new int[N];      // outDegree[i] = # links from page i to anywhere

    // Accumulate link counts.
    while (!StdIn.isEmpty()) {
      int i = StdIn.readInt();
      int j = StdIn.readInt();
      if (counts[i][j] == 0) {         // if there are no links between page i and page j
        outDegree[i]++;
        counts[i][j]++;
      }
    }
    StdOut.println(N + " " + N);

    // Print probability distribution for row i.
    for (int i = 0; i < N; i++) {

      // Print probability for column j.
      for (int j = 0; j < N; j++) {
        double p = 0.90 * counts[i][j] / outDegree[i] + 0.10 / N;
        StdOut.printf("%7.5f ", p);
      }
      StdOut.println();
    }
  }

  public static void problem03(String[] args) {
    int N = StdIn.readInt();           // number of pages
    int[][] counts = new int[N][N];    // counts[i][j] = # links from page i to page j
    int[] outDegree = new int[N];      // outDegree[i] = # links from page i to anywhere

    // Accumulate link counts.
    while (!StdIn.isEmpty()) {
      int i = StdIn.readInt();
      int j = StdIn.readInt();
      outDegree[i]++;
      counts[i][j]++;
    }
    StdOut.println(N + " " + N);

    // Print probability distribution for row i.
    for (int i = 0; i < N; i++) {
        
      // Print probability for column j.
      for (int j = 0; j < N; j++) {
        double p;
        if (outDegree[i] == 0) {
          p = 1.0 / N;
        } else {
          p = 0.90 * counts[i][j] / outDegree[i] + 0.10 / N;
        }
        StdOut.printf("%7.5f ", p);
      }
      StdOut.println();
    }
  }

  public static void problem04(String[] args) {
    StdOut.printf("\nRandomSurfer.java fails if the probabilities in p[page] do not sum to 1. " +
                  "This problem occurs due to the possibility of Math.random() returning a " +
                  "number greater than the sum of p[page], resulting in page defaulting to 0. " +
                  "This behavior can be rectified by calculating the sum of the probabilities " +
                  "in p[page] and adding p[page][j] divided by that sum to the sum counter.\n\n");
  }

  public static void problem05(String[] args) {
    StdOut.printf("\nRandomSurfer.java requires 10,000 iterations to calculate page ranks to " +
                  "four decimal places and 100,000 iterations to calculate page ranks to five " +
                  "decimal places for tiny.txt.\n\n");
  }

  public static void problem06(String[] args) {
    StdOut.printf("\nMarkov.java requires 2 iterations to calculate page ranks to 3 decimal " +
                  "places, 3 iterations to calculate page ranks to 4 decimal places, and 8 " +
                  "iterations to calculate page ranks to 10 decimal places.\n\n");
  }

  public static void problem07(String[] args) {
    StdOut.printf("\nAdding links to page 23 from all other pages in medium.txt (see " +
                  "medium7.txt) results in a higher rank for page 23 due to higher " +
                  "likelihood of moving to page 23 from any other page, increasing " +
                  "the fraction of links to page 23.\n\n");
  }

  public static void problem08(String[] args) {
    StdOut.printf("\nAdding links from page 23 to all other pages in medium.txt (see " +
                  "medium7.txt) results in a lower rank for page 23 due to higher " +
                  "likelihood of moving to any other page from page 23, diluting " +
                  "the links so that a smaller fraction link to page 23.\n\n");
  }

  public static void problem09(String[] args) {
    
  }

  public static void problem10(String[] args) {

  }

  public static void problem11(String[] args) {

  }

  public static void problem12(String[] args) {

  }

  public static void problem13(String[] args) {

  }

  public static void problem14(String[] args) {

  }

  public static void problem15(String[] args) {

  }

  public static void problem16(String[] args) {

  }

  public static void problem17(String[] args) {

  }

  public static void problem18(String[] args) {

  }

  public static void problem19(String[] args) {

  }



}
