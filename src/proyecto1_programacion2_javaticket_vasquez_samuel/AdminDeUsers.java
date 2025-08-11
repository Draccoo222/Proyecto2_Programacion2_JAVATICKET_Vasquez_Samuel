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
public class AdminDeUsers extends JFrame {
   private UserManage uMan;
   private JButton salir;
   
    public AdminDeUsers(){
         initComp();
         panelAdmin();
    
    }

     public void initComp(){
        uMan = UserManage.getInstance();
     
        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xaec3fc));
        
        JLabel l = new JLabel("MANEJO DE USERS");
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
            JButton crearU = new JButton("Crear Usuario");
            JButton editarU = new JButton("Editar Usuario");
            JButton elimU = new JButton("Borrar Usuario");
            
            JButton[] opcAdmin = {crearU, editarU, elimU};
            
            crearU.addActionListener(e -> {
            this.dispose();
             CreateUser a = new CreateUser();
             a.setVisible(true);
            });
            
            editarU.addActionListener(e -> {
                if(uMan.cantUsers() >= 2){
                    this.dispose();
                    EditarUser a = new EditarUser();
                    a.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "PARA PODER EDITAR USUARIO NECESITA POR LO MENOS 2 CREADOS");
                
                }
            });

            crearU.setBounds(320, 180, 140, 30);
            editarU.setBounds(320, 180 + 40, 140, 30);
            elimU.setBounds(320, 180 + 80, 140, 30);
            
            elimU.addActionListener(e ->{
                JComboBox<Usuario> selecU = new JComboBox<>();
                ArrayList<Usuario> users = uMan.getUsuarios();
                
                for(Usuario u: users){
                    if(!u.getUserName().equals("Admin")){
                        selecU.addItem(u);
                    }                  
                }

               int result = JOptionPane.showConfirmDialog(
                       null, 
                       selecU,
                       "Seleccione Que Usuario Desea Eliminar!",
                       JOptionPane.OK_CANCEL_OPTION
               );
               
               if(result == JOptionPane.OK_OPTION){
                   Usuario byeU = (Usuario) selecU.getSelectedItem();
                   uMan.getUsuarios().remove(byeU);
                   JOptionPane.showMessageDialog(null, "Usuario Eliminado Exitosamente!");
                   System.out.println("User Actual: " + uMan.getUserActualName() + " Rango: " 
                    + ((uMan.getUserActual() != null) ? uMan.getUserActual().getTipoUser() : "nulo"));
               }
            });
            
 
            for(JButton bot: opcAdmin){
                   bot.setFont(new Font("Arial Black", Font.PLAIN, 12));
                   add(bot);   
               }
        }   
    //}
}
