/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author unwir
 */
public class VerPerfil extends JFrame {

    private UserManage uMan;
    private JButton login;
    private JButton regresar;

    private JTextField nombreComp = new JTextField(22);
    private JTextField uName = new JTextField(22);

    private JTextField eD = new JTextField(22);
    private JTextField passW = new JTextField(22);

    private JTextField tipo = new JTextField(22);

    public VerPerfil() {

        initComps();
    }

    private void initComps() {
        uMan = UserManage.getInstance();
        setSize(1100, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0xA2463));

        JLabel l = new JLabel("VER PERFIL");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Serif", Font.BOLD, 50));
        l.setBounds(190, 30, 500, 50);
        add(l);

        // Panel para los campos de entrada
        JPanel panelCreacion = new JPanel();
        panelCreacion.setLayout(new GridBagLayout());
        panelCreacion.setBackground(new Color(0xA2463));
        // en vez de centrarlo con 150 px de margen, lo ponemos desde la izquierda
        panelCreacion.setBounds(-25, 100, 500, 300);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // alineación a la izquierda
        gbc.anchor = GridBagConstraints.WEST;

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
        panelCreacion.add(tipo, gbc);

        add(panelCreacion);

        // Botones
        Border botBor = BorderFactory.createLineBorder(new Color(0xB89E2E), 4);

        JButton regresarBtn = new JButton("REGRESAR");
        regresarBtn.setBackground(new Color(0xEBC926));
        regresarBtn.setFont(new Font("Serif", Font.BOLD, 18));
        regresarBtn.setBorder(botBor);
        regresarBtn.setBounds(400, 420, 180, 35);
        add(regresarBtn);

        JPanel panelEventos = crearPanelEventosListado(uMan);
    
        panelEventos.setBounds(500, 100, 500, 200); 
      
        add(panelEventos);
       

        regresarBtn.addActionListener(e -> {
            salir();
        });

        
        
        nombreComp.setEditable(false);
        nombreComp.setEnabled(false);
        
        uName.setEditable(false);
        uName.setEnabled(false);
        
        eD.setEditable(false);
        eD.setEnabled(false);
      
        passW.setEditable(false);
        passW.setEnabled(false);
        
        tipo.setEditable(false);
        tipo.setEnabled(false);
        

        
        loadInfo();
    }
    
    public JPanel crearPanelEventosListado(UserManage userManager) {
   Color azulito = new Color(30, 60, 90);
    String[] columnasEventos = {"TIPO", "NOMBRE", "MONTO", "FECHA"};
    DefaultTableModel modeloEventos = new DefaultTableModel(columnasEventos, 0);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // 🔹 Ahora usamos SOLO los eventos creados por el usuario actual
    userManager.getUserActual().getEventosCreados().stream()
            .sorted((e1, e2) -> e2.getFecha().compareTo(e1.getFecha())) // ordenar de más nuevo a más viejo
            .forEach(evento -> {
                modeloEventos.addRow(new Object[]{
                        evento.getClass().getSimpleName(),
                        evento.getNombre(),
                        evento.getMontoRenta(),
                        sdf.format(evento.getFecha().getTime())
                });

                // 🔹 Contadores y montos por tipo
               
            });

    JTable tablaEventos = new JTable(modeloEventos);
    tablaEventos.setFillsViewportHeight(true);
    tablaEventos.setShowGrid(true);
    tablaEventos.setIntercellSpacing(new Dimension(0, 0));
    tablaEventos.setPreferredScrollableViewportSize(tablaEventos.getPreferredSize());

    JPanel tituloEventos = new JPanel(new BorderLayout());
    tituloEventos.setBackground(azulito);
    JLabel lblEventos = new JLabel("Eventos del Usuario Actual", SwingConstants.CENTER);
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

    private void loadInfo() {
        nombreComp.setText(uMan.getUserActual().getNombreCompleto());
        uName.setText(uMan.getUserActual().getUserName());
        eD.setText(String.valueOf(uMan.getUserActual().getEdad()));
        passW.setText(uMan.getUserActual().getPassW());
        tipo.setText(uMan.getUserActual().getTipoUser());
        
    }

    private void salir() {
        this.dispose();
        new Reportes().setVisible(true);
    }
}
