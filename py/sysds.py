a = [238, 289, 98, 63, 289, 13, 4, 1024]

print(a.count(238), a.count(289), a.count(4), a.count(64))

a.insert(1, 1024)

a.append(39)

print(a)
print(a.index(1024))

a.remove(13)
print(a)

a.sort()
print(a)

a.reverse();
print(a)

a.pop()
print(a)

a.append(14)
print(a)

from collections import deque
b = deque(a)

b.append(14)
print(b)

print(b.popleft())


c = set('abcdefbfeiw')
print(c)
d = set('ioeiwowejik')
print(d)
print(c - d)
print(c | d)
print(c ^ d)

e = {'one': 'c', 'two': 'java', 'three': 'cpp'}
print(e)
print(e['one'])

for k, v in e.items():
    print(k, v)

f = [('four', 'html'), ('five', 'csharp')]
print(dict(f))
for k,v in enumerate(f):
    print(k, v)
