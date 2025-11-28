numero1 = int(input("Introduce el primer número:"))
numero2 = int(input("Introduce el segundo número:"))
divisores1 = list()
divisores2 = list()
divisorestotal = list()
for x in range(2,numero1):
    if numero1 % x == 0: divisores1.append(x)
for y in range(2,numero2):
    if numero2 % y == 0: divisores2.append(y)
if len(divisores1) > len(divisores2):
    for z in divisores1:
        if z in divisores2: divisorestotal.append(z)
else:
    for z in divisores2:
        if z in divisores1: divisorestotal.append(z)
print("Los divisores en común son:",divisorestotal)