run: draw.lisp test.lisp
	clisp test.lisp
	convert image.ppm image.png

clean:
	rm -f *~

clean-images:
	rm *.png *.ppm

display-png: image.png
	display image.png

display-ppm: image.ppm
	display image.ppm
