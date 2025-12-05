package paqueteModelo;

import java.util.ArrayList;

public class Modelo {
    private jugador Jugador1;
    private jugador Jugador2;
    private jugador jugadorActual;

    private int[][] tablero = new int[6][7];

    ArrayList<jugador> jugadores = new ArrayList();
    private ArrayList<int[]> coordenadasGanadoras = new ArrayList();

    public void crearJugadores(String nombre1, String nombre2) {
        Jugador1 = new jugador(nombre1, 1);
        Jugador2 = new jugador(nombre2, 2);
        jugadores.add(this.Jugador1);
        jugadores.add(this.Jugador2);
        jugadorActual = jugadores.get(0);
    }

    public String getJugadorActual() {
        return "Turno de " + jugadorActual.getNombre();
    }

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
        for(int fila = 5; fila >= 0; --fila) {
            if (tablero[fila][columna] == 0) {
                tablero[fila][columna] = jugadorActual.getId();

                contadorTurnos++;
                return true;
            }
        }
        return false;
    }

    public ArrayList<int[]> getCoordenadasGanadoras() {
        return coordenadasGanadoras;
    }

    public int hayGanador() {
        this.coordenadasGanadoras.clear();

        for(int fila = 0; fila < 6; ++fila) {
            for(int columna = 0; columna < 7; ++columna) {
                int ficha = this.tablero[fila][columna];
                if (ficha != 0) {
                    if (columna <= 3 && ficha == this.tablero[fila][columna + 1] && ficha == this.tablero[fila][columna + 2] && ficha == this.tablero[fila][columna + 3]) {
                        this.coordenadasGanadoras.add(new int[]{fila, columna});
                        this.coordenadasGanadoras.add(new int[]{fila, columna + 1});
                        this.coordenadasGanadoras.add(new int[]{fila, columna + 2});
                        this.coordenadasGanadoras.add(new int[]{fila, columna + 3});
                        return ficha;
                    }

                    if (fila <= 2 && ficha == this.tablero[fila + 1][columna] && ficha == this.tablero[fila + 2][columna] && ficha == this.tablero[fila + 3][columna]) {
                        this.coordenadasGanadoras.add(new int[]{fila, columna});
                        this.coordenadasGanadoras.add(new int[]{fila + 1, columna});
                        this.coordenadasGanadoras.add(new int[]{fila + 2, columna});
                        this.coordenadasGanadoras.add(new int[]{fila + 3, columna});
                        return ficha;
                    }

                    if (fila <= 2 && columna <= 3 && ficha == this.tablero[fila + 1][columna + 1] && ficha == this.tablero[fila + 2][columna + 2] && ficha == this.tablero[fila + 3][columna + 3]) {
                        this.coordenadasGanadoras.add(new int[]{fila, columna});
                        this.coordenadasGanadoras.add(new int[]{fila + 1, columna + 1});
                        this.coordenadasGanadoras.add(new int[]{fila + 2, columna + 2});
                        this.coordenadasGanadoras.add(new int[]{fila + 3, columna + 3});
                        return ficha;
                    }

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
}