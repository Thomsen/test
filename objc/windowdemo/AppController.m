#include "AppController.h"
#include <AppKit/AppKit.h>

@implementation AppController : NSObject
- (void) applicationWillFinishLaunching: (NSNotification *) not
{
  NSMenu *menu;
  NSMenu *info;

  menu = [NSMenu new];
  // menu = AUTORELEASE ([NSMenu new]);
  // [menu addItemWidthTitle: @"Info"  // error width -> with, but compile warning
  //		   action: NULL
  //	    keyEquivalent: @""];
  [menu addItemWithTitle: @"Info"
		   action: NULL
	    keyEquivalent: @""];
  [menu addItemWithTitle: @"Hide"
		   action: @selector (hide:)
	    keyEquivalent: @"h"];
  [menu addItemWithTitle: @"Quit"
		   action: @selector (terminate:)
	    keyEquivalent: @"q"];

  info = [NSMenu new];
  /* If your application name is, for example, MyFirstApp, then you need to create a file called MyFirstAppInfo.plist in your source directory (you do not need to add anything to your GNUmakefile; the make system looks for this file automatically) */
  [info addItemWithTitle: @"Info Panel..."
		  action: @selector (orderFrontStandardInfoPanel:) 
	   keyEquivalent: @""];
  [info addItemWithTitle: @"Preferences"
		  action: NULL
	   keyEquivalent: @""];
  [info addItemWithTitle: @"Help"
		  action: @selector (orderFrontHelpPanel:)
	   keyEquivalent: @"?"];

  [menu setSubmenu: info
	   forItem: [menu itemWithTitle: @"Info"]];
  RELEASE(info);

  [NSApp setMainMenu: menu];
  RELEASE(menu);

  window = [[NSWindow alloc] initWithContentRect: NSMakeRect(300, 100, 400, 100)
				       styleMask: (NSTitledWindowMask |
						   NSMiniaturizableWindowMask |
						   NSResizableWindowMask)
					 backing: NSBackingStoreBuffered
					   defer: YES];
  [window setTitle: @"Hello Window"];

  label = [[NSTextField alloc] initWithFrame: NSMakeRect(30, 30, 80, 30)];
  [label setSelectable: NO];
  [label setBezeled: NO];
  [label setDrawsBackground: NO];
  [label setStringValue: @"Hello label"];

  [[window contentView] addSubview: label];
  RELEASE(label);
}

- (void) applicationDidFinishLaunching: (NSNotification *) not
{
  // show window
  [window makeKeyAndOrderFront: self];
}

- (void) dealloc
{
  RELEASE(window);
  [super dealloc];
}

@end
