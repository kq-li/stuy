public class CheckSum{
  /*
    From Sedgewick and Wayne section 2.1 exercise 15
  */


  // *********** Question 1 ************************/
  //precondition: 0 <= d < 10
  //postcondition: returns the sum of the digits of 2d
  //         f(7) = 5, f(1) = 2, ...
  public static long f(long d){
    return 2 * d % 10 + d / 5;
  }

  // ************ Question 2 ************************/

  // precondition: n is a 10 digit number. d0d1d2...d9
  // postcondition: compute the check digit such that the sum:
  // d0 + f(d1) + d2 + f(d3) + d4 +
  // f(d5) + d6 + f(d7) + d8 + f(d9) + checkDigit
  // is a multiple of 10
  // Note: 0 <= checkDigit < 10
  public static int checkDigit(long n){
    long sum = 0;
    for (int i = 9; i >= 0; i--) {
      if (i % 2 == 1) {
        sum += f(n % 10);
      } else {
        sum += n % 10;
      }
      n /= 10;
    }
    return (int) (10 - sum % 10) % 10;
  }


  // **************** Question 3 ********************/
  // post condition: returns a uniform random 10 digit integer
    
  public static long randomAccount(){
    long n = 0;
    for (int i = 0; i < 9; i++) {
      n += Math.pow(10, i) * (int) (Math.random() * 10);
    }
    n += Math.pow(10, 9) * (int) (Math.random() * 9 + 1);
    return n;
  }

  // ***************** Question 4 *******************/
  // postcondition: returns an 11 digit number where the 
  // least significant digit is the checkdigit
  public static String createAccount(){
    long acc = randomAccount();
    return "" + acc + checkDigit(acc);
  }

  // ***************** Question 5 ******************/
  // requires using String methods such as length(),
  // substring()

  // precondition: acct represents a potential account number 
  // postcondition: returns true if the acct represents an actual account
  public static boolean checkSum(String acct){
    return false;
  }

  public static void main(String [] args){
    System.out.println(createAccount());
  }
}
