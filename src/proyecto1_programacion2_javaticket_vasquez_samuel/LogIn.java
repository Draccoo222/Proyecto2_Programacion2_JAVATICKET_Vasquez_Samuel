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
public class LogIn extends JFrame {
    private UserManage uMan;
    private JButton login;
    private JButton regresar;
    private JTextField nombreComp = new JTextField();
    private JTextField uName = new JTextField();
    private JTextField passW = new JTextField();
    
    public LogIn(){
        initComps();
    }
      private void initComps() {
        uMan = UserManage.getInstance();
        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xA2463)); // Fondo igual a MenuPrincipal

        // Título "INICIAR SESION"
        JLabel l = new JLabel("INICIAR SESIÓN");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 70));
        l.setBounds(140, 50, 800, 100);
        add(l);
        
        // --- Panel para los campos de texto y botones para mejor organización ---
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new GridBagLayout());
        panelLogin.setBackground(new Color(0xA2463));
        panelLogin.setBounds(200, 180, 400, 200);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo de usuario
        JLabel nomTitle = new JLabel("Nombre de Usuario");
        nomTitle.setFont(new Font("Serif", Font.BOLD, 18));
        nomTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelLogin.add(nomTitle, gbc);
        
        uName = new JTextField(15);
        uName.setFont(new Font("Serif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelLogin.add(uName, gbc);
        
        // Campo de contraseña
        JLabel passWTitle = new JLabel("Contraseña");
        passWTitle.setFont(new Font("Serif", Font.BOLD, 18));
        passWTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelLogin.add(passWTitle, gbc);
        
        passW = new JTextField(15);
        passW.setFont(new Font("Serif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelLogin.add(passW, gbc);
        
        // Botones
        login = new JButton("LOG IN");
        login.setBackground(new Color(0xEBC926));
        login.setFont(new Font("Serif", Font.BOLD, 18));
        Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);
        login.setBorder(botBor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelLogin.add(login, gbc);
        
        regresar = new JButton("REGRESAR");
        regresar.setBackground(new Color(0xEBC926));
        regresar.setFont(new Font("Serif", Font.BOLD, 18));
        regresar.setBorder(botBor);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelLogin.add(regresar, gbc);

        add(panelLogin);

        // Agregando Listeners
        login.addActionListener(e -> {
                try{
                    logeo();
                }catch(UserException eu){
                    JOptionPane.showMessageDialog(null, eu.getMessage());     
                }
         } );
        regresar.addActionListener(e -> salir());
        
    }
    
    private void logeo() throws UserException{
        String pass = passW.getText().trim();
        String userN = uName.getText().trim();
        
        
        if(passW.getText().isEmpty() || uName.getText().isEmpty()){
            throw new UserException("No puede dejar ningun campo vacio");
        }
        
        if(!uMan.logIn(userN, pass)){
          throw new UserException("El usuario no exist o la contraseña esta mala");
          
        }
   
   
        uMan.logIn(userN, pass);
        JOptionPane.showMessageDialog(null, "Inicio de Sesion Exitoso!");
        salir();
         
    }
    
    private void salir(){
        this.dispose();
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }
 

    
}
