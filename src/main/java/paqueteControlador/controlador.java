package paqueteControlador;

//librerias
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.Timer;
import java.awt.Color;

//paquetes
import paqueteModelo.Modelo;
import paqueteVista.vistaLogin;
import paqueteVista.vistaTablero;
import paqueteLog.logPartida;

public class controlador implements ActionListener {

    private Modelo modeloJuego;
    private vistaLogin vistaLoginJuego;
    private vistaTablero vistaTableroJuego;
    private logPartida logsPartida;

    public controlador(Modelo modeloJuego, vistaLogin vistaLoginJuego) {
        this.modeloJuego = modeloJuego;
        this.vistaLoginJuego = vistaLoginJuego;
        this.vistaLoginJuego.getBtnIngresar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLoginJuego.getBtnIngresar()) {
            vistaLoginJuego.vaciarTextoLogin();
            try {
                String nombre1 = vistaLoginJuego.getTxtNombre1().getText();
                String nombre2 = vistaLoginJuego.getTxtNombre2().getText();
                modeloJuego.crearJugadores(nombre1, nombre2);

                vistaLoginJuego.mostrarTextoLogin(nombre1, nombre2);

                //aca entramos al tablero esperando 1 segundo
                Timer timer = new Timer(1000, event -> {
                    vistaLoginJuego.dispose();

                    vistaTableroJuego = new vistaTablero();
                    vistaTableroJuego.setVisible(true);

                    logsPartida = new logPartida();
                    logsPartida.crearArchivoPartida();

                    vistaTableroJuego.creacionArrayTablero();
                    vistaTableroJuego.creacionArrayColumnas();

                    vistaTableroJuego.getInfoTurno().setText(modeloJuego.getJugadorActual());

                    agregarListenerColumnasYHacerMovimiento();

                });
                timer.setRepeats(false);
                timer.start();

            } catch (IllegalArgumentException ex) {
                vistaLoginJuego.mostrarExepcion(ex);
            }
        }
    }

    //metodos de conexion

    public void agregarListenerColumnasYHacerMovimiento() {
        for (int i = 0; i < vistaTableroJuego.getBotonesColumna().length; ++i) {
            int columna = i;
            vistaTableroJuego.getBotonesColumna()[i].addActionListener(ev -> {
                insertarFicha(columna);
            });
        }
    }

    public void insertarFicha(int columna){
        boolean confirmacion = modeloJuego.insertarFicha(columna);

        if (!confirmacion){
            vistaTableroJuego.ingresoFallido();
            return;
        }

        vistaTableroJuego.vaciarTextoColumnaLlena();

        vistaTableroJuego.pintarTablero(modeloJuego.getTablero());


        logsPartida.guardarJugadaEnPila(columna, modeloJuego.getNombreJugadorActual());

        int ganadorId = modeloJuego.hayGanador();

        if (ganadorId != 0){
            hayVictoria(ganadorId);

        } else if (modeloJuego.tableroLleno()) {
            hayEmpate();
        } else {
            modeloJuego.cambiarTurno();
            vistaTableroJuego.getInfoTurno().setText(modeloJuego.getJugadorActual());
        }

    }

    private void hayEmpate() {
        vistaTableroJuego.mostrarTextoEmpate();
        Timer timer = new Timer(3000, event -> {
            logsPartida.cerraryMostrarArchivo("NADIE! HUBO UN EMPATE");
        });
        timer.setRepeats(false);
        timer.start();

        JButton[] botonesColumnas = vistaTableroJuego.getBotonesColumna();
        for (int i = 0; i < botonesColumnas.length; i++) {
            botonesColumnas[i].setEnabled(false);
        }
    }

    private void hayVictoria(int ganadorId) {
        vistaTableroJuego.mostrarVictoria(modeloJuego.getNombreJugadorActual());

        Timer timer = new Timer(3000, event -> {
            logsPartida.cerraryMostrarArchivo(modeloJuego.getNombreJugadorActual());
        });
        timer.setRepeats(false);
        timer.start();

        ArrayList<int[]> listaGanadora = modeloJuego.getCoordenadasGanadoras();

        vistaTableroJuego.mostrarFilaVictoriosa(listaGanadora);
    }

}
