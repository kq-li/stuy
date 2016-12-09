import java.util.*;
import java.io.*;
public class SudokuSolver {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("SudokuSolverIN.txt"));
        
        int[][] puzzle = new int[9][9];
        
        while(sc.hasNext()) {
        	for(int i = 0; i < 9; i++) {
        		for(int j = 0; j < 9; j++) {
        			puzzle[i][j] = sc.nextInt();
        		}
        	}
        	
        	puzzle = sudokuSolve(puzzle);
        	
        	for(int i = 0; i < 9; i++) {
        		for(int j = 0; j < 9; j++) {
        			if(j < 8) {
        				System.out.print(puzzle[i][j] + " ");
        			} else {
        				System.out.println(puzzle[i][j] + " ");
        			}
        		}
        	}
        	
        	System.out.println("");
        	
        }
    }
    
    public static int[][] sudokuSolve(int[][] puzzle) {
    	/* Fill out the body of this method.
    	 * 
    	 * This method takes in the unsolved sudoku puzzle
    	 * and fills out all the 0's with a number between 1
    	 * and 9. Make sure that each row, column, and bolded
    	 * box have exactly the numbers 1 through 9.
    	 */
    	return puzzle;
    }
    
}
