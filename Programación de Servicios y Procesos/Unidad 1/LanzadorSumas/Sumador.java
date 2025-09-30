package LanzadorSumas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sumador {
    public int sumar(int n1, int n2, int proceso) {
        int resultado = 0;
        for (int i = n1; i <= n2; i++) {
            resultado += i;
        }

        System.out.println("Ejecutado por el proceso: " + proceso);
        System.out.println("El resultado de la suma de " + n1 + " y " + n2 + " es " + resultado);

        return resultado;
    }

    public int sumar2(int n1, int n2, int proceso, String path) {
        int resultado = 0;
        for (int i = n1; i <= n2; i++) {
            resultado += i;
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
            bw.write("Ejecutado por el proceso: " + proceso + " ");
            bw.write("El resultado de la suma de " + n1 + " y " + n2 + " es " + resultado);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static void main(String[] args) {
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int proceso = Integer.parseInt(args[2]);
        String path = args[3];
        Sumador sumador = new Sumador();
        sumador.sumar2(n1,n2,proceso,path);
    }
}
