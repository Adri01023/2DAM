import re

ip = input("Introduce una dirección IP:")
patron = ("([0-9]|[0-9][0-9]|[1-2][0-9][0-9]).([0-9]|[0-9][0-9]|[1-2][0-9][0-9]).([0-9]|[0-9][0-9]|[1-2][0-9]["
          "0-9]).([0-9]|[0-9][0-9]|[1-2][0-9][0-9])")
if re.fullmatch(patron, ip):
    primerbyte = int(ip[0:ip.find(".")])
    if primerbyte >= 0 and primerbyte <= 127:
        print(ip + "/8")
    elif primerbyte >= 128 and primerbyte <= 191:
        print(ip + "/16")
    elif primerbyte >= 192 and primerbyte <= 223:
        print(ip + "/24")
    elif primerbyte >= 224 and primerbyte <= 255:
        print("Dirección Reservada")
    else:
        print("Dirección no válida")
else:
    print("Dirección no válida")