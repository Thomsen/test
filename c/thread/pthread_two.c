#include "pthread_two.h"

int acon;
int sum;

pthread_two_test()
{
  pthread_t thread[THREAD_SIZE];

  int i;
  printf("thread size: %d\n", THREAD_SIZE);
  for (i=0; i<THREAD_SIZE; i++)
    {
      printf("add thread %d creating...\n", i);
      param[i].a = sum;
      param[i].b = i;
      pthread_create(&thread[i], NULL, add, &param[i]);
      printf("add thread %d created\n", i);
    }

  for (i=THREAD_SIZE-1; i>=0; --i)
    {
      printf("sub thread %d creating...\n", i);
      param[i].a = sum;
      param[i].b = i;
      pthread_create(&thread[i], NULL, sub, &param[i]);
      printf("sub thread %d created\n", i);
    }

}

int *add(struct params *p)
{
  sum = p->a + p->b;
  printf("add sum : %d\t\n", sum);
  return sum;
}

int *sub(struct params *p)
{
  sum = p->a - p->b;
  printf("sub sum: %d\t\n", sum);
  return sum;
}
