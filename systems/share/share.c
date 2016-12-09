#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
	int sd = shmget(347, 16, IPC_CREAT | 0644);
	int *ip = shmat(sd, 0, 0);
	
	if (fork()) {
		wait(NULL);
	} else {
		i = 1;
	}

	printf("%d\n", i);

	return 0;
}
