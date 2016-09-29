#include <stdio.h>
#include <stdlib.h>
#include <string.h>

size_t my_strlen(char s[]) {
  size_t length = 0;

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

char *my_strcat(char *dest, char *src) {
  my_strcpy(dest + my_strlen(dest), src);
  return dest;
}

char *my_strncat(char *dest, char *src, size_t length) {
  my_strncpy(dest + my_strlen(dest), src, length);
  return dest;
}

int my_strcmp(char *s1, char *s2) {
  while (*s1 && *s2) {
    int test = *(s1++) - *(s2++);

    if (test != 0)
      return test;
  }

  return 0;
}

char *my_strchr(char *s, char c) {
  while (*(s++))
    if (*s == c)
      return s;

  return NULL;
}

char *my_strstr(char *s1, char *s2) {
  while (*(s1++))
    if (*s1 && my_strcmp(s1, s2) == 0)
      return s1;

  return NULL;
}

int main() {
  char s[1024] = "cat";
  char my_s[1024] = "cat";

  printf("INITIAL:\n");
  printf("s = %s\n", s);
  printf("my_s = %s\n", my_s);
  printf("\n");

  strcpy(s, "dog");
  my_strcpy(my_s, "dog");

  printf("strcpy(s, \"dog\")\n");
  printf("my_strcpy(my_s, \"dog\")\n");
  printf("s = %s\n", s);
  printf("my_s = %s\n", my_s);
  printf("\n");

  strncpy(s, "turtle", 3);
  my_strncpy(my_s, "turtle", 3);

  printf("strncpy(s, \"turtle\", 3)\n");
  printf("my_strncpy(my_s, \"turtle\", 3)\n");
  printf("s = %s\n", s);
  printf("my_s = %s\n", my_s);
  printf("\n");

  printf("strcat(s, \"bat\") = %s\n", strcat(s, "bat"));
  printf("my_strcat(my_s, \"bat\") = %s\n", my_strcat(my_s, "bat"));
  printf("s = %s\n", s);
  printf("my_s = %s\n", my_s);
  printf("\n");

  printf("strcmp(s, \"turbat\") = %d\n", strcmp(s, "turbat"));
  printf("strcmp(s, \"turdog\") = %d\n", strcmp(s, "turdog"));
  printf("my_strcmp(my_s, \"turbat\") = %d\n", my_strcmp(my_s, "turbat"));
  printf("my_strcmp(my_s, \"turdog\") = %d\n", my_strcmp(my_s, "turdog"));
  printf("\n");

  printf("strchr(s, 'u') = %s\n", strchr(s, 'u'));
  printf("strchr(s, 'j') = %s\n", strchr(s, 'j'));
  printf("my_strchr(my_s, 'u') = %s\n", my_strchr(my_s, 'u'));
  printf("my_strchr(my_s, 'j') = %s\n", my_strchr(my_s, 'j'));
  printf("\n");
  
  printf("strstr(s, \"rba\") = %s\n", strstr(s, "rba"));
  printf("strstr(s, \"rbb\") = %s\n", strstr(s, "rbb"));
  printf("my_strstr(my_s, \"rba\") = %s\n", my_strstr(my_s, "rba"));   
  printf("my_strstr(my_s, \"rbb\") = %s\n", my_strstr(my_s, "rbb"));   

  return 0;
}
