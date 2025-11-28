
class Perro:
    numPerros = 0

    def __init__(self, nombre="Firulais"):
        self.nombre = nombre
        Perro.numPerros += 1

    def llamar(self):
        return("Ey " + self.nombre + " ven aquí!")

    @classmethod
    def cuantosPerros(cls):
        return cls.numPerros

    @staticmethod
    def ladrar():
        return "Guau"


    def sobrecargada(self,atributo):
        if isinstance(atributo,int):
            print("Estoy trabajando con un entero")
        elif isinstance(atributo,float):
            print("Estoy trabajando con un float")
        elif isinstance(atributo, str):
            print("Estiy trabajando con un string")
        elif isinstance(atributo, list):
            print("Estoy trabajando con una lista")
        else:
            print("No se que es eso")

    def sobrecargada2(self, *atributos):
        if isinstance(atributos, int):
                    print("Estoy trabajando con un entero")
        elif isinstance(atributos, float):
                    print("Estoy trabajando con un float")
        elif isinstance(atributos, str):
                    print("Estiy trabajando con un string")
        elif isinstance(atributos, list):
                    print("Estoy trabajando con una lista")
        else:
                    print("No se que es eso")

mascota1= Perro("Igris")
mascota2 = Perro()
mascota3= Perro("Balto")
print(mascota1.llamar())
print(mascota2.llamar())
print(mascota3.llamar())
print(Perro.cuantosPerros())
print(Perro.cuantosPerros())
print(mascota2.cuantosPerros())
print(mascota1.ladrar())
mascota1.sobrecargada(3)
mascota1.sobrecargada(3.5)
mascota1.sobrecargada("Hola")
mascota1.sobrecargada([1,2,"m"])

mascota2.sobrecargada2()




