#include <Foundation/Foundation.h>
#include <AppKit/AppKit.h>

@interface MyDelegate : NSObject
- (void) applicationWillFinishLaunching: (NSNotification *)not;
@end

@implementation MyDelegate : NSObject 
- (void) applicationWillFinishLaunching: (NSNotification *)not
{
  NSMenu *menu;
  
  menu = AUTORELEASE ([NSMenu new]);
  [menu addItemWithTitle: @"Quit"  
        action: @selector (terminate:)  
        keyEquivalent: @"q"];
  [NSApp setMainMenu: menu];
}
@end

int main (int argc, const char **argv)
{ 
  [NSApplication sharedApplication];
  [NSApp setDelegate: [MyDelegate new]];

  return NSApplicationMain (argc, argv);
}
