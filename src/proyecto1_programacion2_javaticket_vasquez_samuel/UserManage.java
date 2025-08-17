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
public final class UserManage {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Evento> eventosTotales = new ArrayList<>();
    private Usuario userActual;
    private static UserManage instancia;
    private boolean yaExiste = false;
    
    
    public static UserManage getInstance(){
        if(instancia == null){
           instancia = new UserManage();
           
        }
        return instancia; 
    }
    
    public Usuario buscarUsuario(String name){
        for(Usuario u: usuarios){
            if (u != null && u.getUserName().equals(name)) {
                return u;
            }
        }
        return null;
    }
    
    public ArrayList<Evento> getEventosTotales(){
        return eventosTotales;
    }
    
    public boolean logIn(String userName, String pass){
        if(buscarUsuario(userName) != null){
            for (Usuario u: usuarios){
                if(u.getUserName().equals(userName) && u.getPassW().equals(pass)){
                    userActual = u;
                    return true;
                }   
            }
            return false;
        }
        return false;
    }
    
    public boolean crearUser(String type, int edad, String nombreCompleto, String userName, String passW){
        if(buscarUsuario(userName) == null && confirmarContra(passW)){
            switch(type){
                case "admin":
                    usuarios.add(new Admin(edad, nombreCompleto, userName, passW));
                break;
                case "contenidos":
                    usuarios.add(new Contenidos(edad, nombreCompleto, userName, passW));
                break;
                case "limitados":
                    usuarios.add(new Limitado(edad, nombreCompleto, userName, passW));
                break;
            }
            return true;
        }
        return false;
    }
    
    public boolean insert(int integer, String type, int edad, String nombreCompleto, String userName, String passW){
        if(buscarUsuario(userName) == null && confirmarContra(passW)){
            switch(type){
                case "admin":
                    usuarios.add(integer, new Admin(edad, nombreCompleto, userName, passW));
                break;
                case "contenidos":
                    usuarios.add(integer, new Contenidos(edad, nombreCompleto, userName, passW));
                break;
                case "limitados":
                    usuarios.add(integer, new Limitado(edad, nombreCompleto, userName, passW));
                break;
            }
            return true;
        }
        return false;
    
    }
    
    public boolean signIn(int edad, String nombreCompleto, String userName, String passW){
        if(buscarUsuario(userName) == null){
            usuarios.add(new Limitado(edad, nombreCompleto, userName, passW));
            return true;      
        }
        return false;
    }
    
    public boolean confirmarContra(String pass){
        return pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{5,}$");
    }
    
    public void addAdmin(){
        if(!yaExiste){
            usuarios.add(new Admin(1, "Administrador", "Admin", "supersecreto"));
            yaExiste = true;
        }
    }
    
    
    
    public String getUserActualName(){
        if(userActual != null){
            return userActual.getUserName();
        }
        return "nulo";
    }
    
    public Usuario getUserActual(){
        return userActual;  
    }
    
    public String checkUser(){
        if(userActual != null){
           return userActual.getTipoUser();
        }
        return "nulo";
    }
    
    public int cantUsers(){
        return usuarios.size();
    
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public void cerrarSesion(){
        userActual = null;
    }
    
    public String listaUsers(int integer){
        if(integer < 0){
          return "";
        }
        return (integer + ": " + usuarios.get(integer).infoUser() + "\n") + listaUsers(integer - 1);
    }
    
     public Evento buscarEvento(int codigo, int indice){
        if(indice >= eventosTotales.size()){
            return null;
        }
        if(eventosTotales.get(indice).getCodigo() == codigo){
            return eventosTotales.get(indice);
        }
        return buscarEvento(codigo, indice + 1);
    }
   
    public String listaEventosCancelados(boolean cancelado, int integer){
        if(integer >= eventosTotales.size()){
          return "";
        }
        
        if(eventosTotales.get(integer).cancelado == cancelado){
         return (integer + ": " + eventosTotales.get(integer).toString() + "\n") + listaEventosCancelados(cancelado, integer + 1);
        }
        
        return listaEventosCancelados(cancelado, integer + 1);
    }
    
    public String listaEventosTotales(int integer){
        if(integer >= eventosTotales.size()){
          return "";
        }

         return (integer + ": " + eventosTotales.get(integer).toString() + "\n") + listaEventosTotales(integer + 1);
       
    }
    
     public String listaEventosEspecificos(String type, int integer) throws Exception{
        if(!type.equals("religioso") || !type.equals("deportivo")  || !type.equals("musical")){
             throw new Exception("ERROR, TIPO DE EVENTO INEXISTENTE");
        }
        if(integer >= eventosTotales.size()){
          return "";
        }
        
        if(eventosTotales.get(integer).getTypeEvent().equals(type)){
         return (integer + ": " + eventosTotales.get(integer).toString() + "\n") + listaEventosEspecificos(type, integer + 1);
        }
        
        return listaEventosEspecificos(type, integer + 1);
    }
     
    
    
}
