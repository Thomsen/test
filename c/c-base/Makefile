objs = main.o data_type.o io_tools.o pointer.o string_type.o \
	structure.o union_type.o bit_field.o file_io.o recursion.o \
	variable_args.o memory_mgmt.o

#
ALL: $(objs)
	gcc -Wall -o main $(objs) -lm

main: ALL
	./main

clean: $(objs)
	rm main $(objs)

main.o: main.c
	gcc -c main.c

data_type.o: data_type.c
	gcc -c data_type.c

io_tools.o: io_tools.c
	gcc -c io_tools.c

pointer.o: pointer.c
	gcc -c pointer.c

string_type.o: string_type.c
	gcc -c string_type.c

structure.o: structure.c
	gcc -c structure.c

union_type.o: union_type.c
	gcc -c union_type.c

bit_field.o: bit_field.c
	gcc -c bit_field.c

file_io.o: file_io.c
	gcc -c file_io.c

recursion.o: recursion.c
	gcc -c recursion.c

variable_args.o: variable_args.c
	gcc -c variable_args.c

memory_mgmt.o: memory_mgmt.h
	gcc -c memory_mgmt.c
