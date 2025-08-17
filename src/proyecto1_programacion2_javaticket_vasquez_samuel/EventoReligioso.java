/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;

import java.util.Calendar;

/**
 *
 * @author unwir
 */
public class EventoReligioso extends Evento {
     private int cantConvertidos;
   

    public EventoReligioso(int codigo, String nombre, String descripcion, Calendar fecha, double montoRenta, int cantGente) {
        super(codigo, nombre, descripcion, fecha, montoRenta, cantGente);
    }
    
    
    public String getTypeEvent(){
        return "religioso"; 
    }
    
    public void setConvertidos(int cant){
        cantConvertidos = cant; 
    }
    
    public String getBonus(){
        return "nulo";
    }
    
     @Override
    public String toString() {
        return super.toString();
    }
    
    
}
