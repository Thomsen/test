#include "union_type.h"

union data
{
  int i;
  float f;
  char str[20];
};

void print_data(union data dt);

void union_test()
{
  union data d1;

  printf("memory size occupied by data: %d\n", sizeof(d1));

  d1.i = 200;
  d1.f = 20.0;
  strcpy(d1.str, "C Programming");

  print_data(d1);

  union data d2;

  d2.i = 200;
  printf("d2 i value: %d\n", d2.i);

  d2.f = 20.0;
  printf("d2 f value: %f\n", d2.f);

  strcpy(d2.str, "C Programming");
  printf("d2 str value: %s\n", d2.str);
}

void print_data(union data dt)
{
  printf("data i value: %d\n", dt.i);
  printf("data f value: %f\n", dt.f);
  printf("data str value: %s\n", dt.str);

}
