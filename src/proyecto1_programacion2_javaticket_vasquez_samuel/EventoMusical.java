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
public class EventoMusical extends Evento {
    private Enumeraciones.Musica tipo;
    ArrayList<String> integrantes;
    
    
    
    public EventoMusical(int codigo,String nombre, String descripcion, Calendar fecha, double montoRenta, int cantGente, Enumeraciones.Musica tipo ) {
        super(codigo, nombre, descripcion, fecha, montoRenta + (montoRenta*0.3), cantGente);
        this.tipo = tipo;
        integrantes = new ArrayList<>();
    }
    
    public String getTypeEvent(){
        return "musical";
    }
    

    public ArrayList<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<String> integrantes) {
        this.integrantes = integrantes;
    }
    
    
    public String getOpcional(){
        return tipo.name();
    }

    public String getBonus(){
        return tipo.toString();
    }
   
    @Override
    public String toString() {
        return super.toString() + "\n Genero Musical: " + getOpcional() + "\n";
    }
    
    
}
