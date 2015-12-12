/*
ID: noobbyte
LANG: C
PROG: transform
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

void rot90(char **preimage, char **image, int size) {
  int i, j;
  for (i = 0; i < size; i++) {
    char string[size];
    for (j = 0; j < size; j++) {
      string[j] = preimage[size - j - 1][i];
    }
    strcpy(image[i], string);
  }
}

void rot180(char **preimage, char **image, int size) {
  char temp[size][size];
  rot90(preimage, temp, size);
  rot90(temp, image, size);
}


void rot270(char **preimage, char **image, int size) {
  char temp1[size][size];
  char temp2[size][size];
  rot90(size, preimage, temp1);
  rot90(size, temp1, temp2);
  rot90(size, temp2, image);
}


void refX(char **preimage, char **image, int size) {
  int i, j;
  for (i = 0; i < size; i++) {
    char string[size];
    for (j = 0; j < size; j++) {
      string[j] = preimage[i][size - j - 1];
    }
    strcpy(image[i], string);
  }
  }*/

int compareArrays(int size, char array1[][size], char array2[][size]) {
  int i, j;
  for (i = 0; i < size; i++)
    for (j = 0; j < size; j++)
      if (array1[i][j] != array2[i][j])
        return 0;
  return 1;
}

void printArray(char *array[], int size) { 
  int i, j;
  for (i = 0; i < size; i++) {
    for (j = 0; j < size; j++) {
      printf("%c", array[i][j]);
    }
    printf("\n");
  }
  printf("\n");
}
  
int main() {
  FILE *fin = fopen("transform.in", "r");
  FILE *fout = fopen("transform.out", "w");

  int i;
  int size = 5;
  char *preimage[5];
  char *image[5];
  char *correct[5];

  for (i = 0; i < size; i++) {
    preimage[i] = (char *) malloc(size * sizeof(char));
    image[i] = (char *) malloc(size * sizeof(char));
    correct[i] = (char *) malloc(size * sizeof(char));
  }
  
  strcpy(preimage[0], "@.@@@");
  strcpy(preimage[1], ".@@.@");
  strcpy(preimage[2], "...@.");
  strcpy(preimage[3], ".@...");
  strcpy(preimage[4], "@.@@.");

  strcpy(correct[0], ".@@.@");
  strcpy(correct[1], "...@.");
  strcpy(correct[2], ".@...");
  strcpy(correct[3], "@.@@.");
  strcpy(correct[4], "@@@.@");
  /*
  rot90(size, preimage, image);
  if (compareArrays(size, correct, image))
    printf("0\n");
    else {
    rot180(size, preimage, image);
    if (compareArrays(size, correct, image))
      printf("1\n");
    else {
      rot270(size, preimage, image);
      if (compareArrays(size, correct, image))
        printf("2\n");
      else {
        refX(size, preimage, image);
        if (compareArrays(size, correct, image))
          printf("3\n");
        else {
          
          printf("4\n");
      }
    }
    }*/
    
  
  printArray(preimage, 5);
  rot90(preimage, image, 5);
  printArray(image, 5);
  /*
  rot180(5, preimage, image);
  printArray(5, image);
  rot270(5, preimage, image);
  printArray(5, image);
  refX(5, preimage, image);
  printArray(5, image);
  */

  return 0;
}
