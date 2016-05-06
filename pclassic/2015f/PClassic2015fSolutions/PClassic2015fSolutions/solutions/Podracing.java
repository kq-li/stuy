import java.util.*;
import java.io.*;
class Podracing
{
    public static void main(String[] args)throws IOException
    {
        Scanner in = new Scanner(new FileReader("PodracingIN.txt"));
        int T =  in.nextInt();
        while(T-- >0){
            int N = in.nextInt();
            int A[] = new int[N];
            for(int i = 0 ; i < N ; i++)
                A[i] = in.nextInt();
            EqualSums(N, A);
        }
    }

    static void EqualSums(int N, int[] A)
    {
        ArrayList<Integer> left   = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        int[] L = new int[N/2]; // Left partite
        int[] R = new int[(N/2)+(N%2)]; // Right partite
        int sum = 0;
        for( int i = 0 ; i < N ; i++){
            sum += A[i];
        }
        if(sum % 2 == 1) // Can't partition odd numbers
        {
            System.out.println("false");
            return;
        }

        int idx1 = 0 , idx2 = 0;
        for(int i = 0 ; i < N ; i++)
        {
            if( i < N/2 ) L[idx1++] = A[i];
            else          R[idx2++] = A[i];
        }

        Split(0,0,idx1,0,left,right,R,L); // Compute Powersets
        Split(0,0,idx2,1,left,right,R,L);

        Collections.sort(right); // Sort right Powerset
        int sz = left.size();
        int flag = 0;
        for(int i = 0 ; i < sz ; i++)
        {
            int cur_sum = left.get(i);
            int target = (sum/2) - cur_sum;
            if(Collections.binarySearch(right,target) >= 0)
            {
                System.out.println("true");
                flag = 1;
                break;
            }
        }

        if(flag == 0)
          System.out.println("false");
    }

    static void Split(int sum, int cur, int lim, int type, ArrayList<Integer> left, ArrayList<Integer> right, int[] R, int[] L)
    {
        if(cur == lim){
            if(type == 0) left.add(sum);
            else          right.add(sum);
            return;
        }
        Split(sum+(type==0?L[cur]:R[cur]),cur+1,lim,type,left,right,R,L);
        Split(sum,cur+1,lim,type,left,right,R,L);
    }
}
