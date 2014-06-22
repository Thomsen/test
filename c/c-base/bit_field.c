#include "bit_field.h"

void bit_field_test()
{
  printf("memory size occupied by status1: %d\n", sizeof(status1));
  printf("memory size occupied by status2: %d\n", sizeof(status2));

  status1.width_validated = 0;
  printf("status1 width_validated value: %d\n", status1.width_validated);

  status1.width_validated = 4;
  printf("status1 width_validated value: %d\n", status1.width_validated);

  status1.width_validated = 8;
  printf("status1 width_validated value: %d\n", status1.width_validated);

  status2.width_validated = 0;
  printf("status2 width_validated value: %d\n", status2.width_validated);

  status2.width_validated = 3;
  printf("status2 width_validated value: %d\n", status2.width_validated);

  status2.width_validated = 4;
  printf("status2 width_validated value: %d\n", status2.width_validated);

  status2.width_validated = 5;
  printf("status2 width_validated value: %d\n", status2.width_validated);

  status2.height_validated = 0;
  printf("status2 height_validated value: %d\n", status2.height_validated);

  status2.height_validated = 3;
  printf("status2 height_validated value: %d\n", status2.height_validated);

  status2.height_validated = 4;
  printf("status2 height_validated value: %d\n", status2.height_validated);

}

