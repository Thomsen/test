#include <string.h>

#include "recursion.h"

#include "iteration.h"

void main(int argc, char *argv[])
{
  int n = 15;
  char* t = "-i";
  if (argc == 3)
  {
    t = argv[1];

    char* c_n = argv[2];
    n = atoi(c_n);

  }

  if (strcmp(t, "-i") == 0)
    {
      printf("i_fiboniacci(%d) = %d\n", n, i_fiboniacci(n));
      printf("i_hanoi(%d) = %d\n", n, i_hanoi(n));
    }
  else if (strcmp(t, "-r") == 0)
    {
      printf("r_fiboniacci(%d) = %d\n", n, r_fiboniacci(n));
      printf("r_hanoi(%d) = %d\n", n, r_hanoi(n));
      r_hanoi_opt(n, 'A', 'B', 'C');
    }

}
