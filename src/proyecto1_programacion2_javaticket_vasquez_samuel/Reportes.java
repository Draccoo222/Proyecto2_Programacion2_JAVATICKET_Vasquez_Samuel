/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
/**
 *
 * @author unwir
 */
public class Reportes extends JFrame {
     private UserManage uMan;
   private JButton salir;
   private JPanel buttonPanel;
   
    public Reportes(){
         initComp();
         panelAdmin();
    
    }

     public void initComp(){
        uMan = UserManage.getInstance();
     
        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xA2463));
        
        JLabel l = new JLabel("REPORTES", SwingConstants.CENTER);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 50));
        l.setBounds(150, 40, 500, 50);
        add(l);
        
        System.out.println("User Actual: " + uMan.getUserActualName() + " Rango: " 
          + ((uMan.getUserActual() != null) ? uMan.getUserActual().getTipoUser() : "nulo"));
     
    }
    
    public void panelAdmin(){
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(0xA2463));
        buttonPanel.setBounds(100, 50, 600, 500);
        add(buttonPanel);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);
        
        JButton eventosRealizadosBtn = new JButton("Eventos Realizados");
        eventosRealizadosBtn.setBackground(new Color(0xEBC926));
        eventosRealizadosBtn.setFont(new Font("Serif", Font.BOLD, 18));
        eventosRealizadosBtn.setBorder(botBor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(eventosRealizadosBtn, gbc);

        JButton eventosFuturosBtn = new JButton("Eventos Futuros");
        eventosFuturosBtn.setBackground(new Color(0xEBC926));
        eventosFuturosBtn.setFont(new Font("Serif", Font.BOLD, 18));
        eventosFuturosBtn.setBorder(botBor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(eventosFuturosBtn, gbc);
        
        JButton reporteVentasBtn = new JButton("Eventos Cancelados");
        reporteVentasBtn.setBackground(new Color(0xEBC926));
        reporteVentasBtn.setFont(new Font("Serif", Font.BOLD, 18));
        reporteVentasBtn.setBorder(botBor);
        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(reporteVentasBtn, gbc);
        
        JButton eventosPorFechaBtn = new JButton("Eventos Por Fecha");
        eventosPorFechaBtn.setBackground(new Color(0xEBC926));
        eventosPorFechaBtn.setFont(new Font("Serif", Font.BOLD, 18));
        eventosPorFechaBtn.setBorder(botBor);
        gbc.gridx = 0;
        gbc.gridy = 4;
        buttonPanel.add(eventosPorFechaBtn, gbc);
        
        JButton verPerfilBtn = new JButton("Ver Perfil");
        verPerfilBtn.setBackground(new Color(0xEBC926));
        verPerfilBtn.setFont(new Font("Serif", Font.BOLD, 18));
        verPerfilBtn.setBorder(botBor);
        gbc.gridx = 0;
        gbc.gridy = 5;
        buttonPanel.add(verPerfilBtn, gbc);
        
        salir = new JButton("SALIR");
        salir.setBackground(new Color(0xEBC926));
        salir.setFont(new Font("Serif", Font.BOLD, 18));
        salir.setBorder(botBor);
        gbc.gridx = 0;
        gbc.gridy = 6;
        buttonPanel.add(salir, gbc);

        eventosRealizadosBtn.addActionListener(e -> {
            this.dispose();
            EventosRealizados a = new EventosRealizados();
            a.setVisible(true);
        });
        
        eventosFuturosBtn.addActionListener(e -> {
            this.dispose();
            EventosFuturos a = new EventosFuturos();
            a.setVisible(true);
        });
        
        reporteVentasBtn.addActionListener(e -> {
            this.dispose();
            EventosCancelados a = new EventosCancelados();
            a.setVisible(true);
        });
        
        eventosPorFechaBtn.addActionListener(e -> {
            this.dispose();
            EventosPorRango l = new EventosPorRango();
            l.setVisible(true);
        });
        
        verPerfilBtn.addActionListener(e -> {
            this.dispose();
            new VerPerfil().setVisible(true);
        });

        salir.addActionListener(e -> {
             this.dispose();
             MenuPrincipal a = new MenuPrincipal();
             a.setVisible(true);
        });
    }
    
    public static void main(String[] args){
        new Reportes().setVisible(true);
    
    }
    
}
