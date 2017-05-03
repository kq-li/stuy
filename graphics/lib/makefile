run: init.lisp
	clisp init.lisp

clean: clean-images clean-slime clean-temp

clean-images:
	rm *.png *.ppm

clean-slime:
	rm *.fas *.lib

clean-temp:
	rm *~ \#*

generate-script: generate.py
	python generate.py > script
