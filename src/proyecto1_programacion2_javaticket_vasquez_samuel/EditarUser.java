/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.Border;
/**
 *
 * @author unwir
 */
public class EditarUser extends JFrame {
    private UserManage uMan;
    private JButton login;
    private JButton regresar;
    
    private JTextField nombreComp = new JTextField(22);
    private JTextField uName = new JTextField(22);
    
    private JTextField eD = new JTextField(22);
    private JTextField passW = new JTextField(22);
    
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
        getContentPane().setBackground(new Color(0xA2463));
        
        JLabel l = new JLabel("EDITAR USUARIO");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 50));
        l.setBounds(190, 30, 500, 50);
        add(l);
        
        // Panel para los campos de entrada
        JPanel panelEditar = new JPanel();
        panelEditar.setLayout(new GridBagLayout());
        panelEditar.setBackground(new Color(0xA2463));
        panelEditar.setBounds(150, 100, 500, 300);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Usuario a Editar
        JLabel selecU = new JLabel("Usuario");
        selecU.setFont(new Font("Serif", Font.BOLD, 18));
        selecU.setForeground(Color.WHITE);
        
        ArrayList<Usuario> users = uMan.getUsuarios();
        ola = new JComboBox<>();
        
        for(Usuario u : users){
            if(!u.getNombreCompleto().equals("Administrador")){
                ola.addItem(u);
            }
        }
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEditar.add(selecU, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelEditar.add(ola, gbc);

        // Nombre Completo
        JLabel nomTitle = new JLabel("Nombre Completo");
        nomTitle.setFont(new Font("Serif", Font.BOLD, 18));
        nomTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEditar.add(nomTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelEditar.add(nombreComp, gbc);
        
        // Nombre de Usuario
        JLabel uTitle = new JLabel("Nombre de Usuario");
        uTitle.setFont(new Font("Serif", Font.BOLD, 18));
        uTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelEditar.add(uTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelEditar.add(uName, gbc);
        
        // Edad
        JLabel eTitle = new JLabel("Edad");
        eTitle.setFont(new Font("Serif", Font.BOLD, 18));
        eTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelEditar.add(eTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelEditar.add(eD, gbc);
        
        // Contraseña
        JLabel passWTitle = new JLabel("Contraseña");
        passWTitle.setFont(new Font("Serif", Font.BOLD, 18));
        passWTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelEditar.add(passWTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelEditar.add(passW, gbc);
        
        // Tipo de Usuario
        JLabel typeTitle = new JLabel("Tipo de Usuario");
        typeTitle.setFont(new Font("Serif", Font.BOLD, 18));
        typeTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelEditar.add(typeTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelEditar.add(type, gbc);
        
        add(panelEditar);
        
        // Botones
        Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);

        JButton loginBtn = new JButton("EDITAR");
        loginBtn.setBackground(new Color(0xEBC926));
        loginBtn.setFont(new Font("Serif", Font.BOLD, 18));
        loginBtn.setBorder(botBor);
        loginBtn.setBounds(200, 420, 180, 35);
        add(loginBtn);
        
        JButton regresarBtn = new JButton("REGRESAR");
        regresarBtn.setBackground(new Color(0xEBC926));
        regresarBtn.setFont(new Font("Serif", Font.BOLD, 18));
        regresarBtn.setBorder(botBor);
        regresarBtn.setBounds(400, 420, 180, 35);
        add(regresarBtn);
        
        // Habilitar/Deshabilitar campos
        for(Component b : panelEditar.getComponents()){
            if(b != ola && !(b instanceof JLabel)){
                 b.setEnabled(false);
            }
        }
       
        ola.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Object seleccionado = ola.getSelectedItem();
                boolean hab = (seleccionado != null);
                for(Component b : panelEditar.getComponents()){
                    if(b != ola && !(b instanceof JLabel)){
                        b.setEnabled(hab);
                    }
                }
            }
        });

        loginBtn.addActionListener(e -> creacion());
        regresarBtn.addActionListener(e -> salir());
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

            if (!eD.getText().matches("\\d+")) {
                throw new UserException("PORFAVOR SOLO USAR NUMEROS POSITIVOS EN EDAD");
            }
            
            
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
             
             if (!uMan.confirmarContra(pass)) {
                throw new UserException("Porfavor incluir caracteres especiales, numeros, mayusculas y minusculas "
                            + "en la contraseña");
             }
             
            boolean coinciden = (uMan.getUserActual() == userSelect);
             
             
            ArrayList<Evento> mantenerList = userSelect.getEventosCreados();
            uMan.getUsuarios().remove(userSelect);
            uMan.insert(index, tipo, edad, nombreC, userN, pass);
            uMan.getUsuarios().get(index).setEventosCreados(mantenerList);
            JOptionPane.showMessageDialog(null, "Usuario editado con exito!");
            if(coinciden){
                JOptionPane.showMessageDialog(null, "Atencion!, al haber editado sus propios datos, debera ingresar de nuevo");
                uMan.cerrarSesion();
                this.dispose();
                new MenuPrincipal().setVisible(true);
            }else{
                salir();
            }
            System.out.println(uMan.listaUsers(uMan.cantUsers() - 1));
            System.out.println(uMan.getUsuarios().get(index).getEventosCreados());
          
        }catch(UserException e){
            System.out.println(e.getMessage());
        
        }
    }
    
   
    private void salir(){
        this.dispose();
        new AdminDeUsers().setVisible(true);
    }
 

    
}
