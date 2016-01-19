import java.io.*;
import java.nio.*;
import java.util.ArrayList;

public class Angry {
  private static int N, K;
  private static int[] bales;
  
  private static int partition(int[] arr, int left, int right) {
    int i = left, j = right;
    int tmp;
    int pivot = arr[(left + right) / 2];
     
    while (i <= j) {
      while (arr[i] < pivot)
        i++;
      while (arr[j] > pivot)
        j--;
      if (i <= j) {
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        i++;
        j--;
      }
    }
     
    return i;
  }
 
  private static void quickSort(int[] arr, int left, int right) {
    int index = partition(arr, left, right);
    if (left < index - 1)
      quickSort(arr, left, index - 1);
    if (index < right)
      quickSort(arr, index, right);
  }

  private static int[] balesBetween(int start, int end) {
    ArrayList<Integer> retAL = new ArrayList<Integer>();
    for (int i = 0; i < N; i++)
      if (bales[i] > end)
        break;
      else if (bales[i] >= start && bales[i] <= end)
        retAL.add(bales[i]);
    int[] ret = new int[retAL.size()];
    for (int i = 0; i < retAL.size(); i++)
      ret[i] = retAL.get(i);
    return ret;
  }
  
  private static int[] balesNear(int point, int radius) {
    return balesBetween(point - radius, point + radius);
  }      

  private static int prevBale(int point) {
    for (int i = N - 1; i >= 0; i--)
      if (bales[i] < point)
        return bales[i];
    return -1;
  }
  
  private static int nextBale(int point) {
    for (int i = 0; i < N; i++)
      if (bales[i] > point)
        return bales[i];
    return -1;
  }
  
  private static boolean canExplodeAll(int radius, int start, int end, int cows) {
    if (cows < 0)
      return false;

    int first = start + radius;
    int last = end - radius;

    if (start == -1 || end == -1 || start > end)
      return true;
    
    if (balesBetween(first + radius, last - radius).length == 0 && cows >= 2)
      return true;

    return canExplodeAll(radius, nextBale(first + radius), prevBale(last - radius), cows -= 2);
  }
  
  private static void printArray(int[] a) {
    for (int i : a)
      System.out.print(i + " ");
    System.out.println();
  }

  private static int[] addArrays(int[] a, int[] b) {
    int[] ret = new int[a.length + b.length];
    for (int i = 0; i < a.length; i++)
      ret[i] = a[i];
    for (int i = 0; i < b.length; i++)
      ret[a.length + i] = b[i];
    return ret;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader in = null;
    BufferedWriter out = null;

    try {
      in = new BufferedReader(new FileReader("angry.in"));
      out = new BufferedWriter(new FileWriter("angry.out"));

      String[] firstLine = in.readLine().split(" ");
      N = Integer.parseInt(firstLine[0]);
      K = Integer.parseInt(firstLine[1]);
      bales = new int[N];

      for (int i = 0; i < N; i++)
        bales[i] = Integer.parseInt(in.readLine());

      quickSort(bales, 0, N - 1);
      int ans = 0;
      
      for (int power = 0; power < (bales[N - 1] - bales[0]) / K; power++) {
        if (canExplodeAll(power, bales[0], bales[N - 1], K)) {
          ans = power;
          break;
        }
      }

      System.out.println(ans);
      out.write(ans + "\n");
    } finally {
      if (in != null)
        in.close();
      if (out != null)
        out.close();
    }
  }
}
