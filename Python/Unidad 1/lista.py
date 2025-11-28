lista = []
lista2 = list()
lista3 = 1,2,3,4,5,6,7,8,9
lista4 = [34,"pepe",False, 765.827,(1,2,3)]
print(lista3)
print(lista4)
for elemento in lista4:
    print(elemento)
    for posicion in range (0,len(lista4)):
        print(posicion, "-", lista4[posicion])

lista4.append("Nuevo elemento")
print(lista4)

lista5 = lista3 + (23,45)
print(lista5)
lista6 = (lista5 + lista3)
print(lista6)



lista3.sort()
print(lista3.remove(7))