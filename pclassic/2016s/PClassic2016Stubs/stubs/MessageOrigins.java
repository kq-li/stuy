import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MessageOrigins {

	public static void main(String[] args) throws IOException{
		Reader r = new FileReader("MessageOriginsIN.txt");
		BufferedReader reader = new BufferedReader(r);
		while (reader.ready()){
			String[] arr=reader.readLine().split(" ");
			int[] intArr = new int[arr.length - 1];
	
			for(int i=0;i < arr.length - 1;i++)
			 intArr[i]=Integer.parseInt(arr[i]);
			int index = Integer.parseInt(arr[arr.length - 1]);
			System.out.println(findIndex(intArr, index));
		}
		
	}
		
  //Fill out the body of this method
  public static int findIndex(int [] parent, int i){
    return 0;
  }
}
