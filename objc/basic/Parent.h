#ifndef _PARENT_H_
#define _PARENT_H_

#import <Foundation/NSObject.h>

@interface Parent : NSObject
{
  NSString *name;
  int age;
}

- (void) print;
- (void) setName: (NSString *) nameValue;
- (void) setAge: (int) ageValue;
			   
@end

#endif
