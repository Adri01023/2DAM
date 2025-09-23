num1 = int(input("Introduce el primer número: "))
num2 = int(input("Introduce el segundo número: "))
num3 = int(input("Introduce el tercer número: "))
mayor = max(num1,num2,num3)
menor = min(num1,num2,num3)
intermedio = 0
if num1 != mayor and num1 != menor: intermedio = num1
elif num2 != mayor and num2 != menor: intermedio = num2
else: intermedio = num3
print("Mayor:",mayor,"Intermedio:",intermedio,"Menor:",menor)