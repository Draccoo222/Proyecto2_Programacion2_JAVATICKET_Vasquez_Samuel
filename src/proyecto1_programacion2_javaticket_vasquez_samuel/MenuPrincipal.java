/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;
import javax.swing.*;
import java.awt.Component.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author unwir
 */
public class MenuPrincipal extends JFrame {
    private UserManage uMan;
    private JButton login;
    private JButton cerrarLog;
    
     
    public MenuPrincipal(){
        initComp();
        panelAdmin();
    }
    
    public void initComp(){
        uMan = UserManage.getInstance();
        uMan.addAdmin();
        
        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xaec3fc));
        
        JLabel l = new JLabel("JAVA TICKETS");
        l.setSize(100, 100);
        l.setFont(new Font("Arial Black", Font.PLAIN, 35));
        l.setBounds(265, 40, 500, 50);
        add(l);
        
        
        System.out.println("User Actual: " + uMan.getUserActualName() + " Rango: " 
          + ((uMan.getUserActual() != null) ? uMan.getUserActual().getTipoUser() : "nulo"));
        
        login = new JButton("Iniciar Sesion");
       // login.setForeground(Color.red);
        login.setFont(new Font("Arial Black", Font.PLAIN, 12));
        login.setBounds(320, 200, 140, 30);
        add(login);
        
        login.addActionListener(e -> {
             this.dispose();
             LogIn a = new LogIn();
             a.setVisible(true);
        });
        
        if(uMan.getUserActual() != null){
            cerrarLog = new JButton("Cerrar Sesion");
            cerrarLog.setFont(new Font("Arial Black", Font.PLAIN, 12));
            cerrarLog.setBounds(100, 480, 140, 30);
            add(cerrarLog);

            cerrarLog.addActionListener(e -> {
                uMan.cerrarSesion();
                reload();
            });
        }
        
    }
    
    public void reload(){
        this.dispose();
        MenuPrincipal e = new MenuPrincipal();
        e.setVisible(true);
    }
    
    
    public void panelAdmin(){
        if(uMan.getUserActual() != null && uMan.getUserActual().getTipoUser().equals("admin")){
            JButton crearU = new JButton("Crear Usuario");
            JButton editarU = new JButton("Editar Usuario");
            JButton elimE = new JButton("Eliminar Evento");
            JButton editarE = new JButton("Editar Eventos");
            JButton crearE = new JButton("Crear Eventos");

            JButton[] opcAdmin = {crearE, crearU, editarU, editarE, elimE};
            
            crearU.addActionListener(e -> {
            this.dispose();
             CreateUser a = new CreateUser();
             a.setVisible(true);
            });

            crearE.setBounds(320, 180, 140, 30);
            crearU.setBounds(320, 180 + 40, 140, 30);
            editarU.setBounds(320, 180 + 80, 140, 30);
            editarE.setBounds(320, 180 + 120, 140, 30);
            elimE.setBounds(320, 180 + 160, 140, 30);

            for(JButton bot: opcAdmin){
                   bot.setFont(new Font("Arial Black", Font.PLAIN, 12));
                   add(bot);   
               }
           remove(login);
        }   
    }
    
    
    
    public static void main(String[] args){
        MenuPrincipal a = new MenuPrincipal();
        a.setVisible(true);
    
    }
    
}
