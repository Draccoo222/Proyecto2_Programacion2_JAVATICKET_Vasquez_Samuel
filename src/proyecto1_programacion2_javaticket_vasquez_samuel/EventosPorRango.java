/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author unwir
 */
public class EventosPorRango extends JFrame {

    private final UserManage userManager;
    private final JTable tablaEventos;
    private final DefaultTableModel modeloEventos;
    private final JLabel lblEstadisticas;
    private final JDateChooser dateInicio;
    private final JDateChooser dateFin;

    public EventosPorRango() {
        userManager = UserManage.getInstance();

        setTitle("Consulta de Eventos por Rango de Fechas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // ==== ESTILO GENERAL DEL FRAME ====
        getContentPane().setBackground(new Color(30, 60, 90));
        setLayout(new BorderLayout(10, 10));

        // ==== PANEL SUPERIOR CON DATECHOOSER ====
        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.setBackground(new Color(30, 60, 90));

        JLabel lblInicio = new JLabel("Fecha inicio:");
        lblInicio.setForeground(Color.WHITE);
        dateInicio = new JDateChooser();
        dateInicio.setDateFormatString("dd/MM/yyyy");

        JLabel lblFin = new JLabel("Fecha fin:");
        lblFin.setForeground(Color.WHITE);
        dateFin = new JDateChooser();
        dateFin.setDateFormatString("dd/MM/yyyy");

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBackground(new Color(70, 130, 180));
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setFocusPainted(false);

        btnFiltrar.addActionListener(e -> cargarEventos());

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(70, 130, 180));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(e -> {
            this.dispose();
            new Reportes().setVisible(true);
        });

        panelSuperior.add(lblInicio);
        panelSuperior.add(dateInicio);
        panelSuperior.add(lblFin);
        panelSuperior.add(dateFin);
        panelSuperior.add(btnFiltrar);
        panelSuperior.add(btnSalir);

        add(panelSuperior, BorderLayout.NORTH);

        // ==== TABLA DE EVENTOS ====
        String[] columnas = {"TIPO", "NOMBRE", "MONTO", "FECHA", "ESTADO"};
        modeloEventos = new DefaultTableModel(columnas, 0);
        tablaEventos = new JTable(modeloEventos);
        tablaEventos.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(tablaEventos);
        add(scroll, BorderLayout.CENTER);

        // ==== ETIQUETA DE ESTADÍSTICAS ====
        lblEstadisticas = new JLabel("Estadísticas: ");
        lblEstadisticas.setForeground(Color.WHITE);
        lblEstadisticas.setFont(new Font("Arial", Font.BOLD, 14));
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(30, 60, 90));
        panelInferior.add(lblEstadisticas);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void cargarEventos() {
        modeloEventos.setRowCount(0); // limpiar tabla

        Date inicio = dateInicio.getDate();
        Date fin = dateFin.getDate();
        if (inicio == null || fin == null) {
            JOptionPane.showMessageDialog(this, "Seleccione ambas fechas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // normalizar a 00:00 y 23:59
        Calendar cInicio = Calendar.getInstance();
        cInicio.setTime(inicio);
        cInicio.set(Calendar.HOUR_OF_DAY, 0);
        cInicio.set(Calendar.MINUTE, 0);
        cInicio.set(Calendar.SECOND, 0);
        cInicio.set(Calendar.MILLISECOND, 0);

        Calendar cFin = Calendar.getInstance();
        cFin.setTime(fin);
        cFin.set(Calendar.HOUR_OF_DAY, 23);
        cFin.set(Calendar.MINUTE, 59);
        cFin.set(Calendar.SECOND, 59);
        cFin.set(Calendar.MILLISECOND, 999);

        int deportivos = 0, musicales = 0, religiosos = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Evento evento : userManager.getEventosTotales()) {
            Date fechaEvento = evento.getFecha().getTime();
            if (!fechaEvento.before(cInicio.getTime()) && !fechaEvento.after(cFin.getTime())) {

                String tipo = evento.getClass().getSimpleName();
                String estado = evento.isCancelado() ? "Cancelado" : "Activo";
                String etiquetaMonto = evento.isCancelado() ? "Multa" : "Renta";

                modeloEventos.addRow(new Object[]{
                    tipo,
                    evento.getNombre(),
                    etiquetaMonto + ": " + evento.getMontoRenta(),
                    sdf.format(fechaEvento),
                    estado
                });

                if (evento instanceof EventoDeportivo) {
                    deportivos++;
                } else if (evento instanceof EventoMusical) {
                    musicales++;
                } else if (evento instanceof EventoReligioso) {
                    religiosos++;
                }
            }
        }

        lblEstadisticas.setText(String.format("Estadísticas: Deportivos=%d | Musicales=%d | Religiosos=%d",
                deportivos, musicales, religiosos));
    }

}
