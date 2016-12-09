all: exec.c child.c
	gcc exec.c -o exec
	gcc child.c -o child

clean:
	rm -f exec child

run: exec
	./exec
