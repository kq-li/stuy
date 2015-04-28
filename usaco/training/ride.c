/*
ID: noobbyte
LANG: C
PROG: ride
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
main() {
  FILE *fin = fopen("ride.in", "r");
  FILE *fout = fopen("ride.out", "w");
  char alphabet[] = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  char comet[8], group[8];
  char i;
  int c = 1, g = 1, j = 0;
  
  fscanf(fin, "%s\n%s\n", comet, group);
  printf("%s %s\n", comet, group);
  for (i = 0; i < strlen(comet); i++) {
    for (j = 1; j <= 26; j++) {
      if (comet[i] == alphabet[j]) {
        printf("%d\n", j);
        c *= j;
        break;
      }
    }
  }
  for (i = 0; i < strlen(group); i++) {
    for (j = 1; j <= 26; j++) {
      if (group[i] == alphabet[j]) {
        printf("%d\n", j);
        g *= j;
        break;
      }
    }
  }
  printf("%d %d\n", c, g);
  if (c % 47 == g % 47) {
    fprintf(fout, "GO\n");
  } else {
    fprintf(fout, "STAY\n");
  }
  printf("%d %d\n", c % 47, g % 47);
  exit(0);
}