/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoaz;

/**
 *
 * @author Copito
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
