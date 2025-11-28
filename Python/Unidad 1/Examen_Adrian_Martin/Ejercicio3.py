texto = input("Introduce un texto: ")
resultado = ""
num_vocales = 0
num_espacios = 0
vocales = "aeiou"
for i in texto:
    if i == " ":
        num_espacios += 1
    elif i.lower() in vocales:
        num_vocales += 1
    else:
        resultado += i
print("Sin vocales ni espacios:",resultado)
print("Vocales suprimidas:",num_vocales)
print("Espacio en blanco suprimidos:",num_espacios)