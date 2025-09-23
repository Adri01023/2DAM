import math

numero = int(input("Introduce un número para saber si es primo o no: "))
primo = True
for i in range(2,int(math.sqrt(numero))):
    if numero % i == 0: primo = False
if primo: print("Es primo")
else: print("No es primo")