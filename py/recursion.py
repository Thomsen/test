# recursion hanoi
def printMove(fr, to): # from is keyword
    print('move from: ' + str(fr) + ' to: ' + str(to))

def towers(n, fr, to, spare):
    if n == 1:
        printMove(fr, to)
    else:
        towers(n-1, fr, spare, to)
        towers(1, fr, to, spare)
        towers(n-1, spare, to, fr)

# recursion fibonacci
global numCalls
numCalls = 0
def fib(n):
    assert type(n) == int and n >= 0
    res = 1
    global numCalls
    numCalls += 1
    if (n == 0) or (n == 1): # elif
        res = 1
    else:
        res = fib(n-1) + fib(n-2)
    return res

def printFibStep(n):
    print('fibonacci(' + str(n) + ') step: ' + str(numCalls))

# recurison no number
def isPalindrome(s):
    def toChars(s):
        s = s.lower()
        ans = ''
        for c in s:
            if c in 'abcdefghijklmnopkrstuvwzyx':
                ans = ans + c
        return ans
    def isPal(s):
        if (len(s) <= 1):
            return True
        else:
            return s[0] == s[-1] and isPal(s[1 : -1])
    return isPal(s)
