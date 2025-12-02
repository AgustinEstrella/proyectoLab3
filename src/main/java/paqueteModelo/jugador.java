package paqueteModelo;

public class jugador {

    public String nombre;
    public int id;

    public jugador(String nombre, int id) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public int getId() {
        return id;
    }

}