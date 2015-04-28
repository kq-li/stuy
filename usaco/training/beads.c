/*
ID: kenneth10
PROB: beads
LANG: C
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

char switchBead(char bead) {
  if (bead == 'r')
    return 'b';
  else if (bead == 'b')
    return 'r';
  else
    return 'w';
}

main() {
  FILE *fin = fopen("beads.in", "r");
  FILE *fout = fopen("beads.out", "w");
  int n, i = 0, j, k, breakpoint, switched = 0, max = 0;
  char curBead;
  fscanf(fin, "%d\n", &n);
  char necklace[n];
  fscanf(fin, "%s", necklace);
  for (i = 0; i < n; i++) {
    if (necklace[i] == 'w') {
      for (k = i; necklace[k % n] == 'w' && k < n; k++)
        printf("Detected w bead at %d, finding next colored bead...\n", k);
      curBead = necklace[k % n];
      printf("Next colored bead detected at bead %d; curBead is %c.\n", k, curBead);
    } else {
      curBead = necklace[i];
    }
    printf("Starting after bead %d (%c)...\n", i, necklace[i]);
    printf("curBead is %c.\n", curBead);
    for (j = 0; (necklace[(j + i) % n] == curBead || necklace[(j + i) % n] == 'w') && j < n; j++)
      printf("%c bead detected!\n", necklace[(j + i) % n]);
    
    curBead = switchBead(curBead);
    breakpoint = i + j;
    for ( ; (necklace[(j + i) % n] == curBead || necklace[(j + i) % n] == 'w') && j < n; j++)
      printf("%c bead detected!\n", necklace[(j + i) % n]);
    if (j > max)
      max = j;
    printf("Detected a total of %d collectible beads when broken after %d; the current max is %d.\n\n", j, breakpoint, max);
  }
  fprintf(fout, "%d\n", max);
  printf("%d\n", max);
  exit(0);
}
