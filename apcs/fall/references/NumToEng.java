public class NumToEng{
    // precondition: 0 <= n <= 1000000
    public static String numToEng(int n){
	if ( n < 10)
	    return digits(n);
	if ( n < 20)
	    return teens(n);
	if ( n < 100 && n % 10 == 0)
	    return tensPrefix(n);
	if ( n < 100)
	    return tensPrefix(n/10*10) + "-" + numToEng(n%10);
	if ( n < 1000 && n % 100 == 0)
	    return numToEng(n/100) + " hundred";
	if ( n < 1000)
	    return numToEng(n/100*100) + " " +  numToEng(n%100);
	if ( n < 1000000 && n % 1000 == 0)
	    return numToEng(n/1000) + " thousand";
	if ( n < 1000000)
	    return numToEng(n/1000*1000) + " " + numToEng(n%1000);
	if ( n % 1000000 == 0)
	    return numToEng(n/1000000) + " million";
	return numToEng(n/1000000*1000000) + "  " + numToEng(n%1000000);
    }

    // precondition: n < 100 and a multiple of ten
    public static String tensPrefix(int n){
	String [] tens = { "","ten", "twenty", "thirty", "forty",
			  "fifty", "sixty", "seventy", "eighty",
			  "ninety"};
	return tens[n/10];
    }
    // precondition: 10 <= n < 20
    // postcondition: 
    public static String teens(int n){
	String [] teens = {"ten", "eleven", "twelve", "thirteen",
			   "fourteen", "fifteen", "sixteen", "seventeen",
	                   "eighteen", "nineteen"};
	return teens[n%10];
    }


    // precondition: 0<= n < 10
    // postcondition: converts integer n to its english equivalent
    // 0 -> "zero",...,9 -> "nine"
    public static String digits(int n){
	String [] digits = {"zero","one","two","three",
			    "four","five","six", "seven",
			    "eight", "nine"};
	return digits[n];
    }



    public static void main(String args[]){
	int n = Integer.parseInt(args[0]);
	System.out.println(numToEng(n));

    }


}
