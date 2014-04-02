#include <Foundation/Foundation.h>

int main(void)
{
  NSString *path;
  NSAutoreleasePool *pool;
  NSFileHandle *readFile, *writeFile;
  NSData *fileData;

  pool = [NSAutoreleasePool new];
  // path = @"main.m";
  path = @"GNUmakefile";

  readFile = [NSFileHandle fileHandleForReadingAtPath:path];
  fileData = [readFile readDataToEndOfFile];

  // writeFile = [NSFileHandle fileHandleWithStandardOutput];
  // [writeFile writeData:fileData];

  char *buffer;
  unsigned int length;

  length = [fileData length];
  buffer = malloc(sizeof(char)*length);
  [fileData getBytes: buffer];

  printf("%s\n", buffer);
  printf("%s\n", length);
  free(buffer);

  RELEASE(pool);

  return 0;
}
