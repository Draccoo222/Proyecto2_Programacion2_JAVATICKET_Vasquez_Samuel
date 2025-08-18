package proyecto1_programacion2_javaticket_vasquez_samuel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.util.ArrayList;

public class PanelEditarEvent extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;

    private EventoDeportivo eventoDeportivo;
    private EventoMusical eventoMusical;

    private boolean esDeportivo;

    public PanelEditarEvent() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 245));
        initUI();
    }

    private void initUI() {
        // Modelo vacío inicial
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);

        // 🎨 Tabla estilizada
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tabla.getTableHeader().setBackground(new Color(45, 45, 45));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setGridColor(new Color(200, 200, 200));
        tabla.setSelectionBackground(new Color(0, 120, 215));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setShowGrid(true);

        // Scroll estilizado
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scroll.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scroll.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        // Botones estilizados
        JButton btnGuardar = crearBoton("Guardar Integrantes", new Color(40, 167, 69));
        btnGuardar.addActionListener(e -> guardarIntegrantes());

        JButton btnAgregarFila = crearBoton("Agregar Fila", new Color(0, 123, 255));
        btnAgregarFila.addActionListener(e -> {
            if (!esDeportivo) { // solo en musical
               
                modelo.addRow(new Object[]{""});
            }
        });
     
        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        abajo.setBackground(new Color(245, 245, 250));
        abajo.add(btnAgregarFila);
        abajo.add(btnGuardar);

        add(scroll, BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);
    }

    // 🔹 Método único para cargar evento (deportivo o musical)
    public void cargarEvento(Object ev) {
        modelo.setRowCount(0); // limpiar filas

        if (ev instanceof EventoDeportivo) {
            this.eventoDeportivo = (EventoDeportivo) ev;
            this.eventoMusical = null;
            this.esDeportivo = true;

            modelo.setColumnIdentifiers(new Object[]{
                "Equipo " + eventoDeportivo.getEquipoA(),
                "Equipo " + eventoDeportivo.getEquipoB()
            });

            int filas = filasPorDeporte(eventoDeportivo.getOpcional());
            for (int i = 0; i < filas; i++) {
                modelo.addRow(new Object[]{"", ""});
            }

            ArrayList<String> a = eventoDeportivo.getIntegrantesA();
            ArrayList<String> b = eventoDeportivo.getIntegrantesB();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (i < a.size()) modelo.setValueAt(a.get(i), i, 0);
                if (i < b.size()) modelo.setValueAt(b.get(i), i, 1);
            }

        } else if (ev instanceof EventoMusical) {
            this.eventoMusical = (EventoMusical) ev;
            this.eventoDeportivo = null;
            this.esDeportivo = false;

            modelo.setColumnIdentifiers(new Object[]{"Integrantes"});
            for (String s : eventoMusical.getIntegrantes()) {
                modelo.addRow(new Object[]{s});
            }
        }
    }

    private int filasPorDeporte(Enumeraciones.Deporte deporte) {
        switch (deporte) {
            case FUTBOL: return 11;
            case TENIS: return 2;
            case BASEBALL: return 9;
            case RUGBY: return 15;
            default: return 5;
        }
    }

    private void guardarIntegrantes() {
        if (esDeportivo && eventoDeportivo != null) {
            ArrayList<String> a = new ArrayList<>();
            ArrayList<String> b = new ArrayList<>();

            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object valA = modelo.getValueAt(i, 0);
                Object valB = modelo.getValueAt(i, 1);

                if (valA != null && !valA.toString().trim().isEmpty()) a.add(valA.toString().trim());
                if (valB != null && !valB.toString().trim().isEmpty()) b.add(valB.toString().trim());
            }

            int max = filasPorDeporte(eventoDeportivo.getOpcional());
            if (a.size() > max || b.size() > max) {
                JOptionPane.showMessageDialog(this,
                        "No puede haber más de " + max + " integrantes por equipo en " + eventoDeportivo.getOpcional());
                return;
            }

            eventoDeportivo.setIntegrantesA(a);
            eventoDeportivo.setIntegrantesB(b);

        } else if (!esDeportivo && eventoMusical != null) {
            ArrayList<String> integrantes = new ArrayList<>();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object val = modelo.getValueAt(i, 0);
                if (val != null && !val.toString().trim().isEmpty()) {
                    integrantes.add(val.toString().trim());
                }
            }
            eventoMusical.setIntegrantes(integrantes);
        }

        JOptionPane.showMessageDialog(this, "Integrantes guardados correctamente");
    }

    // 🔹 Botón estilizado
    private JButton crearBoton(String texto, Color base) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(base);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(base.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(base);
            }
        });

        return btn;
    }

    // 🔹 Scrollbar personalizada
    private static class CustomScrollBarUI extends BasicScrollBarUI {
        private final Dimension d = new Dimension();

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton btn = new JButton();
            btn.setPreferredSize(d);
            btn.setMinimumSize(d);
            btn.setMaximumSize(d);
            return btn;
        }

        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(100, 100, 100, 150);
            this.trackColor = new Color(230, 230, 230);
        }
    }
}
