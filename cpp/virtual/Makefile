all: print
	./print

print.o: sub_print.cpp
	g++ -c sub_print.cpp -o print.o

print: print.o
	g++ print.o -o print

.PHONY: clean

clean: 
	rm print.o print