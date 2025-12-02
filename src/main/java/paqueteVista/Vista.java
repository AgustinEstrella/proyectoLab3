package paqueteVista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista extends JFrame{
    private JPanel contentPane;
    private JTextField txtNombre1;
    private JTextField txtNombre2;
    private JButton btnIngresar;
//--------------------------------------------------//

    //Evento para que al presionar ingresar se creen los jugadores (clase jugador en modelo)
    public Vista() {
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }




}

