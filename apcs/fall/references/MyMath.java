public class MyMath{

    public static int abs(int x){
        if ( x < 0) return -x;
	return x;
    }

    public static double abs(double x){
	double ans = x;
	if (x < 0) ans *= -1.0;
	return ans;
    }


    public static double sqrt(double x){
	if (x < 0) return Double.NaN;
	double guess = 1.0;
	while (abs(guess - x/guess) > 1e-15*guess )
	    guess = (guess + x/guess) / 2.0;
	return guess;
    }

    public static boolean isOdd(int n){
	return n % 2 == 1;
    }

    public static boolean isEven(int n){
	return !isOdd(n);
    }

    public static boolean isPrime(int n){
	if (n == 2) return true;
	for (int d = 2; d <= n/d ; d++){
	    if (n % d == 0)
		return false;
	}
	return true;
    }

    public static void main(String [] args){

	int N = args.length;
	double a[] = new double[N];
	for (int i = 0; i < N; i++)
	    a[i] = Double.parseDouble(args[i]);

	for (int i = 0; i < N; i++){
	    double x = sqrt(a[i]);
	    System.out.println("sqrt(" + a[i] + ")= " + x);
	}
    }
}
