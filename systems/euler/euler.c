#include <stdio.h>
#include <stdlib.h>
#include <math.h>

long isPrime(long n) {
  if (n == 1)
    return 0;

  if (n == 2)
    return 1;
  
  long i;

  for (i = 3; i < (long) sqrt((float) n); i++)
    if (n % i == 0)
      return 0;

  return 1;
}

long isPalindrome(long n) {
  long length = (long) log10l(n) + 1;
  int digits[length];
  int i;

  for (i = 0; i < length; i++) {
    digits[i] = n % 10;
    n /= 10;
  }
  
  for (i = 0; i < length / 2 + 1; i++) 
    if (digits[i] != digits[length - i - 1]) 
      return 0;
  
  return 1;
}

long problem1() {
  long sum = 0;
  long i;
  
  for (i = 1; i < 1000; i++)
    if (i % 3 == 0 || i % 5 == 0)
      sum += i;

  return sum;
}

long problem2() {
  long a = 1;
  long b = 2;
  long sum = 0;
  
  while (a < 4000000) {
    if (a % 2 == 0)
      sum += a;

    b = a + b;
    a = b - a;
  }

  return sum;
}  


long problem3() {
  long n = 600851475143;
  long i;
  long max;
  
  for (i = 1; i < sqrt(n); i++)
    if (n % i == 0 && isPrime(i))
      max = i;

  return max;
}

long problem4() {
  int i, j;
  int max = 0;
  
  for (i = 100; i < 1000; i++) 
    for (j = 100; j < 1000; j++)
      if (i * j > max && isPalindrome(i * j))
        max = i * j;

  return max;
}

int main() {
  printf("%ld\n", problem1());
  printf("%ld\n", problem2());
  printf("%ld\n", problem3());
  printf("%ld\n", problem4());
  return 0;
}
