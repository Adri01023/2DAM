primero = int(input("Introduce el primer numero:"))
segundo = int(input("Introduce el segundo numero:"))
primo = True
for i in range(primero,segundo):
    primo = True
    for x in range(2,i):
        if i % x == 0: primo = False
    if primo and i != 1: print(i)