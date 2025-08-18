/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
/**
 *
 * @author unwir
 */
public class CreateUser extends JFrame {
    private UserManage uMan;
    private JButton login;
    private JButton regresar;
    
    private JTextField nombreComp = new JTextField(22);
    private JTextField uName = new JTextField(22);
    
    private JTextField eD = new JTextField(22);
    private JTextField passW = new JTextField(22);
    
    private String[] opc = {"admin", "limitados", "contenidos"};
    
    private JComboBox<String> type = new JComboBox<>(opc);
    
    public CreateUser(){
    
        initComps();
    }
    
    
 private void initComps(){
        uMan = UserManage.getInstance();
        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xA2463));
        
        JLabel l = new JLabel("CREAR USUARIO");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 50));
        l.setBounds(190, 30, 500, 50);
        add(l);
        
        // Panel para los campos de entrada
        JPanel panelCreacion = new JPanel();
        panelCreacion.setLayout(new GridBagLayout());
        panelCreacion.setBackground(new Color(0xA2463));
        panelCreacion.setBounds(150, 100, 500, 300);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre Completo
        JLabel nomTitle = new JLabel("Nombre Completo");
        nomTitle.setFont(new Font("Serif", Font.BOLD, 18));
        nomTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCreacion.add(nomTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCreacion.add(nombreComp, gbc);
        
        // Nombre de Usuario
        JLabel uTitle = new JLabel("Nombre de Usuario");
        uTitle.setFont(new Font("Serif", Font.BOLD, 18));
        uTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCreacion.add(uTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCreacion.add(uName, gbc);
        
        // Edad
        JLabel eTitle = new JLabel("Edad");
        eTitle.setFont(new Font("Serif", Font.BOLD, 18));
        eTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCreacion.add(eTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelCreacion.add(eD, gbc);
        
        // Contraseña
        JLabel passWTitle = new JLabel("Contraseña");
        passWTitle.setFont(new Font("Serif", Font.BOLD, 18));
        passWTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCreacion.add(passWTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelCreacion.add(passW, gbc);
        
        // Tipo de Usuario
        JLabel typeTitle = new JLabel("Tipo de Usuario");
        typeTitle.setFont(new Font("Serif", Font.BOLD, 18));
        typeTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelCreacion.add(typeTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelCreacion.add(type, gbc);
        
        add(panelCreacion);
        
        // Botones
        Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);

        JButton creacionBtn = new JButton("CREAR");
        creacionBtn.setBackground(new Color(0xEBC926));
        creacionBtn.setFont(new Font("Serif", Font.BOLD, 18));
        creacionBtn.setBorder(botBor);
        creacionBtn.setBounds(200, 420, 180, 35);
        add(creacionBtn);
        
        JButton regresarBtn = new JButton("REGRESAR");
        regresarBtn.setBackground(new Color(0xEBC926));
        regresarBtn.setFont(new Font("Serif", Font.BOLD, 18));
        regresarBtn.setBorder(botBor);
        regresarBtn.setBounds(400, 420, 180, 35);
        add(regresarBtn);

        creacionBtn.addActionListener(e -> {
            try {   
                creacion();
            } catch(UserException em) {
               JOptionPane.showMessageDialog(null, em.getMessage()); 
            } 
        });
        
        regresarBtn.addActionListener(e -> {
            salir();
        });
    }
    
    private void creacion() throws UserException { 
        int edad;
        String tipo = (String) type.getSelectedItem();
        String nombreC = nombreComp.getText().trim();
        String pass = passW.getText().trim();
        String userN = uName.getText().trim();

        if (!eD.getText().matches("\\d+")) {
            throw new UserException("PORFAVOR SOLO USAR NUMEROS POSITIVOS EN EDAD");
        }
        if (nombreComp.getText().isEmpty() || passW.getText().isEmpty() || uName.getText().isEmpty()
                || type.getSelectedItem() == null) {
            throw new UserException("PORFAVOR LLENE TODOS LOS CAMPOS SOLICITADOS");
        }
        
        if (nombreComp.getText().isBlank() || passW.getText().isBlank() || uName.getText().isBlank()) {
            throw new UserException("PORFAVOR LLENE TODOS LOS CAMPOS SOLICITADOS");
        }
   
        if (!uMan.confirmarContra(pass)) {
            throw new UserException("Porfavor incluir caracteres especiales, numeros, mayusculas y minusculas "
                    + "en la contraseña");
        }
        
        if (!nombreC.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new UserException("El nombre solo puede contener letras y espacios.");
        }
        if (!userN.matches("[a-zA-Z0-9_]+")) {
            throw new UserException("El usuario solo puede contener letras, números y guion bajo.");
        }
        
        if(uMan.buscarUsuario(userN) != null){
            throw new UserException("ERROR, ESTE NOMBRE DE USUARIO YA ESTA EN USO");
        }

        edad = Integer.parseInt(eD.getText());

        if(edad < 5 || edad > 120) throw new UserException("Porfavor introduzca una edad valida dentro del rango de 5 a 120");
      
        uMan.crearUser(tipo, edad, nombreC, userN, pass);
        JOptionPane.showMessageDialog(null, "Usuario creado con exito!");
        salir();
        System.out.println(uMan.listaUsers(uMan.cantUsers() - 1));
        
    }
    
    private void salir(){
        this.dispose();
        new AdminDeUsers().setVisible(true);
    }

}
