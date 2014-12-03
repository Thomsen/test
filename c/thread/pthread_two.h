#ifndef __PTHREAD_TWO_H
#define __PTHREAD_TWO_H

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define THREAD_SIZE 10

struct params
{
  int a;
  int b;
} param[THREAD_SIZE];

int *add(struct params *p);
int *sub(struct params *p);

#endif /* __PTHREAD_TWO_H */
