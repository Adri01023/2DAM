class Empleado:
    ##pass
    def __init__(self, nombre, apellidos, edad):
        self.__nombre = nombre
        self.__edad = edad
        self.__apellidos = apellidos

    @property
    def edad(self):
        return self.__edad
    @edad.setter
    def edad(self, nuevaEdad):
        self.__edad = nuevaEdad
    def __str__(self):
        return "Nombre: "+self.__apellidos+", "+self.__nombre+" Edad: "+str(self.edad)
##empleado1 = Empleado()
##empleado1.nombre = "Adrián"
##empleado1.apellido = "Martín"
empleado2 = Empleado("Adrián", "Martín Morales",21)
print(empleado2)
print(empleado2.edad)
empleado2.edad = 42
print(empleado2.edad)
del empleado2
