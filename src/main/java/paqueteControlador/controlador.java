//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package paqueteControlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import paqueteModelo.Modelo;
import paqueteVista.vistaLogin;
import paqueteVista.vistaTablero;

public class controlador implements ActionListener {
    private Modelo modeloJuego;
    private vistaLogin vistaLoginJuego;
    private vistaTablero vistaTableroJuego;

    public controlador(Modelo modeloJuego, vistaLogin vistaLoginJuego) {
        this.modeloJuego = modeloJuego;
        this.vistaLoginJuego = vistaLoginJuego;
        this.vistaLoginJuego.getBtnIngresar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaLoginJuego.getBtnIngresar()) {
            this.vistaLoginJuego.getLineaStatus().setText("");

            try {
                String nombre1 = this.vistaLoginJuego.getTxtNombre1().getText();
                String nombre2 = this.vistaLoginJuego.getTxtNombre2().getText();
                this.modeloJuego.crearJugadores(nombre1, nombre2);
                this.vistaLoginJuego.getLineaStatus().setForeground(new Color(0, 255, 0));
                this.vistaLoginJuego.getLineaStatus().setText("Abriendo juego para " + nombre1 + " y " + nombre2);

                //Aca entramos al tablero esperando 1 segundo para dar tiempo a leer la confirmacion
                Timer timer = new Timer(1000, event -> {
                    vistaLoginJuego.dispose();
                    vistaTableroJuego = new vistaTablero();
                    vistaTableroJuego.setVisible(true);
                });
                timer.setRepeats(false); // Se ejecuta solo una vez
                timer.start();




            } catch (IllegalArgumentException ex) {
                this.vistaLoginJuego.getLineaStatus().setForeground(new Color(255, 0, 0));
                this.vistaLoginJuego.getLineaStatus().setText(ex.getMessage());
                return;
            }
        }

    }

    private void Jugar (){

    }

}
