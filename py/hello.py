#print 'hello' # python2 is ok
print ('hello')
print('c:\node')
print(r'c:\node')
print("c:\node")
print(3*'th' + 'om')
print('py' 'thon')

word = 'python'
print (word[1:3])

list = [1, 1, 2, 3, 5, 'a']
print (list)

a,b = 0,1
while (b < 10):
    print(b, end=',')
    a, b=b, a+b
print()

for i in range(5, 9):
    print (i)

class EmptyClass:
    """
    do nothing, but document it
    """
    pass
print(EmptyClass.__doc__)

def make_incrementor(n):
    return lambda z: z+n
f = make_incrementor(42)
print("lambda incrementor 42 " + str(f(72)))

x = 3
ans = 0
itersLeft = x
while (itersLeft != 0):
    ans = ans + x
    itersLeft = itersLeft - 1
print(str(x) + ' * ' + str(x) + ' = ' + str(ans))
