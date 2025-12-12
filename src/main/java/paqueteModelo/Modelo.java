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

    public void crearJugadores(String nombre1, String nombre2) {
        //if (nombre1.equalsIgnoreCase(nombre2)){
        //    throw new IllegalArgumentException("Los jugadores no pueden tener el mismo nombre!");
        //}  VERIFICACION DUDOSA, PARA CCONSIDERAR
        Jugador1 = new jugador(nombre1, 1);
        Jugador2 = new jugador(nombre2, 2);
        jugadores.add(Jugador1);
        jugadores.add(Jugador2);
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
    private ArrayList<int[]> coordenadasGanadoras = new ArrayList();

    public int hayGanador() {
        coordenadasGanadoras.clear();

        for (int fila = 0; fila < 6; ++fila) {
            for (int columna = 0; columna < 7; ++columna) {
                int ficha = tablero[fila][columna];
                if (ficha != 0) {
                    //HORIZONTAL
                    if (columna <= 3 && ficha == tablero[fila][columna + 1] && ficha == tablero[fila][columna + 2] && ficha == tablero[fila][columna + 3]) {
                        coordenadasGanadoras.add(new int[]{fila, columna});
                        coordenadasGanadoras.add(new int[]{fila, columna + 1});
                        coordenadasGanadoras.add(new int[]{fila, columna + 2});
                        coordenadasGanadoras.add(new int[]{fila, columna + 3});
                        return ficha;
                    }

                    //VERTICAL
                    if (fila <= 2 && ficha == tablero[fila + 1][columna] && ficha == tablero[fila + 2][columna] && ficha == tablero[fila + 3][columna]) {
                        coordenadasGanadoras.add(new int[]{fila, columna});
                        coordenadasGanadoras.add(new int[]{fila + 1, columna});
                        coordenadasGanadoras.add(new int[]{fila + 2, columna});
                        coordenadasGanadoras.add(new int[]{fila + 3, columna});
                        return ficha;
                    }

                    //DIAGONAL ABAJO DERECHA
                    if (fila <= 2 && columna <= 3 && ficha == tablero[fila + 1][columna + 1] && ficha == tablero[fila + 2][columna + 2] && ficha == tablero[fila + 3][columna + 3]) {
                        coordenadasGanadoras.add(new int[]{fila, columna});
                        coordenadasGanadoras.add(new int[]{fila + 1, columna + 1});
                        coordenadasGanadoras.add(new int[]{fila + 2, columna + 2});
                        coordenadasGanadoras.add(new int[]{fila + 3, columna + 3});
                        return ficha;
                    }

                    //DIAGONAL ARRIBA DERECHA
                    if (fila >= 3 && columna <= 3 && ficha == tablero[fila - 1][columna + 1] && ficha == tablero[fila - 2][columna + 2] && ficha == tablero[fila - 3][columna + 3]) {
                        coordenadasGanadoras.add(new int[]{fila, columna});
                        coordenadasGanadoras.add(new int[]{fila - 1, columna + 1});
                        coordenadasGanadoras.add(new int[]{fila - 2, columna + 2});
                        coordenadasGanadoras.add(new int[]{fila - 3, columna + 3});
                        return ficha;
                    }
                }
            }
        }

        return 0;
    }

    public ArrayList<int[]> getCoordenadasGanadoras() {
        return coordenadasGanadoras;
    }

    public boolean tableroLleno() {
        return contadorTurnos >= 42;
    }

}