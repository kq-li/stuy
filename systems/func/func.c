#include <stdio.h>
#include <stdlib.h>

int add(int, int);
void blarg();

int add(int a, int b) {
  return a + b;
}

void blargValue(int n) {
  while (n--) {
    printf("%s\n", "blarg");
  }

  printf("n = %d\n", n);
}

void blargReference(int *n) {
  while ((*n)--) {
    printf("%s\n", "blarg");
  }

  printf("*n = %d\n", *n);
}

int main() {
  int i = 4;
  blargValue(i);
  printf("i = %d\n", i);
  blargReference(&i);
  printf("i = %d\n", i);
  return 0;
}
