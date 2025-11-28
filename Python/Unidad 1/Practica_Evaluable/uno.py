frase = input("Introduce una frase: ")
letra = input("Letra a mantener: ")
resultado = ""
for i in frase:
    if i != letra:
        if i == " ":
            resultado += " "
        else:
            resultado += "*"
    else:
        resultado += letra
print("Resultado:",resultado)