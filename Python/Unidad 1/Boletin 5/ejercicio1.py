import random

lista = list()
while len(lista) != 6:
    numero = random.randint(1,49)
    if not lista.count(numero) > 0: lista.append(numero)
print("Números de la primitiva:", lista)