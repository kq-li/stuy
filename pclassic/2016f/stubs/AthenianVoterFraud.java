import java.util.*;
import java.io.*;

class AthenianVoterFraud {
	public static void main(String[] args)throws IOException {
		
		BufferedReader b = new BufferedReader(new FileReader("AthenianVoterFraudIN.txt"));

		while (b.ready()) {
			String[] data = b.readLine().split(",");
			ArrayList<Integer> votes = new ArrayList<Integer>();
			for (String vote: data) {
				votes.add(Integer.parseInt(vote));
			}
      int[] voters = new int[votes.size()];
      for (int i = 0; i < votes.size(); i++) {
        voters[i] = votes.get(i);
      }
			int[] result = findDuplicates(voters);
      Arrays.sort(result);
			if (result.length == 0) {
				System.out.println("none");
			} else {
				String res = Arrays.toString(result);
				res = res.substring(1, res.length() - 1);
				res = res.replace(", ", ",");
				System.out.println(res);
			}
		}

	}
  // Fill out the body of this method
  // voters is an array of integers indicating the votes cast you should return another
  // array of integers containing the id's that appear more than once
  // if no id's appear multiple times return a zero length array
	public static int[] findDuplicates(int[] voters) {
    return new int[0];
	}
}
