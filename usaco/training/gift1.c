/*
ID: noobbyte
LANG: C
PROG: gift1
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int find(char needle[], char haystack[][15], int len_haystack) {
  int i;
  for (i = 0; i < len_haystack; i++) {
    if (strcmp(haystack[i], needle) == 0) {
      return i;
    }
  }
  return -1;
}

int main() {
  FILE *fin = fopen("gift1.in", "r");
  FILE *fout = fopen("gift1.out", "w");
  int NP, i, j;
  fscanf(fin, "%d\n", &NP);
  int money[NP], gifts[NP], balance[NP];
  char people[NP][15];
  char name[15];
  for (i = 0; i < NP; i++) {
    balance[i] = 0;
  }
  for (i = 0; i < NP; i++) {
    fscanf(fin, "%s\n", people[i]);
    printf("%s\n", people[i]);
  }
  for (i = 0; i < NP; i++) {
    fscanf(fin, "%s\n", name);
    int cur = find(name, people, NP);
    printf("%s's position in the people array is %d.\n", name, cur);
    fscanf(fin, "%d %d\n", &money[cur], &gifts[cur]);
    balance[cur] -= money[cur];
    printf("%s has %d to give to %d people.\n", name, money[cur], gifts[cur]);
    if (gifts[cur] != 0) {
      int gift = money[cur] / gifts[cur];
      printf("%s gives gifts of size %d.\n", name, gift);
      int leftover;
      if (gift != 0) {
        leftover = money[cur] % (gift * gifts[cur]);
      } else {
        leftover = money[cur];
      }
      printf("%s keeps %d.\n", name, leftover);
      balance[cur] += leftover;
      for (j = 0; j < gifts[cur]; j++) {
        fscanf(fin, "%s\n", name);
        int recipient = find(name, people, NP);
        balance[recipient] += gift;
      }
    }
  }
  for (i = 0; i < NP; i++) {
    fprintf(fout, "%s %d\n", people[i], balance[i]);
  }
  return 0;
}