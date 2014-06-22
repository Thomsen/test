#include "string_type.h"

void char_to_string()
{
  char val[] = "hello";
  printf("the val: %s\n", val);

  char val2[] = "world";

  char val3[20];

  printf("strcpy(val3, val): %s\n", strcpy(val3, val));
  printf("strcat(val3, val2): %s\n", strcat(val3, val2));

  printf("val3 length: %d\n", strlen(val3));
}
