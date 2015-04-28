/*
ID: kenneth10
PROG: milk2
LANG: C
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void sort(int array1[], int array2[], int array_len) {
  int i, j, tmp;
  for (i = 0; i < array_len; i++) {
    for (j = i + 1; j < array_len; j++) {
      if (array1[i] > array1[j]) {
        tmp = array1[i];
        array1[i] = array1[j];
        array1[j] = tmp;
        tmp = array2[i];
        array2[i] = array2[j];
        array2[j] = tmp;
      }
    }
  }
}

int contTime(int start1, int end1, int start2, int end2) {
  return end1 > start2 ? end2 - start1 : 0;
}

int idleTime(int start1, int end1, int start2, int end2) {
  return end1 < start2 ? start2 - end1 : 0;
}

int max(int a, int b) {
  return a > b ? a : b;
}

main() {
  FILE *fin = fopen("milk2.in", "r");
  FILE *fout = fopen("milk2.out", "w");
  int n, i, j, cont, idle, contMax = 0, idleMax = 0;
  fscanf(fin, "%d\n", &n);
  int startTime[n], endTime[n];
  for (i = 0; i < n; i++) {
    fscanf(fin, "%d %d\n", &startTime[i], &endTime[i]);
  }
  sort(startTime, endTime, n);
  if (n != 1) {
    for (i = 0; i < n; i++) {
      for (j = i; j < n; j++) {
        if (startTime[j] <= endTime[i]) {
          startTime[j] = startTime[i];
          endTime[i] = max(endTime[i], endTime[j]);
          endTime[j] = endTime[i];
        }
      }
      printf("Effective range: %d to %d\n", startTime[i], endTime[i]);
    }
    for (i = 0; i < n - 1; i++) {
      cont = endTime[i] - startTime[i];
      idle = idleTime(startTime[i], endTime[i], startTime[i + 1], endTime[i + 1]);
      printf("Cont: %d  Idle: %d\n", cont, idle);
      if (cont > contMax)
        contMax = cont;
      if (idle > idleMax)
        idleMax = idle;
    }
  } else {
    contMax = endTime[0] - startTime[0];
    idleMax = 0;
  }
  printf("Max continous time: %d\n", contMax);
  printf("Max idle time: %d\n", idleMax);
  fprintf(fout, "%d %d\n", contMax, idleMax);
  exit(0);
}





