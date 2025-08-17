/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author unwir
 */
public class EventoDeportivo extends Evento {
    private final String equipoA;
    private final String equipoB;
    private ArrayList<String> integrantesA;
    private ArrayList<String> integrantesB;
    private Enumeraciones.Deporte tipo;

    public EventoDeportivo(int codigo, String nombre, String descripcion, Calendar fecha, double montoRenta,
            int cantGente,Enumeraciones.Deporte tipo, String equipoA, String equipoB) {
        super(codigo, nombre, descripcion, fecha, montoRenta, cantGente);
        this.tipo = tipo;
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        integrantesA = new ArrayList<>();
        integrantesB = new ArrayList<>();
    }
    
    String getTypeEvent() {
       return "deportivo";
    }
    
    public String getOpcional(){
        return tipo.name();
    }
    
    public String getEquipoA() {
        return equipoA;
    }
    
    public String getEquipoB() {
        return equipoB;
    }

    public ArrayList<String> getIntegrantesA() {
        return integrantesA;
    }
    
     public ArrayList<String> getIntegrantesB() {
        return integrantesB;
    }

    
     @Override
    public String toString() {
        return super.toString() + "\n Deporte: " + getOpcional() + "\n";
    }
    
    
    
}
