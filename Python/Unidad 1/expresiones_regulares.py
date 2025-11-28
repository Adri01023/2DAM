import re

patron = r"hola"
re.search(r"[0-9]{8}[A-Za-zÑñ]", "78541245q")
re.fullmatch(r"[0-9]{3,5}", "1234")
#if re.search(r"[0-9]{8}[A-Za-zÑñ]", "78541245ñ"):
if re.fullmatch(r"![^HO]", "HO"):
    print("Hay coincidencia")
else:
    print("No hay coincidencia")