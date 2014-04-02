#include "say.h"
#include <Foundation/Foundation.h>

int main(void)
{
  id speaker;
  NSString *name = @"Thomsen !";
  NSAutoreleasePool *pool;

  pool = [NSAutoreleasePool new];
  speaker = [[Say alloc] init];

  [speaker sayHello];
  [speaker sayHello];
  [speaker sayHelloTo:name];

  RELEASE(speaker);
  RELEASE(pool);
  
}
