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
    
    private JTextField nombreEvent = new JTextField();
    private JTextField descripcion = new JTextField(); 
    private JTextField renta = new JTextField();
    private JTextField codigo = new JTextField();
    private JTextField cantGente = new JTextField();
    private JTextField teamA = new JTextField();
    private JTextField teamB = new JTextField();
    
    private String[] opc = {"deportivo", "musical", "religioso"};
    
    private JComboBox<String> type = new JComboBox<>(opc);
    private JComboBox<Object> bonus = new JComboBox<>();
    
    public CrearEvent(){
        initComps();
    }
    
    private void initComps(){
       
        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xaec3fc));
        
        setBackground(Color.red);
        
        
        
        JLabel l = new JLabel("Crear Evento");
        l.setSize(100, 100);
        l.setFont(new Font("Arial Black", Font.PLAIN, 35));
        l.setBounds(275, 20, 500, 50);
        add(l);
        
        crearE = new JButton("Crear");
        crearE.setForeground(Color.green);
        crearE.setFont(new Font("Arial Black", Font.PLAIN, 12));
        crearE.setBounds(275, 430, 100, 30);
        
        crearE.addActionListener(e ->{
            try{
             creacion();
            }catch(EventException ev){
                JOptionPane.showMessageDialog(null, ev.getMessage());
            }
            
        });
        
        add(crearE);
        
        regresar = new JButton("SALIR");
        regresar.setForeground(Color.red);
        regresar.setFont(new Font("Arial Black", Font.PLAIN, 12));
        regresar.setBounds(450, 430, 100, 30);
        
        regresar.addActionListener(e ->{
            salir();
        });
        
        add(regresar);
        
          
        JLabel nomTitle = new JLabel();
        nomTitle.setText("Nombre De Evento");
        nomTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        nomTitle.setBounds(300 + 125, 175 - 50, 200, 30);
        
        nombreEvent.setBounds(300 + 125, 200 - 50, 200, 20);
        
        add(nomTitle);
        add(nombreEvent);
        
        
        JLabel uTitle = new JLabel();
        uTitle.setText("Descripcion");
        uTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        uTitle.setBounds(300 - 125, 175, 200, 30);
        
        descripcion.setBounds(300 - 125, 200, 200, 20);
        
        add(uTitle);
        add(descripcion);
        
        JLabel dato = new JLabel();
        dato.setText("DATOS PARA EVENTO DEPORTIVO");
        dato.setFont(new Font("Arial Black", Font.PLAIN, 20));
        dato.setBounds(200, 170 + 110, 400, 50);
        
        add(dato);
        
        JLabel tAtitle = new JLabel();
        tAtitle.setText("Nombre De Equipo A");
        tAtitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        tAtitle.setBounds(300 + 125, 175 + 150, 200, 30);
        
        teamA.setBounds(300 + 125, 200 + 150, 200, 20);
        
        add(tAtitle);
        add(teamA);
        
        
        JLabel tBtitle = new JLabel();
        tBtitle.setText("Nombre De Equipo B");
        tBtitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        tBtitle.setBounds(300 - 125, 175 + 150, 200, 30);
        
        teamB.setBounds(300 - 125, 200 + 150, 200, 20);
        
        add(tBtitle);
        add(teamB);
        
        
        JLabel genteTitle = new JLabel();
        genteTitle.setText("Cantidad de Gende");
        genteTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        genteTitle.setBounds(300 + 125, 175, 200, 30);
        
        cantGente.setBounds(300 + 125, 200, 200, 20);
        
        add(genteTitle);
        add(cantGente);
        
        JLabel renTitle = new JLabel();
        renTitle.setText("Monto De Renta");
        renTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        renTitle.setBounds(300 + 125, 175 + 50, 200, 30);
        
        renta.setBounds(300 + 125, 200 + 50, 200, 20);
        
        add(renTitle);
        add(renta);
        
        JLabel cTitle = new JLabel();
        cTitle.setText("Codigo");
        cTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        cTitle.setBounds(300 - 125, 175 - 50, 200, 30);
        
        codigo.setBounds(300 - 125, 200 - 50, 200, 20);
        
        add(cTitle);
        add(codigo);
        
        JLabel fTitle = new JLabel();
        fTitle.setText("Fecha");
        fTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        fTitle.setBounds(300 - 125, 175 + 50, 200, 30);
        
        fecha.setBounds(300 - 125, 200 + 50, 200, 20);
        fecha.setDateFormatString("dd/MM/yyyy");
        
        add(fTitle);
        add(fecha);
        
        JLabel typeTitle = new JLabel();
        typeTitle.setText("Tipo de Evento");
        typeTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        typeTitle.setBounds(300 - 125, 175 - 100, 200, 30);
        
        type.setBounds(300 - 125, 200 - 100, 200, 20);
        
        add(typeTitle);
        add(type);
        
        JLabel bonTitle = new JLabel();
        bonTitle.setText("Opcion De Evento");
        bonTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        bonTitle.setBounds(300 + 125, 175 - 100, 200, 30);
        
        bonus.setBounds(300 + 125, 200 - 100, 200, 20);
        
        add(bonTitle);
        add(bonus);
        
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
        
        switch (tipoE) {
            case "deportivo":
                opcionalD = (Enumeraciones.Deporte) bonus.getSelectedItem();
                if (people > 20000) throw new EventException("La cantidad de gente debe ser mayor a 0.");
                if(opcionalD == null) throw new EventException("Debe seleccionar el tipo de Deporte");
                break;
            case "musical":
                opcionalM = (Enumeraciones.Musica) bonus.getSelectedItem();
                if (people > 25000) throw new EventException("La cantidad de gente debe ser mayor a 0.");
                if(opcionalM == null) throw new EventException("Debido a la grama solo se permite un maximo de 25,000 personas\npara un evento religioso.");
                break;
            case "religioso":
               if (people > 30000) throw new EventException("Debido a la grama solo se permite un maximo de 30,000 personas\npara un evento religioso.");
               break;
        }

        switch (tipoE) {
            case "deportivo":
                Evento dep = new EventoDeportivo(code, nomE, desc, cal, montR, people, opcionalD, eqA, eqB);
                persona.getEventosCreados().add(dep);
                uMan.getEventosTotales().add(dep);
                System.out.println(uMan.listaEventosTotales(0));
                break;
            case "musical":
                Evento mus = new EventoMusical(code, nomE, desc, cal, montR, people, opcionalM);
                persona.getEventosCreados().add(mus);
                 uMan.getEventosTotales().add(mus);
                System.out.println(uMan.listaEventosTotales(0));
                break;
            case "religioso":
                Evento rel = new EventoReligioso(code, nomE, desc, cal, montR, people);
                persona.getEventosCreados().add(rel);
                uMan.getEventosTotales().add(rel);
                System.out.println(uMan.listaEventosTotales(0));
                break;
        }
        eventCal.refrescarEventos();
    }
    private void actualizarCombo(JComboBox<Object> combo, String tipo){
        combo.removeAllItems();
             switch(tipo){
            case "deportivo":
                for (Enumeraciones.Deporte d: Enumeraciones.Deporte.values()) {
                    combo.addItem(d);
                }
                break;  
            case "musical":
                for (Enumeraciones.Musica d: Enumeraciones.Musica.values()) {
                    combo.addItem(d);
                }
                break;
            case "religioso":
                combo.setEnabled(false);
                break;
            }
    }
    
    private void salir(){
        this.dispose();
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }
 
    
}
