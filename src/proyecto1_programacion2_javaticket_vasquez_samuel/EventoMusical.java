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
public class EventoMusical extends Evento {
    private Enumeraciones.Musica tipo;
    
    
    
    public EventoMusical(int codigo,String nombre, String descripcion, Calendar fecha, double montoRenta, int cantGente, Enumeraciones.Musica tipo ) {
        super(codigo, nombre, descripcion, fecha, montoRenta, cantGente);
        this.tipo = tipo;
    }
    
    public String getTypeEvent(){
        return "musical";
    }
    
    
    public String getOpcional(){
        return tipo.name();
    }

   
    @Override
    public String toString() {
        return super.toString() + "\n Genero Musical: " + getOpcional() + "\n";
    }
    
    
}
