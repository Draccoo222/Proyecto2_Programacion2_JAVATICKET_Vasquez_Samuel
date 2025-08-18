/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.border.Border;
/**
 *
 * @author unwir
 */
public class CrearEvent extends JFrame {
    private UserManage uMan = UserManage.getInstance();
    Usuario persona = uMan.getUserActual();
    
    private CalendarEventos eventCal = new CalendarEventos(uMan);
    private JDateChooser fecha = eventCal.crearDateChooserConEventos();
   
    private Calendar fechaHoy = Calendar.getInstance();
    
    private JButton crearE;
    private JButton regresar;
    
    private JTextField nombreEvent = new JTextField(20);
    private JTextField descripcion = new JTextField(20); 
    private JTextField renta = new JTextField(20);
    private JTextField codigo = new JTextField(20);
    private JTextField cantGente = new JTextField(20);
    private JTextField teamA = new JTextField(20);
    private JTextField teamB = new JTextField(20);
    
    private String[] opc = {"deportivo", "musical", "religioso"};
    
    private JComboBox<String> type = new JComboBox<>(opc);
    private JComboBox<Object> bonus = new JComboBox<>();
    
    public CrearEvent(){
        initComps();
    }
    
    private void initComps(){
       
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0xA2463));
        
        JLabel l = new JLabel("CREAR EVENTO", SwingConstants.CENTER);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 50));
        add(l, BorderLayout.NORTH);
        
        // Panel para los campos de entrada
        JPanel panelCreacion = new JPanel();
        panelCreacion.setLayout(new GridBagLayout());
        panelCreacion.setBackground(new Color(0xA2463));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Tipo de Evento
        JLabel typeTitle = new JLabel("Tipo de Evento");
        typeTitle.setFont(new Font("Serif", Font.BOLD, 18));
        typeTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCreacion.add(typeTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCreacion.add(type, gbc);
        
        // Opcion de Evento (Deportivo/Musical)
        JLabel bonTitle = new JLabel("Opcion De Evento");
        bonTitle.setFont(new Font("Serif", Font.BOLD, 18));
        bonTitle.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panelCreacion.add(bonTitle, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panelCreacion.add(bonus, gbc);

        // Nombre de Evento
        JLabel nomTitle = new JLabel("Nombre De Evento");
        nomTitle.setFont(new Font("Serif", Font.BOLD, 18));
        nomTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCreacion.add(nomTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCreacion.add(nombreEvent, gbc);
        
        // Código
        JLabel cTitle = new JLabel("Codigo");
        cTitle.setFont(new Font("Serif", Font.BOLD, 18));
        cTitle.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panelCreacion.add(cTitle, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        panelCreacion.add(codigo, gbc);
        
        // Descripción
        JLabel uTitle = new JLabel("Descripcion");
        uTitle.setFont(new Font("Serif", Font.BOLD, 18));
        uTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCreacion.add(uTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelCreacion.add(descripcion, gbc);
        
        // Cantidad de Gente
        JLabel genteTitle = new JLabel("Cantidad de Gente");
        genteTitle.setFont(new Font("Serif", Font.BOLD, 18));
        genteTitle.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 2;
        panelCreacion.add(genteTitle, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        panelCreacion.add(cantGente, gbc);
        
        // Fecha
        JLabel fTitle = new JLabel("Fecha");
        fTitle.setFont(new Font("Serif", Font.BOLD, 18));
        fTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCreacion.add(fTitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        fecha.setDateFormatString("dd/MM/yyyy");
        panelCreacion.add(fecha, gbc);
        
        // Monto de Renta
        JLabel renTitle = new JLabel("Monto De Renta");
        renTitle.setFont(new Font("Serif", Font.BOLD, 18));
        renTitle.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panelCreacion.add(renTitle, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        panelCreacion.add(renta, gbc);
        
        // Datos para eventos deportivos
        JLabel tAtitle = new JLabel("Nombre De Equipo A");
        tAtitle.setFont(new Font("Serif", Font.BOLD, 18));
        tAtitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelCreacion.add(tAtitle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelCreacion.add(teamA, gbc);
        
        JLabel tBtitle = new JLabel("Nombre De Equipo B");
        tBtitle.setFont(new Font("Serif", Font.BOLD, 18));
        tBtitle.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 4;
        panelCreacion.add(tBtitle, gbc);
        gbc.gridx = 3;
        gbc.gridy = 4;
        panelCreacion.add(teamB, gbc);
        
        add(panelCreacion, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        panelBotones.setBackground(new Color(0xA2463));
        
        Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);

        crearE = new JButton("Crear");
        crearE.setBackground(new Color(0xEBC926));
        crearE.setFont(new Font("Serif", Font.BOLD, 18));
        crearE.setBorder(botBor);
        panelBotones.add(crearE);
        
        regresar = new JButton("REGRESAR");
        regresar.setBackground(new Color(0xEBC926));
        regresar.setFont(new Font("Serif", Font.BOLD, 18));
        regresar.setBorder(botBor);
        panelBotones.add(regresar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        teamA.setEnabled(false);
        teamB.setEnabled(false);
        bonus.setEnabled(false);
        
        type.addActionListener(e ->{    
            actualizarCombo(bonus, (String) type.getSelectedItem());
            bonus.setEnabled(true);
            
            if(type.getSelectedIndex() == 2){
               bonus.setEnabled(false);
            } 
            if(type.getSelectedIndex() != 0){
               teamA.setEnabled(false);
               teamB.setEnabled(false);
            }else{
               teamA.setEnabled(true);
               teamB.setEnabled(true);
            }
        });
        
        crearE.addActionListener(e ->{
            try{
             creacion();
            }catch(EventException ev){
                JOptionPane.showMessageDialog(null, ev.getMessage());
            }
            
        });
        
        regresar.addActionListener(e ->{
            salir();
        });
    }
    
    private void creacion() throws EventException{
        int code;
        int people;
        double montR;
        String nomE = nombreEvent.getText().trim();
        String desc = descripcion.getText().trim();
        String eqA = teamA.getText().trim();
        String eqB = teamB.getText().trim();
        String tipoE = (String) type.getSelectedItem();
        Date fechaReal = fecha.getDate();
        Enumeraciones.Deporte opcionalD = null;
        Enumeraciones.Musica opcionalM = null;
        
        if (nombreEvent.getText().isEmpty() || descripcion.getText().isEmpty() || fecha.getDate() == null || type.getSelectedItem() == null
                || (tipoE.equals("deportivo") && teamA.getText().isEmpty() && teamB.getText().isEmpty()))  {
            throw new EventException("NO PUEDE DEJAR NINGUN CAMPO VACIO");
        }
        
        if (!(codigo.getText().matches("\\d+") && renta.getText().matches("\\d+(\\.\\d+)?") && cantGente.getText().matches("\\d+"))) {
            throw new EventException("SOLO PUEDE USAR NÚMEROS POSITIVOS EN CÓDIGO, RENTA Y CANTIDAD DE GENTE");
        }
   
     
    
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaReal);

        code = Integer.parseInt(codigo.getText().trim());
        montR = Double.parseDouble(renta.getText().trim());
        people = Integer.parseInt(cantGente.getText().trim());
        
        if (code <= 0) throw new EventException("El código debe ser mayor a 0.");
        if (people <= 0) throw new EventException("La cantidad de gente debe ser mayor a 0.");
        if (montR <= 0) throw new EventException("El monto de renta debe ser mayor a 0.");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        if (uMan.buscarEvento(code, 0) != null) {
            throw new EventException("ERROR, ESTE CODIGO YA HA SIDO USADO");
        }

        if (sdf.format(fechaHoy.getTime()).equals(sdf.format(fechaReal.getTime()))) {
            throw new EventException("No puede crear un evento el mismo dia");
        }
        
        boolean fechaDisponible = uMan.getEventosTotales().stream().anyMatch(evento ->{
            return (sdf.format(evento.getFecha().getTime()).equals(sdf.format(cal.getTime())) && !evento.isCancelado());
        });
                
        if(fechaDisponible){
            throw new EventException("ERROR, NO SE PUEDE TENER MAS DE UN EVENTO EN LA MISMA FECHA");
        }
        
        
        switch (tipoE) {
            case "deportivo" -> {
                opcionalD = (Enumeraciones.Deporte) bonus.getSelectedItem();
                if (people > 20000) throw new EventException("La cantidad de gente debe ser mayor a 0.");
                if(opcionalD == null) throw new EventException("Debe seleccionar el tipo de Deporte");
            }
            case "musical" -> {
                opcionalM = (Enumeraciones.Musica) bonus.getSelectedItem();
                if (people > 25000) throw new EventException("La cantidad de gente debe ser mayor a 0.");
                if(opcionalM == null) throw new EventException("Debido a la grama solo se permite un maximo de 25,000 personas\npara un evento religioso.");
            }
            case "religioso" -> {
                if (people > 30000) throw new EventException("Debido a la grama solo se permite un maximo de 30,000 personas\npara un evento religioso.");
            }
        }

        switch (tipoE) {
            case "deportivo" -> {
                Evento dep = new EventoDeportivo(code, nomE, desc, cal, montR, people, opcionalD, eqA, eqB);
                persona.getEventosCreados().add(dep);
                uMan.getEventosTotales().add(dep);
                System.out.println(uMan.listaEventosTotales(0));
            }
            case "musical" -> {
                Evento mus = new EventoMusical(code, nomE, desc, cal, montR, people, opcionalM);
                persona.getEventosCreados().add(mus);
                uMan.getEventosTotales().add(mus);
                System.out.println(uMan.listaEventosTotales(0));
            }
            case "religioso" -> {
                Evento rel = new EventoReligioso(code, nomE, desc, cal, montR, people);
                persona.getEventosCreados().add(rel);
                uMan.getEventosTotales().add(rel);
                System.out.println(uMan.listaEventosTotales(0));
            }
        }
        
        JOptionPane.showMessageDialog(null, "Evento creado con exito!");
        salir();
        eventCal.refrescarEventos();
    }
    private void actualizarCombo(JComboBox<Object> combo, String tipo){
        combo.removeAllItems();
             switch(tipo){
            case "deportivo" -> {
                for (Enumeraciones.Deporte d: Enumeraciones.Deporte.values()) {
                    combo.addItem(d);
                }
            }
            case "musical" -> {
                for (Enumeraciones.Musica d: Enumeraciones.Musica.values()) {
                    combo.addItem(d);
                }
            }
            case "religioso" -> combo.setEnabled(false);
            }
    }
    
    private void salir(){
        this.dispose();
        new AdminEventos().setVisible(true);
    }
 
    
}
