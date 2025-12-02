package paqueteModelo;

public class jugador {

    public String nombre;
    public int id;

    public jugador(String nombre, int id) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede ser nulo o estar vacío.");
        }
        if (nombre .length() > 20) {
            throw new IllegalArgumentException("El nombre del jugador no puede exceder los 20 caracteres.");
        }
        if (nombre.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre del jugador no puede contener números.");
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

    public void setId(int id) {
        this.id = id;
    }
}