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
    private int tiempo;
    private char tipo;
    
    //constructor
    public Jugador(String nombre, int tiempo, char tipo){
        this.nombre=nombre;
        this.tiempo=tiempo;
        this.tipo=tipo;
    }
    
    //setters
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public void setTiempo(int tiempo){
        this.tiempo=tiempo;
    }
    
    public void setTipo(char tipo){
        this.tipo=tipo;
    }
    
    //getters
    public String getNombre(){
        return this.nombre;
    }
    
    public int getTiempo(){
        return this.tiempo;
    }
    
    public char getTipo(){
        return this.tipo;
    }
    
}
