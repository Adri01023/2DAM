import random
numeros = []
conta_par = 0
conta_impar = 0
print("10 números entre el 1 y el 1000")
for i in range(1,11):
    azar = random.randint(1,1000)
    numeros.append(azar)
    if azar % 2 == 0:
        conta_par += 1
    else:
        conta_impar += 1
print(str(numeros).replace("[","").replace("]",""))
print("He generado",conta_par,"números pares y",conta_impar,"números impares")
numeros.sort()
print("El número mayor ha sido el",numeros[9],"y el menor",numeros[0])