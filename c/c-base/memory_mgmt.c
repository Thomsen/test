#include "memory_mgmt.h"

void memory_mgmt_test()
{
  char name[100];

  char *desc;

  strcpy(name, "Thomsen");

  desc = malloc(30 * sizeof(char));

  if (desc == NULL)
    {
      fprintf(stderr, "Error - unable to allocate required memory\n");
    }
  else
    {
      strcpy(desc, "Thomsen is a programmer");
    }

  desc = realloc(desc, 100 * sizeof(char));
  if (desc == NULL)
    {
      fprintf(stderr, "Error - unable to allocate required memory\n");
    }
  else
    {
      strcat(desc, ". He develop android program.");
    }


  printf("name: %s\n", name);
  printf("description: %s\n", desc);

  free(desc);
}
