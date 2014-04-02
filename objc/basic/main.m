#import "Parent.h"
#import <Foundation/Foundation.h>

int main(int argc, char* argv[])
{
  NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
  
  Parent *parent = [[Parent alloc] init];
  [parent setName : @"thomsen"];
  [parent setAge : 23];
  [parent print];
  [parent release];

  NSLog(@"String");
  return 0;
}
