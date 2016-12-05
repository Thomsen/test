import os

print('current dir: ' + os.getcwd())

print('os: ' + str(dir(os)))

import glob

print('glob: ' + str(glob.glob('*.py')))


import re

print('findall: ' + str(re.findall(r'\bs[a-z]*', 'stdlid and sysds learn')))


import math

print('pi: ' + str(math.pi))

print('cos: ' + str(math.cos(math.pi / 4)))

import random

print('random sample: ' + str(random.sample(range(100), 10)))


import statistics

data = [1,2,3,2,2,3,2]
print('statistics '+ str(statistics.mean(data)))


from urllib.request import urlopen



import smtplib
