import random
numeros = []
masrepite = 0
vecesrepite = 0
for x in range(0,100):
    aleatorio = random.randint(1,50)
    numeros.append(aleatorio)
    if vecesrepite < numeros.count(aleatorio):
        vecesrepite = numeros.count(aleatorio)
        masrepite = aleatorio
numeros.sort()
print("El número más pequeño es:",numeros[0],"el número más grande es:", numeros[-1],"y el número que más veces se repite es:",masrepite,
      "que se ha repetido una cantidad de",vecesrepite,"veces")
print(numeros)