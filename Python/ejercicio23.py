import math

primo = True
for i in range(2,100):
    primo = True
    for x in range(2,int(math.sqrt(i))+1):
        if i % x == 0: primo = False
    if primo: print(i)