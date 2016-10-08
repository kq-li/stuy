#include <stdio.h>
#include <stdlib.h>

struct node {
  int data;
  struct node *next;
};
  
void printList(struct node *start) {
  if (start) {
    printf("%d ", start->data);
    printList(start->next);
  } else {
    printf("\n");
  }
}

struct node *insertFront(struct node *start, int newData) {
  struct node *newStart;
  newStart = (struct node *) malloc(sizeof(struct node));
  newStart->data = newData;
  newStart->next = start;
  return newStart;
}

struct node *freeList(struct node *start) {
  struct node *ret, *next;
  next = start;
  printf("%lu\n", sizeof(struct node));
  
  while (start) {
    next = start->next;
    free(start);
    start = next;
  }

  return ret;
}
  
int main() {
  struct node *n0, *n1, *n2, *n3;

  n1 = (struct node *) malloc(sizeof(struct node));
  n2 = (struct node *) malloc(sizeof(struct node));
  n3 = (struct node *) malloc(sizeof(struct node));

  n1->data = 5;
  n2->data = 8;
  n3->data = 13;

  n1->next = n2;
  n2->next = n3;

  printList(n1);

  n0 = insertFront(n1, 3);
  printList(n0);

  freeList(n0);
  printList(n0);

  return 0;
}
