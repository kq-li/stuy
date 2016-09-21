#include <stdio.h>
#include <stdlib.h>

int main() {
  short s = 83;
  short *sp;
  sp = &s;
  
  printf("sizeof(s) = %lu\n", sizeof(s));
  printf("s = %d\n", s);
  printf("&s = %p\n", &s);
  printf("\n");

  printf("sizeof(sp) = %lu\n", sizeof(sp));
  printf("sp = %p\n", sp);
  printf("&sp = %p\n", &sp);
  printf("*sp = %d\n", *sp);
  printf("\n");

  double darr[5];
  darr[0] = 3.7;
  darr[1] = 3.14;
  darr[2] = 20.4;
  double *dp;
  dp = darr;

  printf("sizeof(darr) = %lu\n", sizeof(darr));
  printf("darr = %p\n", darr);
  printf("&darr = %p\n", &darr);
  printf("darr[0] = %f\n", darr[0]);
  printf("*darr = %f\n", *darr);
  printf("darr[2] = %f\n", darr[2]);
  printf("*(darr + 2) = %f\n", *(darr + 2));
  printf("2[darr] = %f\n", 2[darr]);
  printf("*(2 + darr) = %f\n", *(2 + darr));
  printf("\n");

  // *dp++ results in dereference after increment
  // (*dp)++ results in increment after dereference
  
  printf("sizeof(dp) = %lu\n", sizeof(dp));
  printf("dp = %p\n", dp);
  printf("&dp = %p\n", &dp);
  printf("*dp = %f\n", *dp);
  printf("\n");

  //Doesn't work -- array pointers immutable
  //darr++;

  dp++;

  printf("sizeof(dp) = %lu\n", sizeof(dp));
  printf("dp = %p\n", dp);
  printf("&dp = %p\n", &dp);
  printf("*dp = %f\n", *dp);

  return 0;
}
