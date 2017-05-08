run: init.lisp
	clisp init.lisp

clean: clean-images clean-slime clean-temp

clean-images:
	rm -f *.png *.ppm

clean-slime:
	rm -f *.fas *.lib

clean-temp:
	rm -f *~ \#*

generate-script: generate.py
	python generate.py > script
