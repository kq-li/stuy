/*
ID: noobbyte
LANG: C
PROG: transform
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

void rot90(char **preimage, char **image, int N) {
  int i, j;
  for (i = 0; i < N; i++) {
    char string[N];
    for (j = 0; j < N; j++) {
      string[j] = preimage[N - j - 1][i];
    }
    strcpy(image[i], string);
  }
}

void rot180(char **preimage, char **image, int N) {
  int i, j;
  for (i = 0; i < N; i++) {
    char string[N];
    for (j = 0; j < N; j++) {
      string[j] = preimage[j][i];
    }
    strcpy(image[i], string);
  }
}


void rot270(char **preimage, char **image, int N) {
  int i, j;
  for (i = 0; i < N; i++) {
    char string[N];
    for (j = 0; j < N; j++) {
      string[j] = preimage[j][N - i - 1];
    }
    strcpy(image[i], string);
  }
}


void refX(char **preimage, char **image, int N) {
  int i, j;
  for (i = 0; i < N; i++) {
    char string[N];
    for (j = 0; j < N; j++)
      string[j] = preimage[i][N - j - 1];
    strcpy(image[i], string);
  }
}

int compareArrays(char **a, char **b, int N) {
  int i, j;
  for (i = 0; i < N; i++)
    for (j = 0; j < N; j++)
      if (a[i][j] != b[i][j])
        return 0;
  return 1;
}

void printArray(char **array, int N) { 
  int i, j;
  for (i = 0; i < N; i++) {
    for (j = 0; j < N; j++)
      printf("%c", array[i][j]);
    printf("\n");
  }
  printf("\n");
}
  
int main() {
  FILE *fin = fopen("transform.in", "r");
  FILE *fout = fopen("transform.out", "w");

  int i, j, N;
  
  fscanf(fin, "%d\n", &N);
  
  char *preimage[N];
  char *image[N];
  char *correct[N];
  char *temp[N];

  for (i = 0; i < N; i++) {
    preimage[i] = (char *) malloc(N * sizeof(char));
    image[i] = (char *) malloc(N * sizeof(char));
    correct[i] = (char *) malloc(N * sizeof(char));
  }
  
  for (i = 0; i < N; i++) {
    char string[N];
    fscanf(fin, "%s\n", string);
    strcpy(preimage[i], string);
  }
  
  for (i = 0; i < N; i++) {
    char string[N];
    fscanf(fin, "%s\n", string);
    strcpy(correct[i], string);
  }

  for (i = 1; i <= 7; i++) {
    int isCombo = 0;

    switch(i) {
    case 1:
      rot90(preimage, image, N);
      break;
    case 2:
      rot180(preimage, image, N);
      break;
    case 3:
      rot270(preimage, image, N);
      break;
    case 4:
      refX(preimage, image, N);
      break;
    case 5:
      for (j = 0; j < N; j++)
        temp[j] = (char *) malloc(N * sizeof(char));
      refX(preimage, temp, N);
      rot90(temp, image, N);
      isCombo = compareArrays(image, correct, N) || isCombo;
      rot180(temp, image, N);
      isCombo = compareArrays(image, correct, N) || isCombo;
      rot270(temp, image, N);
      isCombo = compareArrays(image, correct, N) || isCombo;
      break;
    case 6:
    case 7:
      break;
    }
    
    if (compareArrays(image, correct, N) || isCombo)
      fprintf(fout, "%d\n");
  }
  return 0;
}
