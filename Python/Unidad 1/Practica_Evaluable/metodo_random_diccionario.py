import random

d1 = dict(Sara = 33, Pepe = 55, Luis = 44, Manolo = 33, Eva = 66, Ines = 55)

print(d1)

def eliminaAlAzar(diccionario):
    lista = []
    for elemento in diccionario:
        lista.append(elemento)
    diccionario.pop(lista[random.randint(0,len(lista))])
    return diccionario
d1 = eliminaAlAzar(d1)
print(d1)