/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author unwir
 */
public class EventosCancelados extends JFrame {

    UserManage uMan;

    public EventosCancelados() {
        initComp();
    }

    public void initComp() {
        uMan = UserManage.getInstance();

        setSize(780, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xA2463));

        int[] contadores = {0, 0, 0};
        double[] montos = {0, 0, 0};
        
        JLabel lal = new JLabel("Eventos Realizados");
        lal.setForeground(Color.WHITE);
        lal.setFont(new Font("Serif", Font.BOLD, 40));
        lal.setBounds(230, -50, 500, 200);    

        JButton salir = new JButton("Salir");
        salir.setBackground(new Color(0xEBC926));
        salir.setFont(new Font("Serif", Font.BOLD, 16));
        salir.setBounds(30, 430, 100, 30); 
        
        JPanel panelEventos = crearPanelEventosListado(uMan, contadores, montos);
        JPanel panelStats = crearPanelEventosStats(contadores, montos);

        panelEventos.setBounds(150, 80, 500, 200);
        panelStats.setBounds(150, 320, 500, 150);
        
        add(lal);
        add(panelEventos);
        add(panelStats);
        add(salir);
        
         
        salir.addActionListener(e->{
            this.dispose();
            new Reportes().setVisible(true);
        
        });
       
    }
   
    public JPanel crearPanelEventosListado(UserManage userManager, int[] contadores, double[] montos) {
        Color azulito = new Color(30, 60, 90);
        String[] columnasEventos = {"TIPO", "NOMBRE", "MONTO", "FECHA"};
        DefaultTableModel modeloEventos = new DefaultTableModel(columnasEventos, 0);
        
        Calendar hoyCal = Calendar.getInstance();
        hoyCal.set(Calendar.HOUR_OF_DAY, 0);
        hoyCal.set(Calendar.MINUTE, 0);
        hoyCal.set(Calendar.SECOND, 0);
        hoyCal.set(Calendar.MILLISECOND, 0);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        userManager.getEventosTotales().stream()
                .filter(evento -> evento.isCancelado())
                .sorted((e1, e2) -> e2.getFecha().compareTo(e1.getFecha()))
                .forEach(evento -> {
                    modeloEventos.addRow(new Object[]{
                        evento.getClass().getSimpleName(),
                        evento.getNombre(),
                        evento.getMontoRenta(),
                        sdf.format(evento.getFecha().getTime())
                    });
                    
                    if (evento instanceof EventoDeportivo) {
                        contadores[0]++;
                        montos[0] += evento.getMontoRenta();
                    } else if (evento instanceof EventoMusical) {
                        contadores[1]++;
                        montos[1] += evento.getMontoRenta();
                    } else if (evento instanceof EventoReligioso) {
                        contadores[2]++;
                        montos[2] += evento.getMontoRenta();
                    }
                
                });

        JTable tablaEventos = new JTable(modeloEventos);
        tablaEventos.setFillsViewportHeight(true);
        tablaEventos.setShowGrid(true);
        tablaEventos.setIntercellSpacing(new Dimension(0, 0));
        tablaEventos.setPreferredScrollableViewportSize(tablaEventos.getPreferredSize());

        JPanel tituloEventos = new JPanel(new BorderLayout());
        tituloEventos.setBackground(azulito);
        JLabel lblEventos = new JLabel("Listado de Eventos", SwingConstants.CENTER);
        lblEventos.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblEventos.setForeground(Color.WHITE);
        tituloEventos.add(lblEventos, BorderLayout.CENTER);

        JPanel panelScrollEventos = new JPanel(new BorderLayout());
        panelScrollEventos.setBackground(azulito);
        panelScrollEventos.setBorder(BorderFactory.createEmptyBorder());
        panelScrollEventos.add(tituloEventos, BorderLayout.NORTH);

        JScrollPane scrollEventos = new JScrollPane(tablaEventos);
        scrollEventos.setBorder(BorderFactory.createEmptyBorder());
        scrollEventos.setMaximumSize(tablaEventos.getPreferredSize());
        panelScrollEventos.add(scrollEventos, BorderLayout.CENTER);
        
        estilizarTabla(tablaEventos, new Color(30, 60, 90), Color.WHITE);
       

        return panelScrollEventos;
    }


    public JPanel crearPanelEventosStats(int[] contadores, double[] montos) {
        Color azulito = new Color(30, 60, 90);

        String[] columnasStats = {"Tipo", "Cantidad", "Total (Lps)"};
        Object[][] datosStats = {
            {"Deportivos",  contadores[0], String.format("%.2f",montos[0])},
            {"Musicales", contadores[1], String.format("%.2f",montos[1])},
            {"Religiosos", contadores[2], String.format("%.2f",montos[2])}
        };

        DefaultTableModel modeloStats = new DefaultTableModel(datosStats, columnasStats);
        JTable tablaStats = new JTable(modeloStats);
        tablaStats.setFillsViewportHeight(true);
        tablaStats.setShowGrid(true);
        tablaStats.setIntercellSpacing(new Dimension(0, 0));
        tablaStats.setPreferredScrollableViewportSize(tablaStats.getPreferredSize());

        JPanel tituloStats = new JPanel(new BorderLayout());
        tituloStats.setBackground(azulito);
        JLabel lblStats = new JLabel("Estadísticas", SwingConstants.CENTER);
        lblStats.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStats.setForeground(Color.WHITE);
        tituloStats.add(lblStats, BorderLayout.CENTER);

        JPanel panelScrollStats = new JPanel(new BorderLayout());
        panelScrollStats.setBackground(azulito);
        panelScrollStats.setBorder(BorderFactory.createEmptyBorder());
        panelScrollStats.add(tituloStats, BorderLayout.NORTH);

        JScrollPane scrollStats = new JScrollPane(tablaStats);
        scrollStats.setBorder(BorderFactory.createEmptyBorder());
        scrollStats.setMaximumSize(tablaStats.getPreferredSize());
        panelScrollStats.add(scrollStats, BorderLayout.CENTER);
        
         estilizarTabla(tablaStats, new Color(30, 60, 90), Color.WHITE);

        return panelScrollStats;
    }
    
    private void estilizarTabla(JTable tabla, Color headerBg, Color headerFg) {
        // Fuente general
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(28);

        // Encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(headerBg);
        header.setForeground(headerFg);
        header.setReorderingAllowed(false);
        header.setOpaque(true);

        // Selección
        tabla.setSelectionBackground(new Color(51, 153, 255));
        tabla.setSelectionForeground(Color.WHITE);

        // Bordes y líneas
        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(new Color(220, 220, 220));

        // Renderer para filas alternadas
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(245, 245, 245)); // gris clarito
                    }
                }
                return c;
            }
        });
    }

  

}
