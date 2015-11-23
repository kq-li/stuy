public class NumToEng { 
  public static String numToEng(int num) {
    String[] units = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    String[] tens = {"", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    String[] places = {"", "thousand", "million", "billion"};
    String ret = "";
    if (num < 10) {
      ret += units[num];
    } else if (num < 20) {
      ret += teens[num - 10];
    } else if (num < 100) {
      ret += tens[num / 10 - 1];
      num %= 10;
      if (num != 0) {
        if (num < 10)
          ret += "-" + units[num];
        else if (num < 20)
          ret += " " + teens[num];
      }
    } else if (num < 1000) {
      ret += units[num / 100] + " hundred";
      String threeDigit = numToEng(num % 100);
      if (!threeDigit.equals(""))
        ret += " " + threeDigit;
    } else {
      int numDigits = (int) Math.log10(num) + 1;
      for (int i = 0; i <= numDigits / 3; i++) {
        if (num == 0)
          break;
        String threeDigit = numToEng(num % 1000);
        if (!threeDigit.equals(""))
          ret = threeDigit + " " + places[i] + " " + ret;
        num /= 1000;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    int num = Integer.parseInt(args[0]);
    System.out.println(numToEng(num));
  }
}
