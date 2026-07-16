// METODOS Y LOGICA DE JUGADORES
private jugador jugador_1;
private jugador jugador_2;

ArrayList<jugador> jugadores = new ArrayList<>();
private jugador jugador_actual;

public void crear_jugadores(String nombre_1, String nombre_2) {
    jugador_1 = new jugador(nombre_1, 1);
    jugador_2 = new jugador(nombre_2, 2);
    jugadores.add(jugador_1);
    jugadores.add(jugador_2);
    this.jugador_actual = jugadores.get(0);
}

public String get_jugador_actual() {
    return "Turno de " + jugador_actual.getNombre();
}

private int[][] tablero = new int[6][7];

// METODOS DE TABLERO
public void empezar_partida() {
    tablero = new int[6][7];
}

public int[][] get_tablero() {
    return tablero;
}

// METODOS DE JUEGO
public void cambiar_turno() {
    if (jugador_actual == jugadores.get(0)) {
        jugador_actual = jugadores.get(1);
    } else {
        jugador_actual = jugadores.get(0);
    }
}

public boolean insertar_ficha(int columna) {
    for (int fila = 5; fila >= 0; fila--) {
        if (tablero[fila][columna] == 0) {
            tablero[fila][columna] = jugador_actual.getId();
            return true;
        }
    }
    return false;
}

// METODOS DE RESULTADO DE PARTIDA
private ArrayList<int[]> coordenadas_ganadoras = new ArrayList<>();

public ArrayList<int[]> get_coordenadas_ganadoras() {
    return coordenadas_ganadoras;
}

public int hay_ganador() {
    coordenadas_ganadoras.clear();
    // resto del método...
}
