frase = input("Introduce una frase: ")
palabra = []
vocales = ["a","e","i","o","u"]
contador = 0
contador_vocales = 0
if frase[-1] != " ": frase += " "
for i in frase:
    if i != " ":
        palabra.append(i)
    else:
        print(palabra)
        for x in palabra:
            if x.lower() in vocales:
                vocales.remove(x.lower())
                contador_vocales += 1
        if contador_vocales >= 4: contador += 1
        contador_vocales = 0
        vocales = ["a","e","i","o","u"]
        palabra.clear()
print("Hay",contador,"palabras con 4 o más vocales distintas")