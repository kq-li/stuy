#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
	printf("Pong! PID: %d PPID: %d\n", getpid(), getppid());

	return 0;
}
