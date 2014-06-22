#include "data_type.h"

void learn_complex (void)
{
  complex double z = 1.0 + 3.0*I;
  printf ("Phase is %f, modulus is %f\n", carg(z), cabs(z));
}

void print_sizeof()
{
  printf("storage size for char : %d byte\n", sizeof(char));
  printf("storage size for unsigned char : %d byte\n", sizeof(unsigned char));
  printf("storage size for int : %d byte\n", sizeof(int));
  printf("storage size for unsigned int : %d byte\n", sizeof(unsigned int));
  printf("storage size for short : %d byte\n", sizeof(short));
  printf("storage size for unsigned short : %d byte\n", sizeof(unsigned short));
  printf("storage size for long : %d byte\n", sizeof(long));
  printf("storage size for unsigned long : %d byte\n", sizeof(unsigned long));

  printf("storage size for float : %d byte\n", sizeof(float));
  printf("storage size for double : %d byte\n", sizeof(double));
  printf("storage size for long double : %d byte\n", sizeof(long double));

  printf("minimum float positive value : %E\n", FLT_MIN);
  printf("maximum float positive value : %E\n", FLT_MAX);
  printf("precision value : %d\n", FLT_DIG); // 精度
}
