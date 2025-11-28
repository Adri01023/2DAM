diccionario = {"Nombre": "Sara", "Edad": 57, "Soltera": True}
print(diccionario)
print(diccionario["Edad"])
print(diccionario["Nombre"])
for elemento in diccionario:
    print(elemento)

for elemento in diccionario:
    print(diccionario[elemento])

for elemento in diccionario:
    print(elemento,":",diccionario[elemento])

for clave, valor in diccionario.items():
    print(clave,":", valor)

print(diccionario.get("Edad"))
diccionario["Asignatura"] = "Bases de datos"
print(diccionario)
#diccionario.clear()
print(len(diccionario))
print(diccionario.update({"Titulo":"DAM"}))
dicc2 = dict(Primero = "Uno", Tercero = 3, Edad = 44)
print(diccionario)
print(dicc2.items())
print(diccionario.values())
print()