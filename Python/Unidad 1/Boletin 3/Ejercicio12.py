from re import match

fecha = input("Introduce una fecha: ")
dia = fecha[0:fecha.find("/")]
mes = fecha[fecha.find("/") + 1:fecha.find("/",3)]
anio = fecha[fecha.find("/", 3) + 1:]
if fecha.count("/") != 2 or len(dia) != 2 or len(mes) != 2 or len(anio) != 4:
    print("El formato de la fecha es incorrecto")
elif (int(mes) > 12 and int(mes) < 1) or (int(dia) < 1 and int(dia) > 31) or (int(anio) < 0 and int(anio) > 2025):
    print("Fecha errónea")
hora = 8
match hora:
    case 8:
        print("Desayuno")
    case 14:
        print("Comida")
    case 21:
        print("Cena")
    case _:
        print("No toca comer")