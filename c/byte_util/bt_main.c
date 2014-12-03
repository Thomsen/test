#include <stdio.h>

typedef unsigned char byte;

char* byte_to_hex(byte b)
{
  char hex[] = {'0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'A', 'B',
                'C', 'D', 'E', 'F'};
  int v = b;
  if (v < 0)
    {
      v = 0x100 + v;
    }
  int d1 = v >> 4;
  int d2 = v & 0xF;

  char c[2];
  c[0] = hex[d1];
  c[1] = hex[d2];
  return c;

}

int main(char* argv, int argc)
{

  /*
  char c;
  c = getchar();
  */
  /*putchar(argv);*/
  /*  putchar(byte_to_hex(c));*/

  //char *s; // %s
  char s;  // %c
  printf("input string: ");
  scanf("%c", &s);
  printf("result %c\n", s);
  printf("result hex %c", byte_to_hex(s));

  printf("\n");


  return 1;
}
