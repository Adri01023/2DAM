import random
numero = int(input("Introduce un número: "))
numeros = []
if numero < 10:
    print("Introduce un número mayor a 10")
else:
    print("5 números pares aleatorios y diferentes comprendidos entre el 1 y el",numero,":")
    while len(numeros) != 5:
        azar = random.randint(1, numero)
        if azar % 2 == 0 and azar not in numeros:
            print(azar)
            numeros.append(azar)