package paqueteModelo;

import java.util.ArrayList;

public class Modelo
{

    //METODOS Y LOGICA DE JUGADORES
    private jugador Jugador1;
    private jugador Jugador2;

    ArrayList<jugador> jugadores= new ArrayList<>();
    private jugador jugadorActual;

    public void crearJugadores(String nombre1, String nombre2){
        Jugador1 = new jugador(nombre1, 1);
        Jugador2 = new jugador(nombre2, 2);
        jugadores.add(Jugador1);
        jugadores.add(Jugador2);
        this.jugadorActual = jugadores.get(0);
    }

    public String getJugadorActual() {
        return "Turno de " +jugadorActual.getNombre();
    }

    private int[][] tablero = new int[6][7];


    //METODOS DE TABLERO
    public void empezarPartida()
    {
        tablero = new int[6][7];
    }

    public int[][] getTablero()
    {
        return tablero;
    }


    //METODOS DE JUEGO
    public void cambiarTurno()
    {
        if (jugadorActual == jugadores.get(0)){
            jugadorActual = jugadores.get(1);
        } else {
            jugadorActual = jugadores.get(0);
        }
    }

    public boolean insertarFicha(int columna)
    {
        for (int fila = 5; fila >= 0; fila--) {
            if (tablero[fila][columna] == 0) {
                tablero[fila][columna] = jugadorActual.getId();
                return true;
            }
        }
         return false;
    }


    //METODOS DE RESULTADO DE PARTIDA
    private ArrayList<int[]> coordenadasGanadoras = new ArrayList<>();

    public ArrayList<int[]> getCoordenadasGanadoras() {
        return coordenadasGanadoras;
    }

    public int hayGanador()
    {
        coordenadasGanadoras.clear();
        for (int fila = 0; fila < 6; fila++){
            for (int columna = 0; columna < 7; columna++) {
                int ficha = tablero[fila][columna];
                if (ficha == 0) continue;

            //VERIFICACION HORIZONTAL
                if (columna <= 3 &&
                    ficha == tablero[fila][columna+1] &&
                    ficha == tablero[fila][columna+2] &&
                    ficha == tablero[fila][columna+3])
                {
                    coordenadasGanadoras.add(new int[]{fila, columna}); //Estos son las coordenadas que guardo en el arraylist de coordenadas ganadoras
                    coordenadasGanadoras.add(new int[]{fila, columna+1}); // se repite en todos los tipos de victorias
                    coordenadasGanadoras.add(new int[]{fila, columna+2});
                    coordenadasGanadoras.add(new int[]{fila, columna+3});
                    return ficha;
                }
            //FIN VERIFICACION HORIZONTAL

            //VERIFICACION VERTICAL
                if (fila <= 2 &&
                    ficha == tablero[fila+1][columna] &&
                    ficha == tablero[fila+2][columna] &&
                    ficha == tablero[fila+3][columna])
                {
                    coordenadasGanadoras.add(new int[]{fila, columna});
                    coordenadasGanadoras.add(new int[]{fila+1, columna});
                    coordenadasGanadoras.add(new int[]{fila+2, columna});
                    coordenadasGanadoras.add(new int[]{fila+3, columna});
                    return ficha;
                }
            //FIN VERIFICACION VERTICAL

            //VERIFICACION DIAGONAL
                if (fila <= 2 && columna <= 3 &&
                    ficha == tablero[fila+1][columna+1] &&
                    ficha == tablero[fila+2][columna+2] &&
                    ficha == tablero[fila+3][columna+3])
                {
                    coordenadasGanadoras.add(new int[]{fila, columna});
                    coordenadasGanadoras.add(new int[]{fila+1, columna+1});
                    coordenadasGanadoras.add(new int[]{fila+2, columna+2});
                    coordenadasGanadoras.add(new int[]{fila+3, columna+3});
                    return ficha;
                }

                if (fila >= 3 && columna <= 3 &&
                        ficha == tablero[fila-1][columna+1] &&
                        ficha == tablero[fila-2][columna+2] &&
                        ficha == tablero[fila-3][columna+3])
                {
                    coordenadasGanadoras.add(new int[]{fila, columna});
                    coordenadasGanadoras.add(new int[]{fila-1, columna+1});
                    coordenadasGanadoras.add(new int[]{fila-2, columna+2});
                    coordenadasGanadoras.add(new int[]{fila-3, columna+3});
                    return ficha;
                }
            //FIN VERIFICACION DIAGONAL

            }
        }
        return 0;
    }

}