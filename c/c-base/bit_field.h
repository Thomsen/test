#ifndef __BIT_FIELD_H

#define __BIT_FIELD_H

#include <stdio.h>

struct
{
  unsigned int width_validated;
  unsigned int height_validated;
} status1;

struct
{
  unsigned int width_validated : 1;
  unsigned int height_validated : 2;
} status2;

#endif /* __BIT_FIELD_H */
