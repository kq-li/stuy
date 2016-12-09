import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Treasons
{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("TreasonsIN.txt"));
        int i=0;
        String[] input=new String[10000];
        while (sc.hasNext()) {
            input[i]=sc.next();
            i++;    
        }
        int counter=0;
        String[] input2=new String[i];
        while (counter<i){
            input2[counter]=input[counter];
            counter++;}
        String[] input20=new String[20];
        int counter2=0;
        for (int j=0; j<i;j++){
            if (!(input2[j].equals("|"))){
                input20[counter2]=input2[j];
                counter2++;
            }
            else{
                counter2=0;
                System.out.println(Arrays.toString(maximalAnagramList(input20)));
            }
        }
        sc.close();
    }
    /*The function will be given an array of distinct strings. The purpose of this method is to return
     * the largest set of strings which anagram to each other in an array with the strings in the 
     * output array being in alphabetical order.
       */
    public static String[] maximalAnagramList(String[] array) {
        return array;
    }
}
