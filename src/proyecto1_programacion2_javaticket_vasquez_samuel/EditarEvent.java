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
public class EditarEvent extends JFrame {

    private UserManage uMan = UserManage.getInstance();
    Usuario persona = uMan.getUserActual();

    private CalendarEventos eventCal = new CalendarEventos(uMan);
    private JDateChooser fecha = eventCal.crearDateChooserConEventos();

    private Calendar fechaHoy = Calendar.getInstance();

    private Evento eSelected;

    private JButton crearE;
    private JButton buscarE;
    private JButton regresar;

    private JTextField nombreEvent = new JTextField();
    private JTextField descripcion = new JTextField();
    private JTextField renta = new JTextField();
    private JTextField cantGente = new JTextField();
    private JTextField teamA = new JTextField();
    private JTextField teamB = new JTextField();
    private JTextField codigo = new JTextField();

    private JLabel cod = new JLabel("Codigo: N/A");
    private JLabel nombre = new JLabel("Evento: N/A");
    private JLabel tipe = new JLabel("Categoria: N/A");
    
    private PanelEditarEvent tablaEvento = new PanelEditarEvent();

    public EditarEvent() {
        initComps();
    }

    private void initComps() {

        setSize(800, 620);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xaec3fc));

        setBackground(Color.red);

        JLabel l = new JLabel("Editar Evento");
        l.setSize(100, 100);
        l.setFont(new Font("Arial Black", Font.PLAIN, 35));
        l.setBounds(275, 20, 500, 50);
        add(l);

        crearE = new JButton("Crear");
        crearE.setForeground(Color.green);
        crearE.setFont(new Font("Arial Black", Font.PLAIN, 12));
        crearE.setBounds(275, 430, 100, 30);

        crearE.addActionListener(e -> {
            try {
                edicion();
            } catch (EventException ev) {
                JOptionPane.showMessageDialog(null, ev.getMessage());
            }

        });

        add(crearE);

        regresar = new JButton("SALIR");
        regresar.setForeground(Color.red);
        regresar.setFont(new Font("Arial Black", Font.PLAIN, 12));
        regresar.setBounds(450, 430, 100, 30);

        regresar.addActionListener(e -> {
            salir();
        });

        add(regresar);

        buscarE = new JButton("Buscar");
        buscarE.setForeground(Color.MAGENTA);
        buscarE.setFont(new Font("Arial", Font.BOLD, 12));
        buscarE.setBounds(320, 175 - 75, 100, 30);
        buscarE.setSize(80, 20);

        buscarE.addActionListener(e -> {
            try {
                buscarEv();
            } catch (EventException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        });

        cod.setFont(new Font("Arial Black", Font.BOLD, 15));
        cod.setBounds(430, 175 - 40 - 80, 400, 60);

        nombre.setFont(new Font("Arial Black", Font.BOLD, 15));
        nombre.setBounds(430, 175 - 20 - 80, 400, 60);

        tipe.setFont(new Font("Arial Black", Font.BOLD, 15));
        tipe.setBounds(430, 175 - 80, 400, 60);

        add(cod);
        add(nombre);
        add(tipe);

        add(buscarE);

        JLabel busEtitle = new JLabel();
        busEtitle.setText("Buscar Evento");
        busEtitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        busEtitle.setBounds(300 - 200, 175 - 100, 200, 30);

        codigo.setBounds(300 - 200, 200 - 100, 200, 20);

        add(busEtitle);
        add(codigo);

        JLabel nomTitle = new JLabel();
        nomTitle.setText("Nombre De Evento");
        nomTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        nomTitle.setBounds(300- 200, 175 - 50, 200, 30);

        nombreEvent.setBounds(300 - 200, 200 - 50, 200, 20);

        add(nomTitle);
        add(nombreEvent);

        JLabel uTitle = new JLabel();
        uTitle.setText("Descripcion");
        uTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        uTitle.setBounds(300 - 200, 175, 200, 30);

        descripcion.setBounds(300 - 200, 200, 200, 20);

        add(uTitle);
        add(descripcion);

        JLabel genteTitle = new JLabel();
        genteTitle.setText("Cantidad de Gende");
        genteTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        genteTitle.setBounds(300 - 200, 175 + 100, 200, 30);

        cantGente.setBounds(300 - 200, 200 + 100, 200, 20);

        add(genteTitle);
        add(cantGente);

        JLabel renTitle = new JLabel();
        renTitle.setText("Monto De Renta");
        renTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        renTitle.setBounds(300- 200, 175 + 50, 200, 30);

        renta.setBounds(300 - 200, 200 + 50, 200, 20);

        add(renTitle);
        add(renta);

        JLabel fTitle = new JLabel();
        fTitle.setText("Fecha");
        fTitle.setFont(new Font("Arial Black", Font.PLAIN, 14));
        fTitle.setBounds(300 - 200, 175 + 150, 200, 30);

        fecha.setBounds(300- 200, 200 + 150, 200, 20);
        fecha.setDateFormatString("dd/MM/yyyy");

        add(fTitle);
        add(fecha);



        teamA.setEnabled(false);
        teamB.setEnabled(false);
        
        add(tablaEvento);

    }

    private void edicion() throws EventException {
        int code;
        int people;
        double montR;
        String nomE = nombreEvent.getText().trim();
        String desc = descripcion.getText().trim();
        String eqA = teamA.getText().trim();
        String eqB = teamB.getText().trim();

        Date fechaReal = fecha.getDate();
        Enumeraciones.Deporte opcionalD = null;
        Enumeraciones.Musica opcionalM = null;

        if (codigo.getText().isEmpty() || nombreEvent.getText().isEmpty() || descripcion.getText().isEmpty() || fecha.getDate() == null) {
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

        if (code <= 0) {
            throw new EventException("El código debe ser mayor a 0.");
        }
        if (people <= 0) {
            throw new EventException("La cantidad de gente debe ser mayor a 0.");
        }
        if (montR <= 0) {
            throw new EventException("El monto de renta debe ser mayor a 0.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (uMan.buscarEvento(code, 0) != null) {
            throw new EventException("ERROR, ESTE CODIGO YA HA SIDO USADO");
        }

        if (sdf.format(fechaHoy.getTime()).equals(sdf.format(fechaReal.getTime()))) {
            throw new EventException("No puede crear un evento el mismo dia");
        }

        eventCal.refrescarEventos();
    }

    private void actualizarCombo(JComboBox<Object> combo, String tipo) {
        combo.removeAllItems();
        switch (tipo) {
            case "deportivo":
                for (Enumeraciones.Deporte d : Enumeraciones.Deporte.values()) {
                    combo.addItem(d);
                }
                break;
            case "musical":
                for (Enumeraciones.Musica d : Enumeraciones.Musica.values()) {
                    combo.addItem(d);
                }
                break;
            case "religioso":
                combo.setEnabled(false);
                break;
        }
    }

    private void buscarEv() throws EventException {
        int code;
        
        if (codigo.getText().isEmpty()) {
            throw new EventException("No se puede buscar el evento si deja el codigo vacio.");
        }

        code = Integer.parseInt(codigo.getText().trim());

        eSelected = uMan.buscarEvento(code, 0);
        
        cod.setText("Codigo: " + eSelected.getCodigo());
        nombre.setText("Nombre: " + eSelected.getTypeEvent().toUpperCase());
        if(!(eSelected instanceof EventoReligioso)){
            tipe.setText("Tipo: " + eSelected.getBonus().toUpperCase());
        }else{
            tipe.setText("Tipo: NULO");
        }
        
        nombreEvent.setText(eSelected.getNombre());
        descripcion.setText(eSelected.getDescripcion());
        cantGente.setText(String.valueOf(eSelected.getCantGente()));
        renta.setText(String.valueOf(eSelected.getMontoRenta()));
        fecha.setCalendar(eSelected.getFecha());
     
        
       
        tablaEvento.setComps(eSelected);
        tablaEvento.setBounds(350, 150, 350, 300);
          
    
    }

    private void salir() {
        this.dispose();
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }

    public static void main(String[] args) {
        UserManage uMen = UserManage.getInstance();
        uMen.getEventosTotales().add(new EventoMusical(1, "S", "Pinga", Calendar.getInstance(), 1000, 10, Enumeraciones.Musica.CLASICA));
        uMen.getEventosTotales().add(new EventoDeportivo(2, "Balon de Playa", "Pinga", Calendar.getInstance(), 1000, 10, Enumeraciones.Deporte.FUTBOL, "Farsa", "Vardrid"));
        
        
        new EditarEvent().setVisible(true);

    }

}
