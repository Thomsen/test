#import <Foundation/NSString.h>
#import <Foundation/Foundation.h>
#import <stdio.h>
int main(int argc, char *argv[]){
    NSString *aa = @"Hello world!";
    printf("Length of aa is: %i\n", [aa length]);
    return 0;
}
