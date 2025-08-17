/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_programacion2_javaticket_vasquez_samuel;

/**
 *
 * @author unwir
 */
public final class Enumeraciones {
    
 
    
    public enum Deporte {
       TENIS("tennis"), RUGBY("rugby"), BASEBALL("baseball"), FUTBOL("futbol");
       
       private final String nombre;
  
       Deporte(String nombre){
        this.nombre = nombre;
       }

        @Override
        public String toString() {
            return nombre;
        }
       
       
       
    }
    
    public enum Musica {
        ROCK("rock"), POP("pop"), CLASICA("clasica"), RAP("rap"), REGGEATON("reggeaton"), OTRO("otro");
       
        private final String name;
        
        Musica(String name){
         this.name = name;
        }
        
        @Override
        public String toString() {
            return name;
        }
       
   
    }
    
 
    
}
