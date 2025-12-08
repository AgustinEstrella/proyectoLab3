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
        if (e.getSource() == vistaLoginJuego.getBtnIngresar()) {
            this.vistaLoginJuego.getLineaStatus().setText("");

            try {
                String nombre1 = vistaLoginJuego.getTxtNombre1().getText();
                String nombre2 = vistaLoginJuego.getTxtNombre2().getText();
                modeloJuego.crearJugadores(nombre1, nombre2);

                vistaLoginJuego.getLineaStatus().setForeground(new Color(0, 255, 0));
                vistaLoginJuego.getLineaStatus().setText("Abriendo juego para " + nombre1 + " y " + nombre2);

                //aca entramos al tablero esperando 1 segundo
                Timer timer = new Timer(1000, event -> {
                    vistaLoginJuego.dispose();

                    vistaTableroJuego = new vistaTablero();
                    vistaTableroJuego.setVisible(true);

                    modeloJuego.crearArchivoPartida();

                    vistaTableroJuego.creacionArrayTablero();
                    vistaTableroJuego.creacionArrayColumnas();

                    vistaTableroJuego.getInfoTurno().setText(modeloJuego.getJugadorActual());

                    agregarListenerColumnas();

                });
                timer.setRepeats(false);
                timer.start();

            } catch (IllegalArgumentException ex) {
                vistaLoginJuego.getLineaStatus().setForeground(new Color(255, 0, 0));
                vistaLoginJuego.getLineaStatus().setText(ex.getMessage());
            }
        }
    }

    public void agregarListenerColumnas() {
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
            vistaTableroJuego.getAvisoLlena().setText("COLUMNA LLENA. VUELVA A INTENTARLO");
            vistaTableroJuego.getAvisoLlena().setForeground(new Color(255, 0, 0));
            return;
        }
        vistaTableroJuego.getAvisoLlena().setText("");

        actualizarTablero();
        modeloJuego.guardarJugadaEnArchivo(columna, modeloJuego.getNombreJugadorActual());

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

    private void actualizarTablero() {
        int[][] tablero = modeloJuego.getTablero();
        JButton[][] botonera = vistaTableroJuego.getBotonesTablero();

        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 7; columna++)
            {
                int valorCelda = tablero[fila][columna];

                if (valorCelda == 1) {
                    botonera[fila][columna].setBackground(Color.BLUE);
                    botonera[fila][columna].setOpaque(true);
                } else {
                    if (valorCelda == 2) {
                        botonera[fila][columna].setBackground(Color.YELLOW);
                        botonera[fila][columna].setOpaque(true);
                    }
                }
            }
        }
    }

    private void hayEmpate() {
        vistaTableroJuego.getInfoTurno().setText("¡HAY EMPATE! EL TABLERO ESTÁ LLENO");
        vistaTableroJuego.getInfoTurno().setForeground(Color.ORANGE);
        Timer timer = new Timer(3000, event -> {
            modeloJuego.cerraryMostrarArchivo("NADIE! HUBO UN EMPATE");
        });
        timer.setRepeats(false);
        timer.start();

        JButton[] botonesColumnas = vistaTableroJuego.getBotonesColumna();
        for (int i = 0; i < botonesColumnas.length; i++) {
            botonesColumnas[i].setEnabled(false);
        }
    }

    private void hayVictoria(int ganadorId) {
        String mensajeVictoria = modeloJuego.getJugadorActual().replace("Turno de ", "EL GANADOR ES: ");
        vistaTableroJuego.getInfoTurno().setForeground(Color.GREEN);
        vistaTableroJuego.getInfoTurno().setText(mensajeVictoria);

        Timer timer = new Timer(3000, event -> {
            modeloJuego.cerraryMostrarArchivo(modeloJuego.getNombreJugadorActual());
        });
        timer.setRepeats(false);
        timer.start();

        ArrayList<int[]> listaGanadora = modeloJuego.getCoordenadasGanadoras();

        for (int i = 0; i < listaGanadora.size(); i++) {
            int[] coordenada = listaGanadora.get(i);
            int fila = coordenada[0];
            int columna = coordenada[1];

            vistaTableroJuego.getBotonesTablero()[fila][columna].setBackground(Color.GREEN);
            vistaTableroJuego.getBotonesTablero()[fila][columna].setOpaque(true);
        }

        JButton[] botonesColumnas = vistaTableroJuego.getBotonesColumna();
        for (int i = 0; i < botonesColumnas.length; i++) {
            botonesColumnas[i].setEnabled(false);
        }
    }

}
