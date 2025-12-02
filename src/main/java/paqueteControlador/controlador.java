package paqueteControlador;
import paqueteModelo.Modelo;
import paqueteVista.Vista;

public class controlador {

    private Modelo modeloJuego;
    private Vista vistaJuego;

    public controlador(paqueteModelo.Modelo modeloJuego, paqueteVista.Vista vistaJuego) {
        this.modeloJuego = modeloJuego;
        this.vistaJuego = vistaJuego;
    }



}
