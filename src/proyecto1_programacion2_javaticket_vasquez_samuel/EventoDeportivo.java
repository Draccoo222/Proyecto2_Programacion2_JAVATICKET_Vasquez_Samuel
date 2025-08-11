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
    private String equipoA;
    private String equipoB;
    private ArrayList<String> integrantes;

    
    
        

    public EventoDeportivo(int codigo, int cantGente, String nombre, String descripcion, Calendar fecha) {
        super(codigo, cantGente, nombre, descripcion, fecha);
        integrantes = new ArrayList<>();
    }

    
    String getTypeEvent() {
       return "deportivo";
    }

    public String getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(String equipoA) {
        this.equipoA = equipoA;
    }

    public String getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(String equipoB) {
        this.equipoB = equipoB;
    }

    public ArrayList<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<String> integrantes) {
        this.integrantes = integrantes;
    }
    
    
    
    
}
