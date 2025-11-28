import re
def matriculaValida(*matriculas):
    patron = "[0-9]{4}-[a-zA-Z]{3}"
    patronespacio = "[0-9]{4} [a-zA-Z]{3}"
    noadmitidas = "AEIOUÑQ"
    contabien = 0
    contamal = 0
    for m in matriculas:
        for i in m:
            if i.upper() in noadmitidas:
                m += " "
        if re.fullmatch(patron, m):
            print(m,"es válida")
            contabien += 1
        elif re.fullmatch(patronespacio, m):
            print(m,"es válida")
            contabien += 1
        else:
            print(m,"no es válida")
            contamal += 1
    print("Matrículas válidas:",contabien)
    print("Matrículas no válidas:",contamal)
try:
    matriculaValida("2254 CDR","7845-QNM")
except:
    print("Introduce cadenas de texto")