public class Factors{
    // pre: n >= 2
    // post: primeFactors(144) prints prime factors of 144: 2 2 2 2 3 3
    public static void primeFactors(int n){
	int d = 2;
	System.out.print("prime factors of " + n + " : ");
	while (d <= n){
	    if (n % d == 0){
		System.out.print(d + " ");
		n /= d;
	    }
	    else d++;
	}
	System.out.println();
    }


    public static void main(String [] args){
	int n = Integer.parseInt(args[0]);
	for (int i = 2; i <= n; i++)
	    primeFactors(i);


    }



}
