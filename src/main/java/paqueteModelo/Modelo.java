package paqueteModelo;

//librerias
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;

//Librerias horario
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Modelo {
    private jugador Jugador1;
    private jugador Jugador2;
    private jugador jugadorActual;

    private int[][] tablero = new int[6][7];

    private ArrayList<jugador> jugadores = new ArrayList();
    private ArrayList<int[]> coordenadasGanadoras = new ArrayList();

    public void crearJugadores(String nombre1, String nombre2) {
        //if (nombre1.equalsIgnoreCase(nombre2)){
        //    throw new IllegalArgumentException("Los jugadores no pueden tener el mismo nombre!");
        //}  VERIFICACION DUDOSA, PARA CCONSIDERAR
        Jugador1 = new jugador(nombre1, 1);
        Jugador2 = new jugador(nombre2, 2);
        jugadores.add(this.Jugador1);
        jugadores.add(this.Jugador2);
        jugadorActual = jugadores.get(0);
    }

    public String getJugadorActual() {
        return "Turno de " + jugadorActual.getNombre();
    }
    public String getNombreJugadorActual() {
        return jugadorActual.getNombre();
    }

    //metodos de funcionamiento del juego
    public int[][] getTablero() {
        return tablero;
    }

    public void cambiarTurno() {
        if (jugadorActual == jugadores.get(0)) {
            jugadorActual = jugadores.get(1);
        } else {
            jugadorActual = jugadores.get(0);
        }
    }

    private int contadorTurnos;

    public void empezarPartida() {
        tablero = new int[6][7];
        contadorTurnos = 0;
    }

    public boolean insertarFicha(int columna) {
        for (int fila = 5; fila >= 0; --fila) {
            if (tablero[fila][columna] == 0) {
                tablero[fila][columna] = jugadorActual.getId();

                contadorTurnos++;
                return true;
            }
        }
        return false;
    }

    //metodos de verificacion de resultados
    public ArrayList<int[]> getCoordenadasGanadoras() {
        return coordenadasGanadoras;
    }

    public int hayGanador() {
        this.coordenadasGanadoras.clear();

        for (int fila = 0; fila < 6; ++fila) {
            for (int columna = 0; columna < 7; ++columna) {
                int ficha = this.tablero[fila][columna];
                if (ficha != 0) {
                    //HORIZONTAL
                    if (columna <= 3 && ficha == this.tablero[fila][columna + 1] && ficha == this.tablero[fila][columna + 2] && ficha == this.tablero[fila][columna + 3]) {
                        this.coordenadasGanadoras.add(new int[]{fila, columna});
                        this.coordenadasGanadoras.add(new int[]{fila, columna + 1});
                        this.coordenadasGanadoras.add(new int[]{fila, columna + 2});
                        this.coordenadasGanadoras.add(new int[]{fila, columna + 3});
                        return ficha;
                    }

                    //VERTICAL
                    if (fila <= 2 && ficha == this.tablero[fila + 1][columna] && ficha == this.tablero[fila + 2][columna] && ficha == this.tablero[fila + 3][columna]) {
                        this.coordenadasGanadoras.add(new int[]{fila, columna});
                        this.coordenadasGanadoras.add(new int[]{fila + 1, columna});
                        this.coordenadasGanadoras.add(new int[]{fila + 2, columna});
                        this.coordenadasGanadoras.add(new int[]{fila + 3, columna});
                        return ficha;
                    }

                    //DIAGONAL ABAJO DERECHA
                    if (fila <= 2 && columna <= 3 && ficha == this.tablero[fila + 1][columna + 1] && ficha == this.tablero[fila + 2][columna + 2] && ficha == this.tablero[fila + 3][columna + 3]) {
                        this.coordenadasGanadoras.add(new int[]{fila, columna});
                        this.coordenadasGanadoras.add(new int[]{fila + 1, columna + 1});
                        this.coordenadasGanadoras.add(new int[]{fila + 2, columna + 2});
                        this.coordenadasGanadoras.add(new int[]{fila + 3, columna + 3});
                        return ficha;
                    }

                    //DIAGONAL ARRIBA DERECHA
                    if (fila >= 3 && columna <= 3 && ficha == this.tablero[fila - 1][columna + 1] && ficha == this.tablero[fila - 2][columna + 2] && ficha == this.tablero[fila - 3][columna + 3]) {
                        this.coordenadasGanadoras.add(new int[]{fila, columna});
                        this.coordenadasGanadoras.add(new int[]{fila - 1, columna + 1});
                        this.coordenadasGanadoras.add(new int[]{fila - 2, columna + 2});
                        this.coordenadasGanadoras.add(new int[]{fila - 3, columna + 3});
                        return ficha;
                    }
                }
            }
        }

        return 0;
    }

    public boolean tableroLleno() {
        return contadorTurnos >= 42;
    }

    //metodos manejo archivos
    pila log = new pila();
    private FileOutputStream archivo;
    private String rutaArchivo;

    String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
    String nombreArchivo = "LogPartida" +fecha+".txt";

    public void crearArchivoPartida() {
        try {
            rutaArchivo = nombreArchivo;
            archivo = new FileOutputStream(rutaArchivo, true);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void guardarJugadaEnPila(int columna, String jugador) {
        try {
            columna = columna+1;
            String info = (jugador+ " posicionó ficha en la columna ->" +columna+ "<-"+"\n");
            log.insertar(info );
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
            Runtime.getRuntime().exec("notepad \"" + rutaArchivo + "\"");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}