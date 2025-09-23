import math
import random
intentos = 0
azar = 0
primo = True
while primo:
    intentos += 1
    azar = random.randint(10000000,50000000)
    primo = False
    for i in range(2,int(math.sqrt(azar)+1)):
        if azar % i == 0: primo = True
print("Felicidades saliste con el primo:",azar,"te ha tomado:",intentos,"intentos")