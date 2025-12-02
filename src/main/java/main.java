import paqueteModelo.Modelo;
import paqueteControlador.controlador;
import paqueteVista.vistaLogin;

 class main {
public static void main(String[] args) {

    Modelo modelo = new Modelo();
    vistaLogin vistaLoginJuego = new vistaLogin();
    controlador controladorJuego = new controlador(modelo, vistaLoginJuego);

    vistaLoginJuego.setVisible(true);
    }
 }
