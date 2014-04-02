#ifndef _SAY_H_
#define _SAY_H_

#include <Foundation/NSObject.h>

@interface Say: NSObject
{
}

-(void) sayHello;
-(void) sayHelloTo: (NSString *)name;
@end

#endif /* _SAY_H_ */
