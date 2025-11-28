package miPila;

public class nodo
{
    private String info;
    private nodo siguiente;

    public nodo(String info) {
        this.info = info;
        this.siguiente = null;
    }

    public void setSiguiente(nodo siguiente) {
        this.siguiente = siguiente;
    }

    public String getInfo() {
        return info;
    }
}
