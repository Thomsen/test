#include "recursion.h"

int count = 0;

int r_fiboniacci(int n)
{
  count ++;
  int r = 0;
  if (n < 0) {
    printf("fiboniacci please input > 0\n");
  }
  else if (n == 0)
  {
    r = 0;
  }
  else if (n == 1) {
    r = 1;
  }
  else
  {
    r = r_fiboniacci(n-1) + r_fiboniacci(n-2);
  }
  printf("fiboniacci recursion %d process: %d\n", count, r);

  return r;
}

int r_hanoi(int n)
{
  int r = 0;
  if (n < 0)
    {
      printf("hanoi please input > 0\n");
    }
  else if (n ==0 ) {
    r = 0;
  }
  else {
    r = 2 * r_hanoi(n - 1) + 1;
    printf("hanoi recursion %d process: %d\n", n, r);
  }
  return r;
}

void r_hanoi_opt(int n, char x, char y, char z)
{
  printf("operation step: %d\n", n);
  if (n == 0)
    {
      printf("no operation\n", n);
    }
  else
    {
      r_hanoi_opt(n-1, x, z, y);
      printf("target step: %c -> %c \n", x, y);
      r_hanoi_opt(n-1, z, y, x);
    }
}
