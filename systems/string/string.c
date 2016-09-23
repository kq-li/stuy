#include <stdio.h>
#include <stdlib.h>

int main() {
  char s[1024];

  s[0] = 'c';
  s[1] = 'a';
  s[2] = 't';
  s[3] = '\0';

  printf("s = %s\n", s);
  printf("sizeof(s) = %lu\n", sizeof(s));

  return 0;
}
