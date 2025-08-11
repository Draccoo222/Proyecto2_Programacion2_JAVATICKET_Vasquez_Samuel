/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 *
 * @author unwir
 */
public class EditarUser extends JFrame {
    private UserManage uMan;
    private JButton login;
    private JButton regresar;
    
    private JTextField nombreComp = new JTextField();
    private JTextField uName = new JTextField();
    
    private JTextField eD = new JTextField();
    private JTextField passW = new JTextField();
    
    private String[] opc = {"admin", "limitados", "contenidos"};
    
    private JComboBox<String> type = new JComboBox<>(opc);
    
    private JComboBox<Usuario> ola;
    
    
    public EditarUser(){
    
        initComps();
    }
    
    
    private void initComps(){
        uMan = UserManage.getInstance();
        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xaec3fc));
        
        setBackground(Color.red);
        
        JLabel l = new JLabel("Editar Usuario");
        l.setSize(100, 100);
        l.setFont(new Font("Arial Black", Font.PLAIN, 35));
        l.setBounds(275, 30, 500, 50);
        add(l);
        
        login = new JButton("Creacion");
        login.setForeground(Color.green);
        login.setFont(new Font("Arial Black", Font.PLAIN, 12));
        login.setBounds(275, 435, 100, 30);
        
        login.addActionListener(e ->{
            creacion();
        });
        
        add(login);
        
        regresar = new JButton("SALIR");
        regresar.setForeground(Color.red);
        regresar.setFont(new Font("Arial Black", Font.PLAIN, 12));
        regresar.setBounds(450, 435, 100, 30);
        
        regresar.addActionListener(e ->{
            salir();
        });
        
        add(regresar);
        
          
        JLabel nomTitle = new JLabel();
        nomTitle.setText("Nombre Completo");
        nomTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        nomTitle.setBounds(300, 175, 200, 30);
        
        nombreComp.setBounds(300, 200, 200, 20);
        
        add(nomTitle);
        add(nombreComp);
        
    
        JLabel uTitle = new JLabel();
        uTitle.setText("Nombre de Usuario");
        uTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        uTitle.setBounds(300, 175 + 50, 200, 30);
        
        uName.setBounds(300, 200 + 50, 200, 20);
        
        add(uTitle);
        add(uName);
        
        JLabel eTitle = new JLabel();
        eTitle.setText("Edad");
        eTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        eTitle.setBounds(300, 175 + 100, 200, 30);
        
        eD.setBounds(300, 200 + 100, 200, 20);
        
        add(eTitle);
        add(eD);
        
        JLabel passWTitle = new JLabel();
        passWTitle.setText("Contraseña");
        passWTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        passWTitle.setBounds(300, 175 + 150, 200, 30);
        
        passW.setBounds(300, 200 + 150, 200, 20);
        
        add(passWTitle);
        add(passW);
        
        JLabel typeTitle = new JLabel();
        typeTitle.setText("Tipo de User");
        typeTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        typeTitle.setBounds(300, 175 + 200, 200, 30);
        
        type.setBounds(300, 200 + 200, 200, 20);
        
        add(typeTitle);
        add(type);
        
        
         ArrayList<Usuario> users = uMan.getUsuarios();
        
        ola = new JComboBox<>();
        
        for(Usuario u: users){
            if(!u.getNombreCompleto().equals("Administrador")){
                ola.addItem(u);
            }
        }
        
        JLabel selecU = new JLabel();
        selecU.setText("Usuario");
        selecU.setFont(new Font("Arial Black", Font.PLAIN, 14));
        selecU.setBounds(300, 175 - 50, 200, 30);
       
        ola.setBounds(300, 200 - 50, 200, 20);
        add(selecU);
        add(ola);
        
    
        
        
         for(Component b: getContentPane().getComponents()){
            if(b != ola && !(b instanceof JLabel) &&  b!= regresar){
                 b.setEnabled(false);
            }
             
        }
       
        ola.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Object seleccionado = ola.getSelectedItem();
                boolean hab = (seleccionado != null);
                    for(Component b: getContentPane().getComponents()){
                        if(b != ola && !(b instanceof JLabel) && b!= regresar){
                            b.setEnabled(hab);
                        }
                    }
            }
        });
        
    }
    
    private void creacion(){   
        try{
            int edad;

            String tipo = (String) type.getSelectedItem();
            String nombreC = nombreComp.getText().trim();
            String pass = passW.getText().trim();
            String userN = uName.getText().trim();
            Usuario userSelect = (Usuario) ola.getSelectedItem();
            int index = uMan.getUsuarios().indexOf(userSelect);


             if(nombreComp.getText().trim().isEmpty()){
                nombreC = userSelect.getNombreCompleto();
             }  
             if(uName.getText().trim().isEmpty()){
                userN = userSelect.getUserName();
             }
             if(passW.getText().trim().isEmpty()){
                pass = userSelect.getPassW();
             }  
             if(eD.getText().trim().isEmpty()){
                edad = userSelect.getEdad();
             }else{
                edad = Integer.parseInt(eD.getText());
             }
             if(type.getSelectedItem() == null){
                 tipo = userSelect.getTipoUser();
             }


             if(uMan.confirmarContra(pass)){
                uMan.getUsuarios().remove(userSelect);
                uMan.insert(index, tipo, edad, nombreC, userN, pass);
                JOptionPane.showMessageDialog(null, "Usuario editado con exito!");
                salir();
                System.out.println(uMan.listaUsers(uMan.cantUsers() - 1));
            }else{
                JOptionPane.showMessageDialog(null, "Porfavor incluir caracteres especiales, numeros, mayusculas y minusculas "
                        + "en la contraseña");
             }
        }catch(Exception e){
            System.out.println("ERROR XD");
        
        }
    }
    
   
    private void salir(){
        this.dispose();
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }
 

    
}
