from recursion import towers
from recursion import fib, printFibStep
from recursion import isPalindrome

towers(3, 'a', 'b', 'c')

n = 8
print('fibonacci(' + str(n) +') = ' + str(fib(n)))
printFibStep(n)

s = 'abcdcbd'
print(s + ' is palindrome: ' + str(isPalindrome(s)))

s = 'abcdcba'
print(s + ' is palindrome: ' + str(isPalindrome(s)))

if __name__ == '__main__':
    import sys
    if (len(sys.argv) == 2):
        print(str(fib(int(sys.argv[1]))))

    print(dir(sys))
