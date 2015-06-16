
#include <error.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>

char command[256];

void main()
{
  int rtn;

  while(1) {
    printf(">");
    fgets(command, 256, stdin);

    if (fork() == 0) {
      execlp(command, command);

      perror(command);

      exit(-1);
    } else {
      wait(&rtn);
      printf("child process return %d\n", rtn);
    }
  }
}
