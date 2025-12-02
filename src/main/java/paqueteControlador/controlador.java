package paqueteControlador;

import paqueteModelo.Modelo;
import paqueteVista.vistaLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controlador implements ActionListener {

    private Modelo modeloJuego;
    private vistaLogin vistaLoginJuego;

    public controlador(paqueteModelo.Modelo modeloJuego, vistaLogin vistaLoginJuego) {
        this.modeloJuego = modeloJuego;
        this.vistaLoginJuego = vistaLoginJuego;
        this.vistaLoginJuego.getBtnIngresar().addActionListener(this);
    }

    @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == vistaLoginJuego.getBtnIngresar()) {
                try {
                    String nombre1 = vistaLoginJuego.getTxtNombre1().getText();
                    String nombre2 = vistaLoginJuego.getTxtNombre2().getText();
                    modeloJuego.crearJugadores(nombre1, nombre2);

                    JOptionPane.showConfirmDialog(vistaLoginJuego,"Abriendo juego para " + nombre1 + " y " + nombre2);
                } catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(vistaLoginJuego,ex.getMessage());
                    return;
                }
            }
        }

}
