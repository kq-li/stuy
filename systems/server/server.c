#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/stat.h>

int main() {
	int toClient, fromClient;
	
	printf("[SERVER] Initialized program\n");
	printf("[SERVER] Created pipe\n");

	mkfifo("fromClient", 0644);
	fromClient = open("fromClient", O_RDONLY);

	printf("[SERVER] Detected connection on pipe\n");

	char buf[1024];
	read(fromClient, buf, 1024);

	printf("[SERVER] Received client message: \"%s\"\n", buf);
	
	toClient = open(buf, O_WRONLY);

	printf("[SERVER] Successfully connected to client\n");

	write(toClient, "[SERVER] Ping!", 15);

	printf("[SERVER] Sent message to client\n");
	
	return 0;
}
