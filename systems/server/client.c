#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/stat.h>

int main() {
	int fromClient, toClient;
	char *fifo = "client0";
	
	printf("[CLIENT] Initialized program\n");
	printf("[CLIENT] Created pipe\n");

	fromClient = open("fromClient", O_WRONLY);
	write(fromClient, fifo, strlen(fifo));

	mkfifo(fifo, 0644);
	toClient = open(fifo, O_RDONLY);

	printf("[CLIENT] Detected connection on pipe\n");

	char buf[1024];
	read(toClient, buf, 1024);
	printf("[CLIENT] Received server message: \"%s\"\n", buf);

	return 0;
}
