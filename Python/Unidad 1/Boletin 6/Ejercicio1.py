try:
    numero = input("Introduce un número: ")
    print(numero[1])
    numero = int(numero)
except ValueError:
    print("Introduce un código postal válido")