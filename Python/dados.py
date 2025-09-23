import random
salirbucle = True
intentos = 0
while salirbucle:
    a = random.randint(1,6)
    b = random.randint(1,6)
    intentos += 1
    print("Dado A:",a)
    print("Dado B:",b,"\n")
    if (a == b):
        salirbucle = False
print("Felicidades, saliste del bucle, te ha tomado",intentos,"intentos")