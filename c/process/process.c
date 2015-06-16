#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

#include <pthread.h>

void *create(void *argv)
{
  printf("new thread created...\n");
}

int main(int argv, char *argc)
{

  int status;
  pid_t pid = fork(); // vfork() with child pid share data
  pid_t pr;

  if (pid == 0) {
    printf("fork pid = %d\n", pid);
    pthread_t tidp;
    int error;
    error = pthread_create(&tidp, NULL, create, NULL);
    if (error == 0) {
      printf("pthread_create is created...\n");
    } else {
      printf("pthread_create is not created...\n");
    }
  } else if (pid == -1) {
    printf("fork new process error!\n");
    exit(-1);
  } else {
    printf("wait child process...\n");
    pr= wait(NULL);  // waitpid()
    if (WIFEXITED(status)) {
      printf("the child pid %d exit normally.\n", pr);
    } else {
      printf("this is parent pid = %d\n", pid);
    }
  }

  return 0;
}

// gcc -Wall -lpthread -o pr process.c
