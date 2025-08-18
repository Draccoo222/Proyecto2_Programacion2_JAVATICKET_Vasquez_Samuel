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
        getContentPane().setBackground(new Color(0xaec3fc));
        
        JLabel l = new JLabel("MANEJO DE EVENTOS");
        l.setSize(100, 100);
        l.setFont(new Font("Arial Black", Font.PLAIN, 35));
        l.setBounds(240, 40, 500, 50);
        add(l);
        
        
        System.out.println("User Actual: " + uMan.getUserActualName() + " Rango: " 
          + ((uMan.getUserActual() != null) ? uMan.getUserActual().getTipoUser() : "nulo"));
        
        salir = new JButton("SALIR");
        salir.setFont(new Font("Arial Black", Font.PLAIN, 12));
        salir.setBounds(320, 180 + 120, 140, 30);
        add(salir);
        
        salir.addActionListener(e -> {
             this.dispose();
             MenuPrincipal a = new MenuPrincipal();
             a.setVisible(true);
        });
     
    }
    
    public void panelAdmin(){
      //  if(uMan.getUserActual() != null && uMan.getUserActual().getTipoUser().equals("admin")){
            JButton crearU = new JButton("Crear Evento");
            JButton editarU = new JButton("Editar Evento");
            JButton elimU = new JButton("Borrar Evento");
            
            JButton[] opcAdmin = {crearU, editarU, elimU};
            
            crearU.addActionListener(e -> {
             this.dispose();
             CrearEvent a = new CrearEvent();
             a.setVisible(true);
            });
            
            editarU.addActionListener(e -> {
                if(uMan.getUserActual().numUserEvents()>= 1){
                    this.dispose();
                    EditarEvent a = new EditarEvent();
                    a.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "PARA PODER EDITAR EVENTO NECESITA POR LO MENOS UN EVENTO CREADO 1");
                }
            });

            crearU.setBounds(320, 180, 140, 30);
            editarU.setBounds(320, 180 + 40, 140, 30);
            elimU.setBounds(320, 180 + 80, 140, 30);
            
            elimU.addActionListener(e ->{
                JTextField selecU = new JTextField();
              

               int result = JOptionPane.showConfirmDialog(
                       null, 
                       selecU,
                       "Escriba el codigo del evento a cancerlar!",
                       JOptionPane.OK_CANCEL_OPTION
               );
               
                if (result == JOptionPane.OK_OPTION) {
                    int code;
                    if (selecU.getText().matches("\\d+")) {
                        code = Integer.parseInt(selecU.getText());
                        Evento eSearch = uMan.getUserActual().buscarEvento(code, 0);
                        if (eSearch != null && !eSearch.cancelado) {
                            eSearch.setCancel();
                            JOptionPane.showMessageDialog(null, "Evento cancelado wxitosamente!");
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
            });
            
 
            for(JButton bot: opcAdmin){
                   bot.setFont(new Font("Arial Black", Font.PLAIN, 12));
                   add(bot);   
               }
        }   
    //}
}
