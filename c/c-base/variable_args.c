#include "variable_args.h"

void varargs_test()
{
  printf("the (3, 4, 5, 6) average is: %f\n", average(4, 3, 4, 5, 6));
}

double average(int num, ...)
{
  if (num == 0)
    {
      printf("num is zero, exiting...\n");
      exit(EXIT_FAILURE);
    }

  va_list valist;

  double sum = 0.0;
  int i;

  va_start(valist, num);

  for (i=0; i<num; i++)
    {
      sum += va_arg(valist, int);
    }

  va_end(valist);

  return sum / num;
}
