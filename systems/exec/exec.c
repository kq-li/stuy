#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
	printf("Ping! PID: %d PPID: %d\n", getpid(), getppid());
	execlp("./child", "child", NULL);
	
	return 0;
}
