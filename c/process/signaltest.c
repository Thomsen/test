
#include <signal.h>
#include <unistd.h>
#include <stdio.h>

#include <sys/time.h>

void sigroutine(int dunno)
{
  switch (dunno)
    {
    case 1:
      printf("get a signal-SIGHUB\n"); // kill -HUP pid
      break;
    case 2:
      printf("get a signal-SIGINT\n"); // ctrl+c
      break;
    case 3:
      printf("get a signal-SIGQUIT\n"); // ctrl+\
      break;
    default:
      break;
    }
  return ;
}

void sigroutine_send(int signo)
{
  switch (signo)
    {
    case SIGALRM:
      printf("catch a signal-SIGALRM\n");
      break;
    case SIGVTALRM:
      printf("catch a signal-SIGVTALRM\n");
      break;
    default:
      break;
    }
  return;
}

// kill -9 pid

int sec;

int main()
{
  struct itimerval value, ovalue, value2;
  sec = 5;
  printf("process id is %d\n", getpid());
  //signal(SIGHUP, sigroutine);
  //signal(SIGINT, sigroutine);
  //signal(SIGQUIT, sigroutine);

  signal(SIGALRM, sigroutine_send);
  signal(SIGVTALRM, sigroutine_send);

  value.it_value.tv_sec = 1;
  value.it_value.tv_usec = 0;
  value.it_interval.tv_sec = 1;
  value.it_interval.tv_usec = 0;
  setitimer(ITIMER_REAL, &value, &ovalue);

  value2.it_value.tv_sec = 0;
  value2.it_value.tv_usec = 500000;
  value2.it_interval.tv_sec = 0;
  value2.it_interval.tv_usec = 500000;
  setitimer(ITIMER_VIRTUAL, &value2, &ovalue);

  for (;;);  // pid
}

// application pid
