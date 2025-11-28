package miPila;

public class pila {

    private nodo cima;

    public pila(){
        cima = null;
    }

    public boolean pilaVacia()
    {
        if (cima==null){
            return true;
        } else {
            return false;
        }
    }

    public void insertar(String info)
    {
        nodo nuevo = new nodo(info);
        nuevo.setSiguiente(cima);
        cima = nuevo;
    }

    public String quitar()
    {
        if (pilaVacia())
        {
            return null;
        }

        String info = cima.getInfo();
        return info;
    }

}
