package proyecto1_programacion2_javaticket_vasquez_samuel;


import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class CalendarEventos {

    private UserManage uMan;
    private JDayChooser dayChooser;
    private JCalendar jcal;

    public CalendarEventos(UserManage uMan) {
        this.uMan = uMan;
    }

    public JDateChooser crearDateChooserConEventos() {
        JDateChooser dateChooser = new JDateChooser();
        this.jcal = dateChooser.getJCalendar();
        this.dayChooser = jcal.getDayChooser();

        // Detectar cambios en mes, año o día
        jcal.addPropertyChangeListener(evt -> {
            String prop = evt.getPropertyName();
            if ("month".equals(prop) || "year".equals(prop) || "day".equals(prop)) {
                actualizarColores(dayChooser, jcal);
            }
        });

        // Pintar al inicio
        actualizarColores(dayChooser, jcal);

        return dateChooser;
    }

    public void refrescarEventos() {
        if (dayChooser != null && jcal != null) {
            actualizarColores(dayChooser, jcal);
        }
    }

    private void actualizarColores(JDayChooser dayChooser, JCalendar jcal) {
        JPanel dayPanel = dayChooser.getDayPanel();
        Component[] comps = dayPanel.getComponents();
        Calendar cal = (Calendar) jcal.getCalendar().clone();

        int diaSeleccionado = cal.get(Calendar.DAY_OF_MONTH);

        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton dayButton = (JButton) comp;
                try {
                    int dayNum = Integer.parseInt(dayButton.getText());
                    cal.set(Calendar.DAY_OF_MONTH, dayNum);

                    StringBuilder tooltipText = new StringBuilder();
                    boolean esEvento = uMan.getEventosTotales().stream().anyMatch(evento -> {
                        Calendar f = Calendar.getInstance();
                        f.setTime(evento.getFecha().getTime());
                        if (!evento.isCancelado() && f.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &&
                            f.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &&
                            f.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
                            tooltipText.append(evento.getNombre()).append("\n");
                            return true;
                        }
                        return false;
                    });

                    // Configuración base del botón
                    dayButton.setOpaque(false); // importante: false para pintar nosotros
                    dayButton.setContentAreaFilled(false);
                    dayButton.setBorderPainted(false);
                    dayButton.setToolTipText(
                            esEvento ? "<html>" + tooltipText.toString().replace("\n", "<br>") + "</html>" : null
                    );

                    // Guardar variables para pintado
                    boolean seleccionado = (dayNum == diaSeleccionado);
                    boolean tieneEvento = esEvento;

                    // UI personalizado
                    dayButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                        @Override
                        public void paint(Graphics g, JComponent c) {
                            Graphics2D g2 = (Graphics2D) g.create();

                            int w = c.getWidth();
                            int h = c.getHeight();

                            if (tieneEvento) {
                                // Degradado amarillo para eventos
                                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 255, 180),
                                        0, h, new Color(255, 240, 130));
                                g2.setPaint(gp);
                                g2.fillRect(0, 0, w, h);

                                // Borde naranja para eventos
                                g2.setColor(new Color(255, 180, 0));
                                g2.setStroke(new BasicStroke(2));
                                g2.drawRect(1, 1, w - 3, h - 3);
                            } else {
                                // Fondo normal blanco
                                g2.setColor(Color.WHITE);
                                g2.fillRect(0, 0, w, h);
                            }

                            if (seleccionado) {
                                // Resaltado del día seleccionado (azul semitransparente)
                                g2.setColor(new Color(100, 150, 255, 100));
                                g2.fillRect(0, 0, w, h);
                            }

                            g2.dispose();
                            super.paint(g, c);
                        }
                    });

                } catch (NumberFormatException ex) {
                    // Botones vacíos o encabezados
                    dayButton.setOpaque(true);
                    dayButton.setBackground(Color.LIGHT_GRAY);
                    dayButton.setToolTipText(null);
                }
            }
        }
        dayChooser.repaint();
    }
}
