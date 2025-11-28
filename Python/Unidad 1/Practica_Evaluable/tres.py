frase = input("Introduce una frase: ")
contador = 0
frase_x = []
for i in frase:
    if i != " ":
        frase_x.append("*")
    else:
        frase_x.append(" ")
while frase_x.__contains__("*") != 0:
    letra = input("\nIntroduce una letra: ")
    contador_letra = frase.count(letra)
    print("\nLa letra",letra,"aparece en",contador_letra,"ocasiones")
    for i in range(0,len(frase)):
        if frase[i] == letra:
            frase_x[i] = letra
    print("\nResultado:",str(frase_x).replace("[","").replace("]","").replace(",","").replace("'",""))
    contador += 1
print("\nHas ganado. Has necesitado",contador,"intentos para completar la frase")