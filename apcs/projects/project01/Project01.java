import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Exception;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Project01 { 
  public static void main(String[] args) {
    try (Scanner stdIn = new Scanner(System.in)) {
      while (true) {
        StdOut.println();
        StdOut.printf("Problem number (q to quit): ");
        String next = stdIn.next();
        if (next.equals("q")) {
          break;
        }
        int probNum = Integer.parseInt(next);
        String filename;
        int pages, links;
        switch (probNum) {
        case 1:
          StdOut.printf("Probability of following link in percent: ");
          double linkProb = stdIn.nextDouble() / 100;
          StdOut.printf("Probability of jumping randomly in percent (should be 100 - linkProb): ");
          double jumpProb = stdIn.nextDouble() / 100;
          StdOut.printf("Data file: ");
          filename = stdIn.next();
          StdOut.println();
          problem01(linkProb, jumpProb, filename);
          break;
        case 2:
          StdOut.printf("Data file: ");
          filename = stdIn.next();
          StdOut.println();
          problem02(filename);
          break;
        case 3:
          StdOut.printf("Data file: ");
          filename = stdIn.next();
          StdOut.println();
          problem03(filename);
          break;
        case 4:
          StdOut.println();
          problem04();
          break;
        case 5:
          StdOut.println();
          problem05();
          break;
        case 6:
          StdOut.println();
          problem06();
          break;
        case 7:
          StdOut.println();
          problem07();
          break;
        case 8:
          StdOut.println();
          problem08();
          break;
        case 9:
          StdOut.println();
          problem09();
          break;
        case 10:
          StdOut.println();
          problem10();
          break;
        case 11:
          StdOut.println();
          problem11();
          break;
        case 12:
          StdOut.println();
          problem12();
          break;
        case 13:
          StdOut.printf("Data file: ");
          filename = stdIn.next();
          StdOut.println();
          problem13(filename);
          break;
        case 14:
          StdOut.printf("Page count: ");
          pages = stdIn.nextInt();
          StdOut.printf("Link count: ");
          links = stdIn.nextInt();
          StdOut.println();
          problem14(pages, links);
          break;
        case 15:
          StdOut.printf("Page count: ");
          pages = stdIn.nextInt();
          StdOut.printf("Link count: ");
          links = stdIn.nextInt();
          StdOut.printf("Hub count: ");
          int hubs = stdIn.nextInt();
          StdOut.printf("Authorities count: ");
          int auths = stdIn.nextInt();
          StdOut.println();
          problem15(pages, links, hubs, auths);
          break;
        case 16:
          StdOut.println();
          problem16();
          break;
        case 17:
          StdOut.println();
          problem17();
          break;
        case 18:
          StdOut.println();
          problem18();
          break;
        case 19:
          StdOut.println();
          problem19();
          break;
        }
      }
    } catch (Exception e) {
      StdOut.println("Invalid input!");
      e.printStackTrace();
    } finally {
      StdOut.println();
    }
  }

  public static void problem01(double linkProb, double jumpProb, String filename) {
    try (Scanner fIn = new Scanner(new File(filename))) {    
      int N = fIn.nextInt();
      int[][] counts = new int[N][N];
      int[] outDegree = new int[N];
      
      while (fIn.hasNextInt())  {
        int i = fIn.nextInt(); 
        int j = fIn.nextInt(); 
        outDegree[i]++; 
        counts[i][j]++; 
      }
      
      StdOut.println(N + " " + N);
      double[][] trans = transition(counts, outDegree, linkProb, jumpProb);
      print2DArray(trans);
    } catch (FileNotFoundException e) {
      StdOut.printf("File %s not found!", filename);
      StdOut.println();
    }
  }

  public static void problem02(String filename) {
    try (Scanner fIn = new Scanner(new File(filename))) {
      int N = fIn.nextInt();
      int[][] counts = new int[N][N];
      int[] outDegree = new int[N];
      
      while (fIn.hasNextInt()) {
        int i = fIn.nextInt();
        int j = fIn.nextInt();
        if (counts[i][j] == 0) {
          outDegree[i]++;
          counts[i][j]++;
        }
      }
      
      StdOut.println(N + " " + N);
      double[][] trans = transition(counts, outDegree, 0.90, 0.10);
      print2DArray(trans);
    } catch (FileNotFoundException e) {
      StdOut.printf("File %s not found!", filename);
      StdOut.println();
    }
  }

  public static void problem03(String filename) {
    try (Scanner fIn = new Scanner(new File(filename))) {
      int N = fIn.nextInt();
      int[][] counts = new int[N][N];
      int[] outDegree = new int[N];
      
      while (fIn.hasNextInt()) {
        int i = fIn.nextInt();
        int j = fIn.nextInt();
        outDegree[i]++;
        counts[i][j]++;
      }
      
      StdOut.println(N + " " + N);
      double[][] trans = transition(counts, outDegree, 0.90, 0.10);
      
      for (int i = 0; i < N; i++)
        if (outDegree[i] == 0)
          for (int j = 0; j < N; j++)
            trans[i][j] = 1.0 / N;
      print2DArray(trans);
    } catch (FileNotFoundException e) {
      StdOut.printf("File %s not found!", filename);
      StdOut.println();
    }
  }

  public static void problem04() {
    StdOut.println("RandomSurfer.java fails if the probabilities in p[page] do not sum to 1. " +
                  "This problem occurs due to the possibility of Math.random() returning a " +
                  "number greater than the sum of p[page], resulting in page defaulting to 0. " +
                  "This behavior can be rectified by calculating the sum of the probabilities " +
                  "in p[page] and adding p[page][j] divided by that sum to the sum counter.");
  }

  public static void problem05() {
    StdOut.printf("RandomSurfer.java requires 10,000 iterations to calculate page ranks to " +
                  "four decimal places and 100,000 iterations to calculate page ranks to five " +
                  "decimal places for tiny.txt.\n");
  }

  public static void problem06() {
    StdOut.println("Markov.java requires 2 iterations to calculate page ranks to 3 decimal " +
                  "places, 3 iterations to calculate page ranks to 4 decimal places, and 8 " +
                  "iterations to calculate page ranks to 10 decimal places.");
  }

  public static void problem07() {
    StdOut.println("Adding links to page 23 from all other pages in medium.txt (see " +
                  "medium7.txt) results in a higher rank for page 23 due to higher likelihood" +
                  "of moving to page 23 from any other page, increasing the fraction of links" +
                  "to page 23.");
  }

  public static void problem08() {
    StdOut.println("Adding links from page 23 to all other pages in medium.txt (see " +
                  "medium8.txt) results in a lower rank for page 23 due to higher likelihood " +
                  "of moving to any other page from page 23, diluting the links so that a " +
                  "smaller fraction link to page 23.");
  }

  public static void problem09() {
    StdOut.println("Adding a link from 23 to any page that links back to 23 in any number of " +
                   "steps increases the page rank of 23 because the probability of 23 being " +
                   "accessed through links therefore increases (medium09.txt has extra link " +
                   "23 12).");
  }

  public static void problem10() {
    StdOut.println("Adding a link from 23 to any page increases the page rank of that page " +
                   "because the concentration of links, and therefore the probability of " +
                   "navigating to that page, increases (medium10.txt has extra link 23 22).");
  }

  public static void problem11() {
    StdOut.println("Running java Transition < 8pages.txt | java RandomSurfer 100000 returns:");
    StdOut.println();      
    String filename = "8pages.txt";
    int moves = 1000000;
    
    try (Scanner fIn = new Scanner(new File(filename))) {    
      int N = fIn.nextInt();
      int[][] counts = new int[N][N];
      int[] outDegree = new int[N];

      while (fIn.hasNextInt())  {
        int i = fIn.nextInt(); 
        int j = fIn.nextInt(); 
        outDegree[i]++; 
        counts[i][j]++; 
      }
      
      StdOut.println(N + " " + N);
      double[][] trans = transition(counts, outDegree, 0.90, 0.10);
      double[] rank = randomSurfer(trans, moves);
      format1DArray(rank);
    } catch (FileNotFoundException e) {
      StdOut.printf("File %s not found!", filename);
      StdOut.println();
    }
  }

  public static void problem12() {
    StdOut.println("Running java Transition < 8pages.txt | java Markov 100 returns:");
    StdOut.println();
    String filename = "8pages.txt";
    int moves = 100;
    
    try (Scanner fIn = new Scanner(new File(filename))) {    
      int N = fIn.nextInt();
      int[][] counts = new int[N][N];
      int[] outDegree = new int[N];

      while (fIn.hasNextInt())  {
        int i = fIn.nextInt(); 
        int j = fIn.nextInt(); 
        outDegree[i]++; 
        counts[i][j]++; 
      }

      StdOut.println(N + " " + N);
      double[][] trans = transition(counts, outDegree, 0.90, 0.10);
      double[] rank = markov(trans, moves);
      format1DArray(rank);
    } catch (FileNotFoundException e) {
      StdOut.printf("File %s not found!", filename);
      StdOut.println();
    }    
  }

  public static void problem13(String filename) {
    try (Scanner fIn = new Scanner(new File(filename))) {    
      int N = fIn.nextInt();
      int[][] counts = new int[N][N];
      int[] outDegree = new int[N];

      while (fIn.hasNextInt())  {
        int i = fIn.nextInt(); 
        int j = fIn.nextInt(); 
        outDegree[i]++; 
        counts[i][j]++; 
      }

      StdOut.println(N + " " + N);
      double[][] a = transition(counts, outDegree, 0.90, 0.10);
      double[][] b;
      boolean done = false;
      while (!done) {
        b = square(a);
        for (int i = 0; i < N; i++) {
          if (done)
            break;
          for (int j = 0; j < N; j++) {
            if (done)
              break;
            if ((long) (a[i][j] * 10000000000.0) == (long) (b[i][j] * 10000000000.0)) {
              done = true;
            }
          }
        }
        a = copy(b);
      }

      format1DArray(a[0]);
    } catch (FileNotFoundException e) {
      StdOut.printf("File %s not found!", filename);
      StdOut.println();
    }    
  }
  
  public static void problem14(int pages, int links) {
    int[][] counts = generate(pages, links);
    for (int i = 0; i < pages; i++) {
      for (int j = 0; j < pages; j++) {
        for (int k = 0; k < counts[i][j]; k++) {
          StdOut.printf("%2d %2d   ", i, j);
        }
      }
      StdOut.println();
    }
  }

  public static void problem15(int basePages, int baseLinks, int hubs, int auths) {
    StdOut.println("Hubs, which come right after basePages and before authorities, seem to rank " +
                  "higher than auths due to the fact that hubs generate self-traffic, while " +
                   "authorities mainly generate traffic for other pages.");
    int[][] counts = generate(basePages, baseLinks);
    int pages = basePages + hubs + auths;
    int[][] web = new int[pages][pages];
    
    for (int i = 0; i < basePages; i++)
      for (int j = 0; j < basePages; j++)
        web[i][j] = counts[i][j];
    
    for (int i = basePages; i < basePages + hubs; i++) {
      for (int j = 0; j < basePages / 10; j++) {
        int page = (int) (Math.random() * basePages);
        web[page][i]++;
      }
    }

    for (int i = basePages + hubs; i < pages; i++) {
      for (int j = 0; j < basePages / 10; j++) {
        int page = (int) (Math.random() * basePages);
        web[i][page]++;
      }
    }
  
    int[] outDegree = new int[pages];
    for (int i = 0; i < pages; i++)
      for (int j = 0; j < pages; j++)
        outDegree[i] += web[i][j];

    double[][] trans = transition(web, outDegree, 0.90, 0.10);
    double[] rank = markov(trans, 100);
    format1DArray(rank);
  }

  public static void problem16() {

  }

  public static void problem17() {

  }

  public static void problem18() {

  }

  public static void problem19() {

  }

  public static int[][] generate(int pages, int links) {
    int[][] web = new int[pages][pages];
    for (int i = 0; i < links; i++) {
      int src = (int) (Math.random() * pages);
      int dst;
      do {
        dst = (int) (Math.random() * pages);
      } while (dst == src);
      web[src][dst]++;
    }
    return web;
  }
  
  public static double[][] transition(int[][] counts, int[] outDegree,
                                      double linkProb, double jumpProb) {
    int N = counts.length;
    double[][] prob = new double[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (outDegree[i] == 0)
          prob[i][j] = jumpProb / N;
        else
          prob[i][j] = linkProb * counts[i][j] / outDegree[i] + jumpProb / N;
      }
    }

    return prob;
  }

  public static double[] randomSurfer(double[][] transition, int moves) {
    int N = transition.length;
    int[] freq = new int[N];
    int page = 0;

    for (int t = 0; t < moves; t++) {
      double r = Math.random();
      double sum = 0.0;
      for (int i = 0; i < N; i++) {
        sum += transition[page][i];
        if (r < sum) {
          page = i;
          break;
        }
      }
      freq[page]++;
    }
    
    double[] rank = new double[N];
    
    for (int i = 0; i < N; i++)
      rank[i] = (double) freq[i] / moves;
    
    return rank;
  }

  public static double[] markov(double[][] transition, int iterations) {
    int N = transition.length;
    double[] rank = new double[N];
    rank[0] = 1.0;

    for (int t = 0; t < iterations; t++) {
      double[] newRank = new double[N];
      
      for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
          newRank[i] += rank[j] * transition[j][i];
      
      rank = newRank;
    }
    
    return rank;
  }
  
  public static double[][] square(double[][] a) {
    int N = a.length;
    double b[][] = new double[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < N; k++) {
          b[i][j] += a[i][k] * a[k][j];
        }
      }
    }
    return b;
  }

  public static double[][] copy(double[][] a) {
    int N = a.length;
    double b[][] = new double[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        b[i][j] = a[i][j];
    return b;
  }

  public static void format1DArray(double[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++)
      StdOut.printf("%2d  %.10f\n", i, a[i]);
    StdOut.println();
  }
  
  public static void print2DArray(double[][] a) {
    int N = a.length;
    int M = a[0].length;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        StdOut.printf("%.10f ", a[i][j]);
      }
      StdOut.println();
    }
  }

  public static void print2DArray(int[][] a) {
    int N = a.length;
    int M = a[0].length;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        StdOut.printf("%2d ", a[i][j]);
      }
      StdOut.println();
    }
  }
  
}
