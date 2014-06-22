#include "recursion.h"

void recursion_test()
{
  printf("factorial of 8 is: %d\n", factorial(8));

  int i;
  printf("fibonaci of %d is: ", SIZE);
  for (i=0; i<10; i++)
    {
      printf("%d\t", fibonaci(i));
    }
  printf("\n");
}


int factorial(unsigned int i)
{
  if (i == 0)
    {
      return 1;
    }
  return i * factorial(i -1);
}

int fibonaci(int i)
{
  if (i == 0)
    {
      return 0;
    }
  if (i == 1)
    {
      return 1;
    }
  return fibonaci(i-2) + fibonaci(i-1);
}
