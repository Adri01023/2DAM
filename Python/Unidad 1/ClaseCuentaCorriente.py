from abc import abstractmethod, ABC


class CuentaCorriente:
    def __init__(self, titular, saldo):
        self.__titular = []
        self.__titular.append(titular)
        self.__saldo = saldo
    @property
    def titular(self):
        return self.__titular

    @property
    def saldo(self):
        return self.__saldo

    def __add__(self, cuenta):
        self.__saldo += cuenta.saldo
        self.__titular = self.__titular + cuenta.titular
        return self

c1 = CuentaCorriente("Adrian Martin", 1200)
c2 = CuentaCorriente("María Félez", 1000)
c2 += c1
print(c2.titular,c2.saldo)

class ClaseA:
    def __init__(self):
        self.nombre = "Clase A"

    def getNombre(self):
        return self.nombre

class ClaseB(ClaseA):
    def __init__(self):
        super().__init__()
        self.nombre = "Clase B"

class ClaseAbstracta(ABC):
    @abstractmethod
    def metodoAbstracto(self):
        pass

claseabstracta = ClaseAbstracta()
claseabstracta.metodoAbstracto()
claseb = ClaseB()
clasea = ClaseA()
print(claseb.getNombre())