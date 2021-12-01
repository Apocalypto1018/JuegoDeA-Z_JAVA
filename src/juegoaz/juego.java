
package juegoaz;

/**
 *
 * @author 
 */
public class juego {
    char[] letra=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    private  String jugador1;
    private  String jugador2;
    private  String jugador3;
    
    private int tiempo=0;
    private int turno=0;

    //constructor
    public juego(String jugador1, String jugador2, String jugador3, int tiempo, int turno){
        this.jugador1=jugador1;
        this.jugador2=jugador2;
        this.jugador3=jugador3;
        
        this.tiempo=tiempo;
        
        this.turno=turno;
    }
    
    //metodo que corre el juego
    public void run(){
        
    }
}
