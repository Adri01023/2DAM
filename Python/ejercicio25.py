numero = int(input("Introduce un número: "))
print("Has introducido el número: ",numero)
while numero >= 1:
    numero /= 2
    print(numero.__round__(2))