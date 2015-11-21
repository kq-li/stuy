import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SafeSpacewalks {


    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * fourSum returns the number of distinct and unique quadruples in tools which add up to sum
     * @param sum - the target sum
     * @param tools - the list of available tools
     * @return the number of valid quadruples
     */
    public static int fourSum(int sum, ArrayList<Integer> tools) {
        HashMap<Integer, List<Pair>> sumToPairs = precomputeTwoPairs(tools);
        int validQuadruples = 0;
        for (int candidate : sumToPairs.keySet()) {
            int complement = sum - candidate;
            validQuadruples += numDistinctPairs(sumToPairs.get(candidate), sumToPairs.get(complement));
        }
        return validQuadruples;
    }

    /**
     * precomputeTwoPairs generates all (|tools| choose 2) unique pairs of tools
     * @param tools - the list of available tools
     * @return a mapping from sums to all 2-pairs of tools which add to that sum
     */
    private static HashMap<Integer, List<Pair>> precomputeTwoPairs(ArrayList<Integer> tools) {
        HashMap<Integer, List<Pair>> sumToPairs = new HashMap<Integer, List<Pair>>();
        for (int i = 0; i < tools.size(); i++) {
            for (int j = i + 1; j < tools.size(); j++) {
                int a = tools.get(i);
                int b = tools.get(j);
                Pair currentPair;
                if (a < b) {
                    currentPair = new Pair(a, b);
                }
                else {
                    currentPair = new Pair(b, a);
                }
                int currentSum = a + b;

                if (!sumToPairs.containsKey(currentSum)) {
                    sumToPairs.put(currentSum, new LinkedList<Pair>());
                }

                sumToPairs.get(currentSum).add(currentPair);
            }
        }
        return sumToPairs;
    }

    /**
     * numDistinctPairs finds the number of unique, distinct, and valid pairs between two lists
     * @param one - the first list
     * @param two - the second list
     * @return the number of unique, distinct, and valid pairs
     */
    private static int numDistinctPairs(List<Pair> one, List<Pair> two) {
        if (one == null || two == null) {
            return 0;
        }
        int count = 0;
        for (Pair pairOne : one) {
            for (Pair pairTwo : two) {
                if (inOrder(pairOne.x, pairOne.y, pairTwo.x, pairTwo.y) &&
                        distinctNums(pairOne.x, pairOne.y, pairTwo.x, pairTwo.y)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean inOrder(int a, int b, int c, int d) {
        return a < b && b < c && c < d;
    }

    private static boolean distinctNums(int a, int b, int c, int d) {
        return a != b && a != c && a != d && b != c && b != d && c != d;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileInput = new Scanner(new File("SafeSpacewalksIN.txt"));
        while (fileInput.hasNext()) {
            int sum = fileInput.nextInt();
            int k = fileInput.nextInt();
            ArrayList<Integer> tools = new ArrayList<Integer>();
            for (int i = 0; i < k; i++) {
                tools.add(fileInput.nextInt());
            }
            int numValidQuadruples = fourSum(sum, tools);
            System.out.println(numValidQuadruples);
        }
    }
}
