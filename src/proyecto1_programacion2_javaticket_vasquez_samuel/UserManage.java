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
    
    
    public UserManage getInstance(){
        if(instancia == null){
           instancia = new UserManage();
           usuarios.add(new Admin(20, "ADMIN", "ADMINISTRADOR", "supersecreto"));
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
    
    public boolean logIn(String userName){
        if(buscarUsuario(userName) != null){
            for (Usuario u: usuarios){
                if(u.getUserName().equals(userName)){
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
    
    
    
}
