#include <stdio.h>
#include <stdlib.h>
#include <string.h>

long unsigned int my_strlen(char s[]) {
  long unsigned int length = 0;

  while (s[length++]);

  return --length;
}

void my_strcpy(char *dest, char *src) {
  while (*src)
    *(dest++) = *(src++);
}

void my_strncpy(char *dest, char *src, size_t length) {
  int i;

  for (i = 0; i < length; i++)
    *(dest++) = *(src++);
}

char *my_strcat(char *s1, char *s2) {
  char *cp = malloc(my_strlen(s1) + my_strlen(s2));
  my_strcpy(cp, s1);
  my_strcpy(cp + my_strlen(s1), s2);
  return cp;
}

int main() {
  char s[1024] = "cat";
  char my_s[1024] = "cat";

  printf("s = %s\n", s);
  printf("my_s = %s\n", my_s);

  strcpy(s, "dog");
  my_strcpy(my_s, "dog");

  printf("s = %s\n", s);
  printf("my_s = %s\n", my_s);

  strncpy(s, "turtle", 3);
  my_strncpy(my_s, "turtle", 3);

  printf("s = %s\n", s);
  printf("my_s = %s\n", my_s);
  printf("my_strcat(s, my_s) = %s\n", my_strcat(s, my_s));

  return 0;
}
