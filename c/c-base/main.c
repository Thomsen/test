#include "data_type.h"
#include "io_tools.h"

int main(int argc, char *argv[])
{

  printf("command argument: %s\n", argv[1]);
  // io_tools
  //gpchar();

  // data_type
  printf("------------ data_type example ------------\n");
  print_sizeof ();

  // pointer
  printf("\n------------ pointer example --------------\n");
  address();

  // string
  printf("\n------------ string example ---------------\n");
  char_to_string();

  // struct
  printf("\n------------ struct example ---------------\n");
  structure();

  // union
  printf("\n------------ union example ----------------\n");
  union_test();

  // bit_field
  printf("\n------------ bit_field example -----------\n");
  bit_field_test();

  // file_io
  printf("\n------------ file_io example -------------\n");
  file_io_test();

  // recursion
  printf("\n------------ recusion example -------------\n");
  recursion_test();

  // variable_args
  printf("\n------------ valist example -------------\n");
  varargs_test();

  // memory
  printf("\n------------ memory example -------------\n");
  memory_mgmt_test();

  return 0;
}
