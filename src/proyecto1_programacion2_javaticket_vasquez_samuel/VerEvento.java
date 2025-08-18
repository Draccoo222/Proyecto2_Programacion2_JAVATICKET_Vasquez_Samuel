/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author unwir
 */
public class VerEvento extends JFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTextArea txtResultado;
    private UserManage uMan;

    // Evento encontrado
    private Evento eSelected;

    public VerEvento() {
        uMan = UserManage.getInstance();
     setTitle("Buscar Evento");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fondo azul como en el original
        getContentPane().setBackground(new Color(0, 102, 204));
        setLayout(null); // uso absoluto como traía tu clase

        // Etiqueta
        JLabel lblTitulo = new JLabel("Buscar Evento");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(180, 20, 200, 30);
        add(lblTitulo);

        // Campo de búsqueda
        JLabel lblNombre = new JLabel("Codigo del Evento:");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setBounds(50, 80, 200, 30);
        add(lblNombre);

        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtBuscar.setBounds(220, 80, 180, 30);
        add(txtBuscar);

        // Botón buscar
        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.setBounds(180, 130, 120, 35);
        add(btnBuscar);

        // Área de resultado
        txtResultado = new JTextArea();
        txtResultado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(50, 190, 380, 140);
        add(scroll);
      
        // Acción del botón buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtBuscar.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre para buscar.");
                    return;
                }

               
                 Evento eSelec = buscarEv(); // aquí usamos tu método ya existente
               

                if (eSelec != null) {
                    txtResultado.setText(eSelec.toString());
                } else {
                    txtResultado.setText("Evento no encontrado.");
                }
            }
        });
    }

    // Tu método ya existente (ejemplo de firma, adáptalo a tu código real)
    private Evento buscarEv() {
       int code;
       
       try{
        if (txtBuscar.getText().isEmpty()) {
            throw new EventException("No se puede buscar el evento si deja el codigo vacio.");
        }

        code = Integer.parseInt(txtBuscar.getText().trim());

        eSelected = uMan.buscarEvento(code, 0);
        if (eSelected == null) {
            throw new EventException("Evento no encontrado");
        }
        if(eSelected.isCancelado()){
           throw new EventException("No puede editar un evento que ha sido cancelado, pues queda inhabilidado");
        }
       }catch(EventException em){
           JOptionPane.showMessageDialog(null, em.getMessage());
       
       }
        return eSelected;
    
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VerEvento().setVisible(true);
        });
    }
}