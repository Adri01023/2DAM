frase = input("Introduce una frase: ")
letra = input("Letra a mantener: ")
letra_aparicion = input("Introduce una letra: ")
contador_letra = frase.count(letra_aparicion)
resultado = ""
for i in frase:
    if i != letra and i != letra_aparicion:
        if i == " ":
            resultado += " "
        else:
            resultado += "*"
    elif i == letra:
        resultado += letra
    elif i == letra_aparicion:
        resultado += letra_aparicion
print("La letra",letra_aparicion,"aparece en",contador_letra,"ocasiones")
print("Resultado:",resultado)