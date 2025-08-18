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
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

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
    private JTextField converts = new JTextField();

    private JLabel cod = new JLabel("Codigo: N/A");
    private JLabel nombre = new JLabel("Evento: N/A");
    private JLabel tipe = new JLabel("Categoria: N/A");
    
    JPanel panelIzq = new JPanel();

    private PanelEditarEvent tablaEvento = new PanelEditarEvent();

    public EditarEvent() {
        System.out.println(uMan.getEventosTotales());
        initComps();
    }

    private void initComps() {
        setSize(950, 630);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 60, 90));

        // ====== TITULO ======
        JLabel titulo = new JLabel("Editar Evento", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI Black", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        // ====== PANEL IZQUIERDO ======
        panelIzq = new JPanel();
        panelIzq.setLayout(new BoxLayout(panelIzq, BoxLayout.Y_AXIS));
        panelIzq.setBackground(new Color(30, 60, 90));
        panelIzq.setBorder(new EmptyBorder(20, 20, 20, 20));

        Font fuenteLbl = new Font("Segoe UI", Font.BOLD, 14);
        Color lblColor = Color.WHITE;

        panelIzq.add(crearLabel("Buscar Evento", fuenteLbl, lblColor));
        panelIzq.add(codigo);
        panelIzq.add(Box.createVerticalStrut(10));

        buscarE = new JButton("Buscar");
        estilizarBoton(buscarE, new Color(102, 153, 255));
        buscarE.addActionListener(e -> {
            try {
                buscarEv();
            } catch (EventException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
        panelIzq.add(buscarE);
        panelIzq.add(Box.createVerticalStrut(20));

        panelIzq.add(crearLabel("Nombre de Evento", fuenteLbl, lblColor));
        panelIzq.add(nombreEvent);
        panelIzq.add(Box.createVerticalStrut(10));

        panelIzq.add(crearLabel("Descripción", fuenteLbl, lblColor));
        panelIzq.add(descripcion);
        panelIzq.add(Box.createVerticalStrut(10));

        panelIzq.add(crearLabel("Monto de Renta", fuenteLbl, lblColor));
        panelIzq.add(renta);
        panelIzq.add(Box.createVerticalStrut(10));

        panelIzq.add(crearLabel("Cantidad de Gente", fuenteLbl, lblColor));
        panelIzq.add(cantGente);
        panelIzq.add(Box.createVerticalStrut(10));
        
        panelIzq.add(crearLabel("Cantidad de Convertidos", fuenteLbl, lblColor));
        panelIzq.add(converts);
        panelIzq.add(Box.createVerticalStrut(10));

        panelIzq.add(crearLabel("Fecha", fuenteLbl, lblColor));
        fecha.setDateFormatString("dd/MM/yyyy");
        panelIzq.add(fecha);
        panelIzq.add(Box.createVerticalStrut(20));

        crearE = new JButton("Editar");
        estilizarBoton(crearE, new Color(0, 200, 83));
        crearE.addActionListener(e -> {
            try {
                edicion();
            } catch (EventException ev) {
                JOptionPane.showMessageDialog(this, ev.getMessage());
            }
        });
        panelIzq.add(crearE);
        panelIzq.add(Box.createVerticalStrut(10));

        regresar = new JButton("Salir");
        estilizarBoton(regresar, new Color(244, 67, 54));
        regresar.addActionListener(e -> salir());
        panelIzq.add(regresar);

        // ====== PANEL DERECHO ======
        JPanel panelDer = new JPanel(new BorderLayout());
        panelDer.setBackground(Color.WHITE);
        panelDer.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelDer.setPreferredSize(new Dimension(500, 0)); //

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        cod.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tipe.setFont(new Font("Segoe UI", Font.BOLD, 14));

        infoPanel.add(cod);
        infoPanel.add(nombre);
        infoPanel.add(tipe);
        infoPanel.add(Box.createVerticalStrut(15));

        panelDer.add(infoPanel, BorderLayout.NORTH);

        // Tabla personalizada
        tablaEvento.setPreferredSize(new Dimension(400, 250)); // más pequeña
        panelDer.add(new JScrollPane(tablaEvento), BorderLayout.CENTER);

        // ====== ARMADO ======
        add(panelIzq, BorderLayout.WEST);
        add(panelDer, BorderLayout.CENTER);
    }

    private JLabel crearLabel(String txt, Font f, Color c) {
        JLabel l = new JLabel(txt);
        l.setFont(f);
        l.setForeground(c);
        return l;
    }

    private void estilizarBoton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(120, 35));
    }

    private void edicion() throws EventException {
        int people;
        int convers;
        double montR;
        String nomE = nombreEvent.getText().trim();
        String desc = descripcion.getText().trim();
 
        Date fechaReal = fecha.getDate();

        if (!(codigo.getText().matches("\\d+") && renta.getText().matches("\\d+(\\.\\d+)?") && cantGente.getText().matches("\\d+"))) {
            throw new EventException("SOLO PUEDE USAR NÚMEROS POSITIVOS EN CÓDIGO, RENTA Y CANTIDAD DE GENTE");
        }
       
        montR = Double.parseDouble(renta.getText().trim());
        people = Integer.parseInt(cantGente.getText().trim());
        convers = 0;
        
       
        if (people <= 0) {
            throw new EventException("La cantidad de gente debe ser mayor a 0.");
        }
        if (montR <= 0) {
            throw new EventException("El monto de renta debe ser mayor a 0.");
        }
        
        if (eSelected instanceof EventoDeportivo && people > 20000) {
            throw new EventException("Debido al cuidado de la grama, la cantidad de personas no debe de ser mas de 20000");
        }
        if (eSelected instanceof EventoMusical && people > 25000) {
            throw new EventException("Debido al cuidado de la grama, la cantidad de personas no debe de ser mas de 25000");
        }
        if (eSelected instanceof EventoReligioso && people > 30000) {
            throw new EventException("Debido al cuidado de la grama, la cantidad de personas no debe de ser mas de 30000");
        }

        if(eSelected instanceof EventoReligioso){
             convers = Integer.parseInt(converts.getText().trim());
    
            if (convers < 0) {
                throw new EventException("La cantidad de gente convertida no puede ser menor a 0.");
            }
        }
    
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (sdf.format(fechaHoy.getTime()).equals(sdf.format(fechaReal.getTime()))) {
            throw new EventException("No puede dejar que la fehca sea  el mismo dia");
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaReal);

        boolean fechaDisponible = uMan.getEventosTotales().stream().anyMatch(evento -> {
            return (sdf.format(evento.getFecha().getTime()).equals(sdf.format(cal.getTime())) && !evento.isCancelado() && !evento.equals(eSelected));
        });

        if (fechaDisponible) {
            throw new EventException("ERROR, NO SE PUEDE TENER MAS DE UN EVENTO EN LA MISMA FECHA");
        }

        eSelected.setDescripcion(desc);
        eSelected.setNombre(nomE);
        if(fecha.getDate() == null){
             eSelected.setFecha(eSelected.getFecha());
        }else{
        eSelected.setFecha(cal);
        }
        eSelected.setCantGente(people);
        if(eSelected instanceof EventoReligioso){
            EventoReligioso eRel = (EventoReligioso) eSelected;
            eRel.setConvertidos(convers);
            eSelected.setPrice(montR + 2000);
        }else if(eSelected instanceof EventoMusical){
             eSelected.setPrice(montR + (montR*0.3));
        }else{
             eSelected.setPrice(montR);
        }
        JOptionPane.showMessageDialog(null, "Datos cambiados con exito");
        System.out.println(uMan.getEventosTotales());

        eventCal.refrescarEventos();
    }

  

    private void buscarEv() throws EventException {
        int code;

        if (codigo.getText().isEmpty()) {
            throw new EventException("No se puede buscar el evento si deja el codigo vacio.");
        }

        code = Integer.parseInt(codigo.getText().trim());

        eSelected = uMan.buscarEvento(code, 0);
        if (eSelected == null) {
            throw new EventException("Evento no encontrado");
        }
        if(eSelected.isCancelado()){
           throw new EventException("No puede editar un evento que ha sido cancelado, pues queda inhabilidado");
        }

        // Actualizar labels
        cod.setText("Código: " + eSelected.getCodigo());
        nombre.setText("Evento: " + eSelected.getNombre());
        tipe.setText("Categoría: " + eSelected.getTypeEvent());
        
      
        nombreEvent.setText(eSelected.getNombre());
        descripcion.setText(eSelected.getDescripcion());
        cantGente.setText(String.valueOf(eSelected.getCantGente()));
        renta.setText(String.valueOf(eSelected.getMontoRenta()));
        fecha.setCalendar(eSelected.getFecha());

        // Mostrar datos en la tabla
          tablaEvento.setVisible(true);
        tablaEvento.cargarEvento(eSelected);
        converts.setEnabled(false);
        // === Si es religioso, centrar todo ===
        if (eSelected instanceof EventoReligioso) {
            converts.setEnabled(true);
            tablaEvento.setVisible(false);
        } 
        
        revalidate();
        repaint();
    }

    private void salir() {
        this.dispose();
        new AdminEventos().setVisible(true);
    }
    

}
