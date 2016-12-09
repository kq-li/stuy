#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
	int i = 0;

	if (fork()) {
		wait(NULL);
	} else {
		i = 1;
	}

	printf("%d\n", i);

	return 0;
}
