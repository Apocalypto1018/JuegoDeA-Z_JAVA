
package juegoaz;

/**
 *
 * @author 
 */
public class Jugador {
    
    private String nombre;
    private char tipo;
    
    //constructor
    public Jugador(String nombre, char tipo){
        this.nombre=nombre;
        this.tipo=tipo;
    }
    
    //setters
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    
    public void setTipo(char tipo){
        this.tipo=tipo;
    }
    
    //getters
    public String getNombre(){
        return this.nombre;
    }
    
    public char getTipo(){
        return this.tipo;
    }
    
}
