package paqueteModelo;

public class jugador {
    public String nombre;
    public int id;

    public jugador(String nombre, int id) {
        if (nombre != null && !nombre.isEmpty()) {
            if (nombre.length() > 20) {
                throw new IllegalArgumentException("El nombre del jugador no puede exceder los 20 caracteres.");
            } else if (!nombre.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")) { //Evitar poner numeros o simbolos
                throw new IllegalArgumentException("El nombre del jugador no puede contener números o simbolos");
            } else {
                this.nombre = nombre;
                this.id = id;
            }
        } else {
            throw new IllegalArgumentException("El nombre del jugador no puede ser nulo o estar vacío.");
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
