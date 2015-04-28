/*
ID: kenneth10
PROG: friday
LANG: C
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int isLeapYear(int year) {
  return (year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0);
}

int numDays(int month, int year) {
  switch (month) {
    case 2:
      if (isLeapYear(year))
        return 29;
      else
        return 28;
      break;
    case 4: case 6: case 9: case 11:
      return 30;
      break;
    default:
      return 31;
      break;
  }
  return -1;
}

int find(char needle, char haystack[], int len_haystack) {
  int i;
  for (i = 0; i < len_haystack; i++) {
    if (haystack[i] == needle) {
      return i;
    }
  }
  return -1;
}

main() {
  FILE *fin = fopen("friday.in", "r");
  FILE *fout = fopen("friday.out", "w");
  int day, month, year, endYear;
  char today = 'M';
  char days[7] = {'S', 'U', 'M', 'T', 'W', 'H', 'F'};
  int num13[7] = {0};
  fscanf(fin, "%d\n", &endYear);
  endYear += 1900;
  for (year = 1900; year < endYear; year++) {
    for (month = 1; month <= 12; month++) {
      for (day = 1; day <= numDays(month, year); day++) {
        if (day == 13) {
          num13[find(today, days, 7)]++;
        }
        today = days[(find(today, days, 7) + 1) % 7];
      }
    }
  }
  for (day = 0; day < 6; day++) {
    fprintf(fout, "%d ", num13[day]);
  }
  fprintf(fout, "%d\n", num13[6]);
  exit(0);
}
