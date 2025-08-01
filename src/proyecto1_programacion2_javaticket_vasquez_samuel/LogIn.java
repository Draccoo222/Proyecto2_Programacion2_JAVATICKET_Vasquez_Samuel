/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author unwir
 */
public class LogIn extends JFrame {
    private UserManage uMan;
    private JButton login;
    private JButton regresar;
    private JTextField nombreComp;
    private JTextField uName;
    private JTextField passW;
    
    public LogIn(){
    
        initComps();
    }
    
    
    private void initComps(){
        uMan = UserManage.getInstance();
      
        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        setBackground(Color.red);
        
        JLabel l = new JLabel("LOG IN");
        l.setSize(100, 100);
        l.setFont(new Font("Arial Black", Font.PLAIN, 35));
        l.setBounds(265, 40, 500, 50);
        add(l);
        
        
        
        
        login = new JButton("LOG IN");
        login.setForeground(Color.red);
        login.setFont(new Font("Arial Black", Font.PLAIN, 12));
        login.setBounds(300, 500, 100, 30);
        add(login);
     
    }
    
    
    
   
    
}
