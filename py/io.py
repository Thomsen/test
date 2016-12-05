fo = open('workfile', 'w+') # r w a b

print(fo)

print(str(fo.read()))

import json
#f.write(json.dumps([1, 'simple', 'list']))

a = {'abcd': '1234'}
json.dump(a, fo)

fo.close()

fr = open('workfile', 'r+')

b = fr.read()
print(b) #

c = "{'abcd': '1234'}"
x = json.loads(c)
print(x)


fr.close()
