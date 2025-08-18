/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;
import javax.swing.*;
import java.awt.Component.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.border.Border;

/**
 *
 * @author unwir
 */
public class AdminEventos extends JFrame {
   private UserManage uMan;
   private JButton salir;
   
    public AdminEventos(){
         initComp();
         panelAdmin();
    
    }

  public void initComp(){
        uMan = UserManage.getInstance();
     
        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xA2463));
        
        JLabel l = new JLabel("MANEJO DE EVENTOS");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 50));
        l.setBounds(150, 40, 800, 50);
        add(l);
        
        
        System.out.println("User Actual: " + uMan.getUserActualName() + " Rango: " 
          + ((uMan.getUserActual() != null) ? uMan.getUserActual().getTipoUser() : "nulo"));
        
        salir = new JButton("SALIR");
        salir.setBackground(new Color(0xEBC926));
        salir.setFont(new Font("Serif", Font.BOLD, 18));
        Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);
        salir.setBorder(botBor);
        salir.setBounds(300, 350, 180, 35);
        add(salir);
        
        salir.addActionListener(e -> {
             this.dispose();
             MenuPrincipal a = new MenuPrincipal();
             a.setVisible(true);
        });
     
    }
    
    public void panelAdmin(){
      //  if(uMan.getUserActual() != null && uMan.getUserActual().getTipoUser().equals("admin")){
            JButton crearE = new JButton("Crear Evento");
            JButton editarE = new JButton("Editar Evento");
            JButton elimE = new JButton("Cancelar Evento");
            
            JButton[] opcAdmin = {crearE, editarE, elimE};
            
            Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);
            
            crearE.setBackground(new Color(0xEBC926));
            crearE.setFont(new Font("Serif", Font.BOLD, 18));
            crearE.setBorder(botBor);
            
            editarE.setBackground(new Color(0xEBC926));
            editarE.setFont(new Font("Serif", Font.BOLD, 18));
            editarE.setBorder(botBor);

            elimE.setBackground(new Color(0xEBC926));
            elimE.setFont(new Font("Serif", Font.BOLD, 18));
            elimE.setBorder(botBor);
            
            crearE.addActionListener(e -> {
             this.dispose();
             CrearEvent a = new CrearEvent();
             a.setVisible(true);
            });
            
            editarE.addActionListener(e -> {
                if(uMan.getNumEventos()>= 1){
                    this.dispose();
                    EditarEvent a = new EditarEvent();
                    a.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "PARA PODER EDITAR EVENTO NECESITA POR LO MENOS UN EVENTO CREADO 1");
                }
            });

            crearE.setBounds(300, 200, 180, 35);
            editarE.setBounds(300, 250, 180, 35);
            elimE.setBounds(300, 300, 180, 35);
            
       
        elimE.addActionListener(e -> {
            if (uMan.getUserActual().numUserEvents() >= 1 || (uMan.getUserActual().getUserName().equalsIgnoreCase("admin") && uMan.getNumEventos() >= 1)) {
                JTextField selecU = new JTextField();

                int result = JOptionPane.showConfirmDialog(
                        null,
                        selecU,
                        "Escriba el codigo del evento a cancelar!",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (result == JOptionPane.OK_OPTION) {
                    int code;
                    if (selecU.getText().matches("\\d+")) {
                        code = Integer.parseInt(selecU.getText());
                        Evento eSearch;
                        if (uMan.getUserActual().getUserName().equalsIgnoreCase("admin")) {
                            eSearch = uMan.buscarEvento(code, 0);
                        } else {
                            eSearch = uMan.getUserActual().buscarEvento(code, 0);
                        }
                        if (eSearch != null && !eSearch.cancelado) {
                            Calendar tom = (Calendar) eSearch.getFecha().clone();
                            tom.add(Calendar.DAY_OF_MONTH, -1);
                            
                            Calendar hoy = Calendar.getInstance();
                            hoy.set(Calendar.HOUR_OF_DAY, 0);
                            hoy.set(Calendar.SECOND, 0);
                            hoy.set(Calendar.MILLISECOND, 0);
                            hoy.set(Calendar.MINUTE, 0);
                            
                            if(hoy.equals(tom)){
                                if(eSearch instanceof EventoDeportivo || eSearch instanceof EventoMusical){           
                                   JOptionPane.showMessageDialog(null, "Atencion! Se cancelara un evento un dia antes\n Tendra una penalizacion");
                                   eSearch.indebnizar();
                                }
                            }
                            
                            eSearch.setCancel();
                            JOptionPane.showMessageDialog(null, "Evento cancelado exitosamente!");
                            System.out.println(uMan.getEventosTotales());
                        } else if (eSearch != null && eSearch.cancelado) {
                            JOptionPane.showMessageDialog(null, "El evento ya fue cancelado!");
                        } else {
                            JOptionPane.showMessageDialog(null, "El evento no existe o no tiene permiso para eliminarlo!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Use un numero entero positivo para buscar");

                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Para poder eliminar un evento debe de haber mas de uno");
            }
        });
       
            for(JButton bot: opcAdmin){
                   add(bot);   
               }
        }   
    //}
}
