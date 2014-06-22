#include "file_io.h"

void file_io_test()
{
  FILE *fp;

  fp = fopen("./readme.txt", "w+");
  fprintf(fp, "this is testing for fprintf...\n");
  fputs("this is testing for fputs...\n", fp);
  fclose(fp);

  char buff[100];
  fp = fopen("./readme.txt", "r");
  fscanf(fp, "%s", buff);
  printf("the readme.txt scanf buff: %s\n", buff);

  fgets(buff, 255, (FILE *)fp);
  printf("the readme.txt gets buff: %s\n", buff);

  fgets(buff, 255, (FILE *)fp);
  printf("the readme.txt gets buff: %s\n", buff);

  fclose(fp);
}
