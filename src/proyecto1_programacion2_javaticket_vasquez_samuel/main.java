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
public class main extends JFrame {
    public main(){
        setSize(500, 320);
        setLayout(null);
        
        JLabel l = new JLabel("Hola Mundo");
        l.setBounds(210, 10, 500, 50);
        
        add(l);
    
    }
    
    public static void main(String[] args){
        main a = new main();
        a.setVisible(true);
    
    }
    
}
