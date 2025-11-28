class DiasdelaSemana:
    def __init__(self, dia):
        self.dias = ["Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"]
        self.hoy = dia
    def mostrarDia(self):
        print(self.dias[self.hoy])
    def __iter__(self):
        return self
    def __next__(self):
        if self.hoy >= len(self.dias):
            self.hoy = 0
        print(self.dias[self.hoy])
        self.hoy += 1
dia = DiasdelaSemana(2)
dia.mostrarDia()
iterador = iter(dia)
next(iterador)
next(iterador)
next(iterador)
next(iterador)
next(iterador)
next(iterador)
next(iterador)

