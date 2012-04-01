comp: compare.o
	g++ compare.o -o compare

compare.o: compare.cpp
	g++ -c compare.cpp -o compare.o

.PHONY: clean
clean: compare.o compare
	rm compare.o compare