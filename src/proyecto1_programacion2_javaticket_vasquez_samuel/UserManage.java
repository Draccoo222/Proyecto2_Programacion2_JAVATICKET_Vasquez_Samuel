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
public class UserManage {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
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
    
    public boolean signIn(int edad, String nombreCompleto, String userName, String passW){
        if(buscarUsuario(userName) == null){
            usuarios.add(new Limitado(edad, nombreCompleto, userName, passW));
            return true;      
        }
        return false;
    }
    
    public boolean confirmarContra(String pass){
        return pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.\\d)(?=.*[^a-zA-Z\\d]).{8,}$");
    }
    
    public void addAdmin(){
        if(!yaExiste){
            usuarios.add(new Admin(1, "Administrador", "Admin", "supersecreto"));
            yaExiste = true;
        }
    }
    
    public String checkUser(){
        if(userActual != null){
           return userActual.getTipoUser();
        }
        return "nulo";
    }
    
    
    
    
}
