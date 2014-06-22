#include "io_tools.h"

void gpchar(void)
{
  int c;
  // version one
  /*
  c = getchar();
  while (c != EOF)
    {
      putchar(c);
      c = getchar();
    }
  */

  // printf("getchar() != EOF %d", (getchar() != EOF));  // value 1
  // printf("EOF %d", EOF);  // value -1

  // version two
  while ((c = getchar()) != EOF)
    {
      putchar(c);
    }

}
