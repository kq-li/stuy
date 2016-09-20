#include <stdio.h>
#include <stdlib.h>

int main() {
  short s = 83;
  short *sp;
  sp = &s;
  
  printf("size of s: %lu\n", sizeof(s));
  printf("value of s: %d\n", s);
  printf("address of s: %p\n", &s);

  printf("size of sp: %lu\n", sizeof(sp));
  printf("value of sp: %p\n", sp);
  printf("address of sp: %p\n", &sp);
  printf("sp -> %d\n", *sp);

  sp++;

  printf("size of sp: %lu\n", sizeof(sp));
  printf("value of sp: %p\n", sp);
  printf("address of sp: %p\n", &sp);
  printf("sp -> %d\n", *sp);

  return 0;
}
