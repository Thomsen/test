#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

#include <pthread.h>

static int shdata = 4;

void *create(void *argv)
{
  printf("new thread created... tid = %d\n", (unsigned int)pthread_self());
  printf("thread pid = %d, shared data = %d\n", getpid(), shdata);
  return (void *) 0;
}

int main(int argv, char *argc)
{

  int status;
  pid_t pid = fork(); // vfork() with child pid share data
  pid_t pr;

  if (pid == 0) {
    sleep(1);
    printf("fork pid = %d, shared data = %d\n", pid, shdata);
    pthread_t tidp;
    int tid;
    tid = pthread_create(&tidp, NULL, create, NULL);
    if (tid == 0) {
      printf("pthread_create is created...\n");
    } else {
      printf("pthread_create is not created...\n");
    }
  } else if (pid == -1) {
    printf("fork new process error!\n");
    exit(-1);
  } else {
    //sleep(1);
    printf("wait child process...\n");
    pr= wait(NULL);  // waitpid()
    if (WIFEXITED(status)) {
      printf("the child pid %d exit normally.\n", pr);
    } else {
      printf("this is parent pid = %d\n", pid);
    }
  }
  sleep(1);
  return 0;
}

// gcc -Wall -lpthread -o pr process.c
