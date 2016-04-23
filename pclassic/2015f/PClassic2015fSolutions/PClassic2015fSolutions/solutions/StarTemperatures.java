import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Collections;

public class StarTemperatures {
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("StarTemperaturesIN.txt"));

    while (br.ready()) {
      int numStars = Integer.parseInt(br.readLine());
      int[] temperatures = new int[numStars];
      String[] strTemperatures = br.readLine().split(" ");
      for (int i = 0; i < numStars; i++) {
        temperatures[i] = Integer.parseInt(strTemperatures[i]);
      }
      int[] answers = medianTemperatures(temperatures);
      for (int i = 0; i < numStars; i++) {
        System.out.println(answers[i]);
      }
    }
    br.close();
  }

  // Fill out the body of this method
  public static int[] medianTemperatures(int[] temperatures) {
    PriorityQueue<Integer> lessThan = new PriorityQueue<Integer>(temperatures.length, Collections.reverseOrder());
    PriorityQueue<Integer> moreThan = new PriorityQueue<Integer>(temperatures.length);
    int median;
    int[] answers = new int[temperatures.length];
    answers[0] = temperatures[0];
    median = temperatures[0];
    for (int i = 1; i < temperatures.length; i++) {
      int x = temperatures[i];
      if (x <= median) {
        lessThan.offer(x);
      } else {
        moreThan.offer(x);
      }
      if (lessThan.size() < moreThan.size() - 1) {
        lessThan.offer(median);
        median = moreThan.poll();
      }
      if (lessThan.size() > moreThan.size()) {
        moreThan.offer(median);
        median = lessThan.poll();
      }
      answers[i] = median;
    }
    return answers;
  }
}
