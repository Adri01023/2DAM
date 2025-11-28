def mifuncion():
    print(texto)
texto = "Hola mundo"

def argumentosvariables(nombre,*titulos):
    for titulo in titulos:
        print(titulo,nombre)
argumentosvariables("Adrian","Sr","Marqués","Don")

def muestraDatos(nombre,edad):
    print("Nombre:",nombre,"- Edad:",edad)
datos = ["Pepe",85]
muestraDatos(*datos)

def devuelveTresEnteros():
    return 23.12, 28, 34
print(devuelveTresEnteros())
promedio = 0.85741
print(f"El porcentaje de aprobados es de {promedio:.4%}")
poblacion = 1234567890
print(f"La población del país es de {poblacion:,} habitantes")
n1, n2, n3 = devuelveTresEnteros()
print(f"Números:\n{n1:04f}***{n1:<20}***")
print(f"Números: ***{n1:^20}***")
print(f"Inspeccionando variables {n1=} y {n2=}")
nombre = "Adrián"
edad = 21
grado = "DAM"
ficha = f""""
Ficha del alumno
=================
Nombre: {nombre}
Edad: {edad:.2f}
Grado: {grado}
=================
"""
print(ficha)
print(f"¿n1 es par? {'Si' if n1%2==0 else 'No'}")