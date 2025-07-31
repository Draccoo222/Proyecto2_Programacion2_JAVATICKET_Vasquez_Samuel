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
public class Usuario {
    private int edad;
    private String nombreCompleto;
    private String userName;
    private String passW;
    
    private ArrayList<Evento> eventosCreados = new ArrayList<>();
    
    public Usuario(int edad, String nombreCompleto, String userName, String passW){
        this.edad = edad;
        this.nombreCompleto = nombreCompleto;
        this.userName = userName;
        this.passW = passW;
        
        
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
   

}
