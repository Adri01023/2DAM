fraccion = input("Escribe tu fracción:")
numerador = fraccion[0:fraccion.find("/")]
denominador = fraccion[fraccion.find("/") + 1:]
if (fraccion.count("/") != 1 or fraccion[0] == "/" or fraccion[-1] == "/" or fraccion.count(".") != 0 or denominador == "0"
        or not numerador.isdigit() or not denominador.isdigit()):
    print("Introduce una fracción válida")
    exit(0)
resultado = int(numerador) / int(denominador)
print("El valor de tu fracción es:",round(resultado,3))