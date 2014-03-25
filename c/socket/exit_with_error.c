#include <stdio.h> /* for perror() */
#include <stdlib.h> /* for exit() */
#include <time.h> /* for time() */
#include <sys/timeb.h> /* for timeb() */

void exit_with_error(char *errorMessage)
{
/*
  time_t curtime;
  curtime = time(NULL);
  printf(ctime(&curtime));
*/
  /*
  struct timeb timebuffer;
  ftime(&timebuffer);
  printf("ms: %ld\n", (timebuffer.time*1000 + timebuffer.millitm));
  */
  struct timeval curtime;
  gettimeofday(&curtime, NULL);
  printf("us: %d\n", (curtime.tv_sec*1000000 + curtime.tv_usec));
  perror(errorMessage);
  exit(1);
}

