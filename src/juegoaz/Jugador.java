
package juegoaz;

import java.util.UUID;

/**
 *
 * @author 
 */
public final class Jugador {
    
    private String id;
    
    private String nombre;
    private char tipo;
    
    private int aciertos;
    private int fallos;
    
    //constructor
    public Jugador(String nombre, char tipo, int aciertos, int fallos){
        this.id=generarID();
        
        this.nombre=nombre;
        this.tipo=tipo;
        this.aciertos=aciertos;
        this.fallos=fallos;
    }
    
    //metodo que genero un id unico
    public String generarID(){
            
        UUID uniqueKey = UUID.randomUUID();

        return filter(String.valueOf(uniqueKey));
    }
    
    //metodo que toma los 3 primeros digitos del id generado
    public String filter(String word) {

        StringBuilder builder = new StringBuilder();

        String palabras[] = word.split(" ");

        for (String palabra : palabras) {

            if (palabra.length() >= 3) {
               builder.append(palabra.substring(0, 3));
            } else {
               builder.append(palabra);
            }
        }
        return builder.toString();
    }
    
    //setters
    public void nuevoID(){
        this.id=generarID();
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setTipo(char tipo){
        this.tipo=tipo;
    }
    
    public void setAciertos(int aciertos){
        this.aciertos=aciertos;
    }
    
    public void setFallos(int fallos){
        this.fallos=fallos;
    }
            
    
    //getters
    public String getID(){
        return this.id;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public char getTipo(){
        return this.tipo;
    }
    
    public int getAciertos(){
        return this.aciertos;
    }
    
    public int getFallos(){
        return this.fallos;
    }
    
}
