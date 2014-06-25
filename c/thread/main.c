#include <stdio.h>

int main(int argc, char argv[])
{
  // pthread_one
  printf("================ pthread_one ================\n");
  //pthread_one_test();

  // pthread_two
  printf("\n================ pthread_two ===============\n");
  pthread_two_test();

}
