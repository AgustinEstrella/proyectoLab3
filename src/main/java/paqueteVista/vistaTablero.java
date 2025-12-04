package paqueteVista;

import javax.swing.*;

public class vistaTablero extends JFrame {

    private JLabel infoTurno;
    private JButton btnColumna1;
    private JButton btnColumna2;
    private JButton btnColumna3;
    private JButton btnColumna4;
    private JButton btnColumna5;
    private JButton btnColumna6;
    private JButton btnColumna7;
    private JButton celda00;
    private JButton celda04;
    private JButton celda01;
    private JButton celda02;
    private JButton celda03;
    private JButton celda05;
    private JButton celda06;
    private JButton celda10;
    private JButton celda11;
    private JButton celda12;
    private JButton celda13;
    private JButton celda14;
    private JButton celda15;
    private JButton celda16;
    private JButton celda20;
    private JButton celda21;
    private JButton celda22;
    private JButton celda23;
    private JButton celda24;
    private JButton celda25;
    private JButton celda26;
    private JButton celda30;
    private JButton celda31;
    private JButton celda32;
    private JButton celda33;
    private JButton celda34;
    private JButton celda35;
    private JButton celda36;
    private JButton celda40;
    private JButton celda41;
    private JButton celda42;
    private JButton celda43;
    private JButton celda44;
    private JButton celda45;
    private JButton celda46;
    private JButton celda50;
    private JButton celda51;
    private JButton celda52;
    private JButton celda53;
    private JButton celda54;
    private JButton celda55;
    private JButton celda56;
    private JPanel seleccionColumnas;
    private JPanel tablero;

    public JLabel getInfoTurno() {
        return infoTurno;
    }

    public JPanel getSeleccionColumnas() {
        return seleccionColumnas;
    }

    public JPanel getTablero() {
        return tablero;
    }

    private JButton[][] botonesTablero;

    public void creacionArrayTablero(){
        botonesTablero = new JButton[][]{
                {celda00, celda01, celda02, celda03, celda04, celda05, celda06},
                {celda10, celda11, celda12, celda13, celda14, celda15, celda16},
                {celda20, celda21, celda22, celda23, celda24, celda25, celda26},
                {celda30, celda31, celda32, celda33, celda34, celda35, celda36},
                {celda40, celda41, celda42, celda43, celda44, celda45, celda46},
                {celda50, celda51, celda52, celda53, celda54, celda55, celda56}
        };
    }

    public JButton[][] getBotonesTablero() {
        return botonesTablero;
    }
}