
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <wait.h>
#define MAX_LINE 80

int main()
{
  int apipe[2], ret;
  char buf[MAX_LINE+1];
  const char *testbuf = "a test string";
  if (pipe(apipe) == 0) {
    if (fork() == 0) {
      ret = read(apipe[0], buf, MAX_LINE);
      buf[ret] = 0;
      printf("child read %s\n", buf);

    } else {
      ret = write(apipe[1], testbuf, strlen(testbuf));
      ret = wait(NULL);
    }
  }
  close(apipe[0]);
  close(apipe[1]);
  return 0;
}
