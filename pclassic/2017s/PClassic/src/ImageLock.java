import java.util.*;
import java.io.*;
public class ImageLock {
    public static void main(String[] args) throws FileNotFoundException {
        // Before submitting, make sure the main method hasn't been changed!
        Scanner sc = new Scanner(new FileReader("ImageLockIN.txt"));
        while(sc.hasNext()) {
            int width = sc.nextInt();
            int height = sc.nextInt();
            int[][] image = new int[width][height];
            for (int i = 0; i < height; i++) {
              for (int j = 0; j < width; j++) {
                image[j][i] = sc.nextInt();
              }
            }
            if (isCat(image)) {
              System.out.println("cat");
            } else {
              System.out.println("random");
            }
        }
        sc.close();
    }

  //Fill out the body of this method
  public static boolean isCat(int[][] image) {    
    int [][] component = new int[image.length][image[0].length];
    int compNo = 1;
    
    for(int i = 0; i < image.length; i++)
    {
    	for(int j = 0; j < image[0].length; j++)
    	{
    		boolean c = DFS(image, i, j, compNo, component);
    		if(c) compNo++;
    	}
    }
    if(compNo >= Math.sqrt(image.length * image[0].length)) return false;
    return true;
  }
  
  public static boolean DFS(int [][] matrix, int i, int j, int compNo, int [][] component)
  {
	  if(component[i][j] != 0) return false;
	  component[i][j] = compNo;
	  
	  int mag = matrix[i][j];
	  if(i != 0 && Math.abs(mag - matrix[i - 1][j]) <= 25) DFS(matrix, i - 1, j, compNo, component);
	  if(i != matrix.length - 1 && Math.abs(mag - matrix[i + 1][j]) <= 25) DFS(matrix, i + 1, j, compNo, component);
	  if(j != 0 && Math.abs(mag - matrix[i][j - 1]) <= 25) DFS(matrix, i, j - 1, compNo, component);
	  if(j != matrix[0].length - 1 && Math.abs(mag - matrix[i][j + 1]) <= 25) DFS(matrix, i, j + 1, compNo, component);
	  
	  return true;
  }
}
