import random
azar = 0
dias = 0
while azar != 666:
    azar = random.randint(1,1000)
    if azar != 666: dias += azar
    print(azar)
print("¡Faltan",dias,"días para que se acabe todo!")