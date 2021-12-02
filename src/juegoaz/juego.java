
package juegoaz;

import java.util.Scanner;

/**
 *
 * @author 
 */
public class juego {
    private static char[] letra=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    private static Scanner sc = new Scanner(System.in);
    
    private  String jugador1;
    private  String jugador2;
    private  String jugador3;
    
    private int tiempo=0;
    private int turno=0;
    
    private int puntosJugador1=0;
    private int puntosJugador2=0;

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
        
        int iterador=0;
        int i=0;
        String palabra=" ";
        
        i=turno;
        
        while(!palabra.equals("fin")){ //ciclo que sirve como loop del juego
            
            if(i==0){
                
                marcador(this.jugador1, this.tiempo, iterador, puntosJugador1); 
                
                System.out.print("\n*Ingrese la palabra -> ");
                palabra=sc.nextLine();
                
                if(!palabra.equals("fin")){
                    
                    if(validarPalabra(palabra, jugador3)){
                        System.out.print("\n*Correcto. Ha ganado un punto extra\n");
                        puntosJugador1++;
                    }else{
                        System.out.print("\n*Incorrecto. Perdera un punto como penalizacion\n");
                        puntosJugador1--;
                    }
                
                    i=1; //indica que el siguiente turno sera para el otro jugador
                }
    
            }else{
                
                marcador(this.jugador2, this.tiempo, iterador, puntosJugador2);
                
                System.out.print("\n*Ingrese la palabra -> ");
                palabra=sc.nextLine();
                
                if(!palabra.equals("fin")){

                    if(validarPalabra(palabra, jugador3)){
                        System.out.print("\n*Correcto. Ha ganado un punto extra\n");
                        puntosJugador2++;
                    }else if(!palabra.equals("fin")){
                        System.out.print("\n*Incorrecto. Perdera un punto como penalizacion\n");
                        puntosJugador2--;
                    }

                    i=0; //indica que el siguiente turno sera para el otro jugador
                }   
                
            }
            
            iterador++;  
            
            System.out.print("\nEsperando enter...");
            sc.nextLine(); //se limpia el buffer
            
            if(iterador>26){ //si el iterador sobrepasa la cantidad de letras del abecedario
                iterador=0; //vuelve a acero
            }
            
        }
        
        System.out.print("Volviendo al menu principal\n\n");
        
    }
    
    public static void marcador(String jugador, int tiempo, int iterador, int puntos){
        System.out.print("        Jugando de la A a la Z!!\n\n");
     
        System.out.print("Turno del jugador: "+jugador);
        
        System.out.print("    Letra: "+letra[iterador]);
        
        System.out.print("    Puntos: "+puntos);
   
    }
    
    public static boolean validarPalabra(String palabra, String arbitro){
        char orden;
        
        //validar elegir orden
        do {
            System.out.print(arbitro+" ingrese 's' si la palabra: "+palabra+" es acertada, o 'n' de lo contrario\n->");
            orden= sc.next().charAt(0); //se lee el caracter     

            if (orden != 's' && orden != 'n') {
              System.out.println("ERROR!, debe introducir el caracter s, o el caracter n para validar la palabra");
            }
        } while (orden != 's' && orden != 'n'); //ciclo que valida el ingreso de un caracter correcto
        
        if(orden=='s'){
            return true;
        }else{
            return false;
        }  
    }
}
