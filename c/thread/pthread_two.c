#include "pthread_two.h"

pthread_two_test()
{
  pthread_t thread[THREAD_SIZE];

  int i;
  printf("thread size: %d\n", THREAD_SIZE);
  for (i=0; i<THREAD_SIZE; i++)
    {
      printf("thread %d creating...\n", i);
      param[i].a = i;
      param[i].b = i;
      pthread_create(&thread[i], NULL, add, &param[i]);
      printf("thread %d created\n", i);
    }

}

int *add(struct params *p)
{
  int sum = p->a + p->b;
  printf("sum : %d\t\n", sum);
  return 0;
}

