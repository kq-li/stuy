import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SpaceResources {


    /**
     * findDuplicates returns the a sorted list of resources which belong to both input lists
     * @param requested - the requested list of resources
     * @param available - the available list of resources
     * @return the duplicate resources
     */
    public static List<String> findDuplicates(List<String> requested, List<String> available) {
        List<String> duplicates = new ArrayList<String>();
        for (String request : requested) {
            // Since |available| >> |requested| we binary search available to achieve a runtime on the order of
            // |requested|log(|available|)
            if (Collections.binarySearch(available, request) >= 0) {
                duplicates.add(request);
            }
        }
        return duplicates;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileInput = new Scanner(new File("SpaceResourcesIN.txt"));
        while (fileInput.hasNext()) {
            int a = fileInput.nextInt();
            int b = fileInput.nextInt();

            List<String> requested = new ArrayList<String>();
            for (int i = 0; i < a; i++) {
                requested.add(fileInput.next());
            }

            List<String> available = new ArrayList<String>();
            for (int i = 0; i < b; i++) {
                available.add(fileInput.next());
            }

            List<String> duplicates = findDuplicates(requested, available);
            StringBuilder builder = new StringBuilder();
            for (String resource : duplicates) {
                builder.append(resource).append(" ");
            }
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            System.out.println(builder.toString());
        }
    }
}
