//paquetes
import paqueteControlador.controlador;
import paqueteModelo.Modelo;
import paqueteVista.vistaLogin;

class main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        vistaLogin vistaLoginJuego = new vistaLogin();
        new controlador(modelo, vistaLoginJuego);

        vistaLoginJuego.setVisible(true);
    }
}