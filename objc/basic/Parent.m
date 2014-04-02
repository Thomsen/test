#import "Parent.h"
#import <Foundation/Foundation.h>
#import <stdio.h>

@implementation Parent

- (void) print
{
  //  printf("name: "name + "\tage: " + age);
  printf("age: %d\n", age);
  //printf("name: %s\n", name); // 乱码
  printf("name: %@\n", name);  
  //  NSLog(@"name: %@, \t age: %@", name, age);
}

- (void) setName: (NSString *) nameValue
{
  //  name = name;
  if (nameValue != name)
  {
    [nameValue retain];
    [name release];
    name = nameValue;
  }
}

- (void) setAge: (int) ageValue
{
  age = ageValue;
}

@end
