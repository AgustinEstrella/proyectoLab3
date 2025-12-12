package paqueteLog;

import paqueteModelo.pila;

import java.io.FileOutputStream;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logPartida {

    private FileOutputStream archivo;
    private pila log;
    private String nombreArchivo;

    public logPartida(){
        this.log = new pila();
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        this.nombreArchivo = "LogPartida" +fecha+".txt";
    }

    public void crearArchivoPartida() {
        try {
            archivo = new FileOutputStream(nombreArchivo, true);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void guardarJugadaEnPila(int columna, String jugador) {
        try {
            columna = columna+1;
            String info = (jugador+ " posicionó ficha en la columna ->" +columna+ "<-"+"\n");
            log.insertar(info);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void cerraryMostrarArchivo(String quienGano) {
        try {
            String info = log.imprimirPila();
            for (int i = 0; i < info.length(); i++) {
                archivo.write((int) info.charAt(i));
            }
            archivo.write(("El ganador es: "+quienGano+ "\n").getBytes());
            archivo.close(); //cierra la edicion del archivo
            Runtime.getRuntime().exec("notepad \"" + nombreArchivo + "\"");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


}
