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
import javax.swing.border.Border;

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
        getContentPane().setBackground(new Color(0xA2463));
        
        JLabel l = new JLabel("MANEJO DE USERS");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 50));
        l.setBounds(150, 40, 500, 50);
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
        if(uMan.getUserActual() != null && uMan.getUserActual().getTipoUser().equals("admin")){
            JButton crearU = new JButton("Crear Usuario");
            JButton editarU = new JButton("Editar Usuario");
            JButton elimU = new JButton("Borrar Usuario");
            
            Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);
            
            crearU.setBackground(new Color(0xEBC926));
            crearU.setFont(new Font("Serif", Font.BOLD, 18));
            crearU.setBorder(botBor);
            
            editarU.setBackground(new Color(0xEBC926));
            editarU.setFont(new Font("Serif", Font.BOLD, 18));
            editarU.setBorder(botBor);

            elimU.setBackground(new Color(0xEBC926));
            elimU.setFont(new Font("Serif", Font.BOLD, 18));
            elimU.setBorder(botBor);

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

            crearU.setBounds(300, 200, 180, 35);
            editarU.setBounds(300, 250, 180, 35);
            elimU.setBounds(300, 300, 180, 35);
            
            elimU.addActionListener(e ->{
                if(uMan.getUsuarios().size() > 2)
                {
                JComboBox<Usuario> selecU = new JComboBox<>();
                ArrayList<Usuario> users = uMan.getUsuarios();
                
                for(Usuario u: users){
                    if(!u.getUserName().equals("admin")){
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
               }else{
                    JOptionPane.showMessageDialog(null, "Para poder eliminar un usuario debe de haber mas de uno");
                }
            });
            
            add(crearU);
            add(editarU);
            add(elimU);
        }   
    }
    //}
}
