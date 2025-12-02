package paqueteModelo;

public class Modelo
{

    private jugador Jugador1;
    private jugador Jugador2;

    public void crearJugador(String nombre1, String nombre2){
        Jugador1 = new jugador(nombre1, 1);
        Jugador2 = new jugador(nombre2, 2);
    }

    private int jugadorActual = 1; //Jugador 1: "X" Jugador 2: "O"

    public int getJugadorActual() {
        return jugadorActual;
    }

    private int[][] tablero = new int[6][7];

    public void empezarPartida()
    {
        tablero = new int[6][7];
    }

    public int[][] getTablero()
    {
        return tablero;
    }

    private void cambiarTurno()
    {
        if (jugadorActual == 1){
            jugadorActual = 2;
        } else {
            jugadorActual = 1;
        }
    }

    public boolean insertarFicha(int columna)
    {
        for (int fila=5; fila>=0 ; fila--){
            if (tablero[fila][columna] == 0){
                tablero[fila][columna] = jugadorActual;
                return true;
            }
        }
        return false;
    }

    public int hayGanador()
    {
        for (int fila = 0; fila < 6; fila++){
            for (int columna = 0; columna < 7; columna++) {
                int ficha = tablero[fila][columna];
                if (ficha == 0) continue;

            //VERIFICACION HORIZONTAL
                if (columna <= 3 &&
                    ficha == tablero[fila][columna+1] &&
                    ficha == tablero[fila][columna+2] &&
                    ficha == tablero[fila][columna+3])
                {return ficha;}
            //FIN VERIFICACION HORIZONTAL

            //VERIFICACION VERTICAL
                if (fila <= 2 &&
                    ficha == tablero[fila+1][columna] &&
                    ficha == tablero[fila+2][columna] &&
                    ficha == tablero[fila+3][columna])
                {return ficha;}
            //FIN VERIFICACION VERTICAL

            //VERIFICACION DIAGONAL
                if (fila <= 2 && columna <= 3 &&
                    ficha == tablero[fila+1][columna+1] &&
                    ficha == tablero[fila+2][columna+2] &&
                    ficha == tablero[fila+3][columna+3])
                {return ficha;}

                if (fila >= 3 && columna <= 3 &&
                        ficha == tablero[fila-1][columna+1] &&
                        ficha == tablero[fila-2][columna+2] &&
                        ficha == tablero[fila-3][columna+3]) {
                    return ficha;
                }
            //FIN VERIFICACION DIAGONAL

            }
        }
        return 0;
    }

}
