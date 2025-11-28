def toDecimal(numero):
    binario = 0
    if not numero.isdigit():
        return -1
    for i in range(0, len(numero)):
        if int(numero[i]) == 1:
            binario += 2**((len(numero) - 1) - i)
        elif int(numero[i]) != 0:
            return -1
    return binario
try:
    print(toDecimal("1101"))
except:
    print("Introduce una cadena")