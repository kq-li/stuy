#include <stdio.h>
#include <stdlib.h>

int main() {
  int *p, i;
  p = (int *) malloc(5 * sizeof(int));

  p[0] = 0;
  p[9] = 2000;

  for (i = 0; i < 10; ++i)
    printf("%d ", p[i]);

  printf("\n");
  
  return 0;
}

