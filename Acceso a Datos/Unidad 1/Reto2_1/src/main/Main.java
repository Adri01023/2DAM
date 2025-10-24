package main;

import control.AppAgenda;
import vista.VistaAgenda;

public class Main {
    public static void main(String[] args) {
        AppAgenda app = new AppAgenda();
        new VistaAgenda(app);
    }
}