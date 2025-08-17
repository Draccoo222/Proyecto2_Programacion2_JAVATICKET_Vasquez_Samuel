package proyecto1_programacion2_javaticket_vasquez_samuel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelEditarEvent extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;

    private EventoDeportivo eventoDeportivo;
    private EventoMusical eventoMusical;
    private EventoReligioso eventoReligioso;
    
    

    private boolean esDeportivo;

    public PanelEditarEvent() 
    {
    }

    
    public void setComps(Evento ev){
        if(ev instanceof EventoDeportivo eventoDeportivo1){
            this.eventoDeportivo = eventoDeportivo1;
            this.esDeportivo = true;
            initUI();  
        }
        else if(ev instanceof EventoReligioso eventorel){
            this.eventoReligioso = eventorel;
        }
        else if(ev instanceof EventoMusical eventomus){
            this.eventoMusical = eventomus;
            initUI();  
        }
    
    }

    private void initUI() {
        setLayout(new BorderLayout());

        if (esDeportivo) {
            modelo = new DefaultTableModel(
                    new Object[]{"Equipo " + eventoDeportivo.getEquipoA(), "Equipo " + eventoDeportivo.getEquipoB()},
                    0
            );
            tabla = new JTable(modelo);

            int filas = filasPorDeporte(eventoDeportivo.getOpcional());
            for (int i = 0; i < filas; i++) {
                modelo.addRow(new Object[]{"", ""});
            }

            cargarIntegrantesDeportivos();

        } else {
            modelo = new DefaultTableModel(new Object[]{"Integrantes"}, 0);
            tabla = new JTable(modelo);
            cargarIntegrantesMusical();
        }

        // 🎨 Estilizar tabla
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tabla.getTableHeader().setBackground(new Color(45, 118, 232));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setSelectionBackground(new Color(184, 207, 229));
        tabla.setSelectionForeground(Color.BLACK);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JButton btnGuardar = new JButton("Guardar Integrantes");
        btnGuardar.setBackground(new Color(45, 118, 232));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.addActionListener(e -> guardarIntegrantes());

        JButton btnAgregarFila = new JButton("Agregar Fila");
        btnAgregarFila.setBackground(new Color(34, 167, 132));
        btnAgregarFila.setForeground(Color.WHITE);
        btnAgregarFila.setFocusPainted(false);
        btnAgregarFila.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAgregarFila.addActionListener(e -> modelo.addRow(esDeportivo ? new Object[]{"", ""} : new Object[]{""}));

        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        abajo.setBackground(Color.WHITE);
        abajo.add(btnGuardar);
        if (!esDeportivo) {
            abajo.add(btnAgregarFila);
        }

        setBackground(Color.WHITE);
        add(scroll, BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);
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
    private void cargarIntegrantesDeportivos() {
        ArrayList<String> a = eventoDeportivo.getIntegrantesA();
        ArrayList<String> b = eventoDeportivo.getIntegrantesB();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (i < a.size()) modelo.setValueAt(a.get(i), i, 0);
            if (i < b.size()) modelo.setValueAt(b.get(i), i, 1);
        }
    }

    private void cargarIntegrantesMusical() {
        ArrayList<String> integrantes = eventoMusical.getIntegrantes();
        for (String s : integrantes) {
            modelo.addRow(new Object[]{s});
        }
    }

    private void guardarIntegrantes() {
        if (esDeportivo) {
            ArrayList<String> a = new ArrayList<>();
            ArrayList<String> b = new ArrayList<>();

            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object valA = modelo.getValueAt(i, 0);
                Object valB = modelo.getValueAt(i, 1);

                if (valA != null && !valA.toString().trim().isEmpty()) {
                    a.add(valA.toString().trim());
                }
                if (valB != null && !valB.toString().trim().isEmpty()) {
                    b.add(valB.toString().trim());
                }
            }

            int max = filasPorDeporte(eventoDeportivo.getOpcional());
            if (a.size() > max || b.size() > max) {
                JOptionPane.showMessageDialog(this, "No puede haber más de " + max + " integrantes por equipo en " + eventoDeportivo.getOpcional());
                return;
            }

            eventoDeportivo.setIntegrantesA(a);
            eventoDeportivo.setIntegrantesB(b);

        } else {
            ArrayList<String> integrantes = new ArrayList<>();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object val = modelo.getValueAt(i, 0);
                if (val != null && !val.toString().trim().isEmpty()) {
                    integrantes.add(val.toString().trim());
                }
            }
            eventoMusical.setIntegrantes(integrantes);
        }

        JOptionPane.showMessageDialog(this, "Integrantes guardados correctamente ✅");
    }

}
