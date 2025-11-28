def cifradoPin(codigo):
    numeros = str(codigo)
    lista = []
    cifrado = []
    for i in range(0, 4):
        cifrado.clear()
        for x in range (1,11):
            if int(numeros[i]) == x:
                cifrado.append("0")
            else:
                cifrado.append("X")
        if int(numeros[i]) == 0:
            cifrado[-1] = "0"
        lista.append(str(cifrado).replace("[","").replace("]","").replace("'",""))
    tupla = tuple(lista)
    return tupla
try:
    numeros = cifradoPin(1450)
    for i in numeros:
        print(i)
except:
    print("Introduce un pin de 4 numeros")