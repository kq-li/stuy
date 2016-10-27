#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>

int main() {
  int fd = open("test", O_CREAT | O_EXCL, 0666);
  printf("fd = %d\n", fd);
  printf(strerror(17));
  
  if (fd < 0)
    printf("Error %d - %s\n", errno, strerror(errno));

  close(fd);
  
  return 0;
}
