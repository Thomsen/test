#include "pointer.h"

void address()
{
  char val;
  char vals[10];

  printf("address of val variable: %x\n", &val);
  printf("address of vals variable: %x\n", &vals);


  val = 'a';

  char *pc;

  pc = &val;

  printf("value of *pc variable: %c\n", *pc);
}
