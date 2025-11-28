frase = input("Introduce una frase (se contarán cuantas palabras contiene): ")
contador = 0
palabra = []
for i in frase:
    if i != " ":
        palabra.append(i)
    elif palabra[-1] != " ":
        contador += 1
        palabra.append(i)
    else:
        palabra.append(i)
if palabra[-1] != " ": contador += 1
print("El número de palabras en la frase son:",contador)