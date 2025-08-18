/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;

import javax.swing.*;
import java.awt.Component.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

/**
 *
 * @author unwir
 */
public class MenuPrincipal extends JFrame {

    private UserManage uMan;
    private JButton login;
    private JButton crearE;
    private JButton cerrarLog;

    public MenuPrincipal() {
        initComp();
        panelAdmin();
    }

    public void initComp() {
        uMan = UserManage.getInstance();
        uMan.addAdmin();

        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xA2463));

        JLabel l = new JLabel("JAVA TICKETS");
        l.setSize(100, 100);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 70));
        l.setBounds(140, 50, 800, 100);
        add(l);
        
        System.out.println("User Actual: " + uMan.getUserActualName() + " Rango: "
                + ((uMan.getUserActual() != null) ? uMan.getUserActual().getTipoUser() : "nulo"));

        login = new JButton("Iniciar Sesión");
        login.setBackground(new Color(0xEBC926));
        login.setFont(new Font("Serif", Font.BOLD, 18));
        Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);
        login.setBorder(botBor);
        login.setBounds(310, 200, 180, 35);
        add(login);
        
        login.addActionListener(e -> {
            this.dispose();
            LogIn a = new LogIn();
            a.setVisible(true);
        });
        
       
        if (uMan.getUserActual() != null) {
            cerrarLog = new JButton("Cerrar Sesion");
            cerrarLog.setBackground(new Color(0xEBC926));
            cerrarLog.setFont(new Font("Serif", Font.BOLD, 18));
            cerrarLog.setBorder(botBor);
            cerrarLog.setBounds(80, 400, 140, 30);
            add(cerrarLog);

            cerrarLog.addActionListener(e -> {
                uMan.cerrarSesion();
                reload();
            });
            remove(login);
        }

    }

    public void reload() {
        this.dispose();
        MenuPrincipal e = new MenuPrincipal();
        e.setVisible(true);
    }

   public void panelAdmin() {
        if(uMan.getUserActual() != null){
            Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);
            
            if (uMan.getUserActual() instanceof Admin) {
                JButton adminPan = new JButton("User Manage");
                adminPan.setBackground(new Color(0xEBC926));
                adminPan.setFont(new Font("Serif", Font.BOLD, 18));
                adminPan.setBorder(botBor);
                adminPan.setBounds(300, 200, 200, 40);

                adminPan.addActionListener(e -> {
                    this.dispose();
                    AdminDeUsers aUser = new AdminDeUsers();
                    aUser.setVisible(true);
                });

                add(adminPan);
            }
            if(!(uMan.getUserActual() instanceof Limitado)){
                JButton adminEventPan = new JButton("Event Manage");
                JButton repors = new JButton("Reportes");
                
                adminEventPan.setBackground(new Color(0xEBC926));
                adminEventPan.setFont(new Font("Serif", Font.BOLD, 18));
                adminEventPan.setBorder(botBor);
                
                repors.setBackground(new Color(0xEBC926));
                repors.setFont(new Font("Serif", Font.BOLD, 18));
                repors.setBorder(botBor);

                if(uMan.getUserActual() instanceof Admin){
                    adminEventPan.setBounds(300, 250, 200, 40);
                    repors.setBounds(300, 300, 200, 40);
                } else {
                    adminEventPan.setBounds(300, 200, 200, 40);
                    repors.setBounds(300, 250, 200, 40);
                }
                
                adminEventPan.addActionListener(e -> {
                    this.dispose();
                    AdminEventos aUser = new AdminEventos();
                    aUser.setVisible(true);
                });
                
                repors.addActionListener(e -> {
                    this.dispose();
                    Reportes aUser = new Reportes();
                    aUser.setVisible(true);
                });
                
                add(repors);   
                add(adminEventPan);   
            }
        }
    }

    public static void main(String[] args) {
        MenuPrincipal a = new MenuPrincipal();
        a.setVisible(true);

    }

}
