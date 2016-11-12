#include "iteration.h"

int i_fiboniacci(int n)
{
  int r = 0;
  int p = 1;
  if (n < 0)
    {
      printf("fiboniacci please input > 0\n");
    }
  else
    {
      int tn = n;
      while (0 < n--)
        {
          p = p + r;
          r = p - r;
          printf("fiboniacci iteration %d process p: %d r: %d\n", n, p, r);
        }
    }
  return p;
}

int i_hanoi(int n)
{
  int r = 0;
  if (n <= 0)
    {
      printf("hanoi please input > 0\n");
    }
  else
    {
      while (0 < n--)
        {
          r = 2 * r + 1;
          printf("hanoi iteration %d process r: %d\n", n, r);
        }
    }
  return r;
}
