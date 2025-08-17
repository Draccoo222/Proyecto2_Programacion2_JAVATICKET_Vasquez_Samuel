/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;
import java.util.ArrayList;
/**
 *
 * @author unwir
 */
abstract public class Usuario {
    private int edad;
    private String nombreCompleto;
    private String userName;
    private String passW;
    
    private String type;
    
    private ArrayList<Evento> eventosCreados;
    
    public Usuario(int edad, String nombreCompleto, String userName, String passW){
        this.edad = edad;
        this.nombreCompleto = nombreCompleto;
        this.userName = userName;
        this.passW = passW;
        eventosCreados = new ArrayList<>();
    }

    public int getEdad() {
        return edad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassW() {
        return passW;
    }

    public ArrayList<Evento> getEventosCreados() {
        return eventosCreados;
    }
    

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassW(String passW) {
        this.passW = passW;
    }
    
    public void setTipoUser(int caso){
        switch(caso){
            case 0:
                type = "admin";
                break;
            case 1:
                type = "contenidos";
                break;
            case 2:
                type = "limitado";
                break;   
            default:
                type = "nulo";   
        }
    }
    
    public Evento buscarEvento(int codigo, int indice){
        if(indice >= eventosCreados.size()){
            return null;
        }
        if(eventosCreados.get(indice).getCodigo() == codigo){
            return eventosCreados.get(indice);
        }
        return buscarEvento(codigo, indice + 1);
    }
    
    public String getTipoUser(){
        return type;
    }
    
    
    public String infoUser(){
           return "Nombre: " + getNombreCompleto() +
               " Usuario: " + getUserName() +
               " Edad: " + getEdad() +
              " Tipo: " + getTipoUser();
    }
   
  
    @Override
    public String toString(){
        return getUserName();
    }

}
