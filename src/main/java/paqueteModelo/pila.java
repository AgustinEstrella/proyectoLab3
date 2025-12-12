package paqueteModelo;

public class pila {

    private nodo frente;
    private nodo fin;

    public pila() {
        this.frente = null;
        this.fin = null;
    }

    public boolean colaVacia() {
        if (frente == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insertar(String info) {
        nodo nuevoNodo = new nodo(info);

        if (colaVacia())
        {
            frente = nuevoNodo;
            } else {
            fin.setSiguiente(nuevoNodo);
        }
        fin = nuevoNodo;
    }

    public String quitar(){
        String aux;

        if (!colaVacia())
        {
            aux = frente.info;
            frente = frente.siguiente;
        } else {
            return null;
        }
        return aux;
    }

    public String imprimirPila(){
      String texto = "";
      nodo aux = frente;

      while (aux != null) {
          texto = texto + aux.info;
          aux = aux.siguiente;
      }
      return texto;
    }
}