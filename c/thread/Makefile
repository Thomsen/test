thread_objs = main.o pthread_one.o pthread_two.o

main: $(thread_objs)
	gcc -pthread -o main $(thread_objs)

clean:
	rm main $(thread_objs)




