/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author unwir
 */
public abstract class Evento {
    private int codigo;
    private String nombre;
    private String descripcion;
    private Calendar fecha;
    private double montoRenta;
    private int cantGente;
    protected boolean cancelado;

    public Evento(int codigo, String nombre, String descripcion, Calendar fecha, double montoRenta, int cantGente) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.montoRenta = montoRenta;
        this.cantGente = cantGente;
        cancelado = false;
    }

    public void setCantGente(int cantGente) {
        this.cantGente = cantGente;
    }

    public boolean isCancelado() {
        return cancelado;
    }
    
    public void setCancel() {
        cancelado = true;
    }
  
    
    abstract String getTypeEvent();
    
    public void setPrice(double montoRenta){
        this.montoRenta = montoRenta;
    }

    public double getMontoRenta() {
        return montoRenta;
    }

    public int getCantGente() {
        return cantGente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

  
    abstract public String getBonus();

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "codigo: " + codigo + "\n nombre: " + nombre + "\n descripcion: " + descripcion + "\n fecha: " + sdf.format(fecha.getTime()) + "\n montoRenta: " + montoRenta + "\n cantGente: " + cantGente + 
                "\n Cancelado: " + ((cancelado) ? "Si" : "No");
    }
    
    public String eventoPrint() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "(Codigo: " + codigo + ", Nombre: " + nombre + ", Tipo: " + 
               getTypeEvent() + ", fecha: " + sdf.format(fecha.getTime()) + ", Monto de Renta: " + montoRenta + ")\n";
    }
   

   
    
}
