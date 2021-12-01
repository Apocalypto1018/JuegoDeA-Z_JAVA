/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoaz;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class JuegoAZ {
    
    char[] letra=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    private static LinkedList<Jugador> jugadores=new LinkedList<Jugador>();
    private static Scanner sc = new Scanner(System.in);
    
    private static boolean salir = false;
    private static int opc=0;
    
    private static String jugador1;
    private static String jugador2;
    private static String jugador3;
    
    private static int tiempo=0;
    
    private static char orden;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         do {
            //menu principal
            System.out.print("   Bienvenido al Juego del A a la Z\n\n");
            System.out.print("*Ingrese 1 para registrar jugadores\n");
            System.out.print("*Ingrese 0 para salir\n->");
            opc=sc.nextInt();
            
            sc.nextLine();
            
            switch(opc){
                case 0:{
                    System.out.println("Saliendo del Juego, Adios!!");
                    salir=true;
                    break;
                }
                
                case 1:{
                    registro();
                    break;
                }
                
                default:{
                    System.out.println("Parametro no valido, intente de nuevo");
                    break;
                }
            }

        } while (!salir);

    }
    
    //metodo de validacion del registro
    public static void registro(){
        //var locales
        int registro=0;
        int iniciar=0;
        int turno=0;
        
       do{
            //validar registro
            do{
                //solicitud de los datos de los jugadores y el tiempo por turno
                System.out.print("*Ingrese el alias del Jugador 1\n->");
                jugador1 = sc.nextLine();

                System.out.print("*Ingrese el alias del Jugador 2\n->");
                jugador2 = sc.nextLine();

                sc.nextLine(); //se limpia el buffer

                //solicitud de los datos del jugador arbitro
                System.out.print("*Ingrese el alias del Jugador arbitro\n->");
                jugador3 = sc.nextLine();

                System.out.print("*Ingrese el 1 para confirmar registro e iniciar, o otro numero para volver a hacer el registro\n->");
                registro=sc.nextInt();

                sc.nextLine(); //se limpia el buffer ya que se ingreso un entero al final

            }while(registro!=1); //ciclo que valida un registro de jugadores valido

            //validar elegir orden
            do {
                System.out.print("*Ingrese 's' si desean que la pc eliga el orden, o 'n' de lo contrario\n->");
                orden= sc.next().charAt(0); //se lee el caracter     

                if (orden != 's' && orden != 'n') {
                  System.out.println("ERROR!, debe introducir o el caracter s, o el caracter n para indicar el orden");
                }
            } while (orden != 's' && orden != 'n'); //ciclo que valida el ingreso de un caracter correcto

            sc.nextLine(); //se limpia el buffer

            //una ves validada la opcion de orden de turno, se asigna para jugar
            if(orden=='s'){
                turno=Turno(); //se asigna un turno al azar
            }else{
                turno=0; //indicando que el turno inicial sera para el primer jugador
            }

            //validar jugar por tiempo
            do {
                System.out.print("*Ingrese 's' si desean Jugar con Tiempo, o 'n' de lo contrario\n->");
                orden= sc.next().charAt(0); //se lee el caracter     

                if (orden != 's' && orden != 'n') {
                  System.out.println("ERROR!, debe introducir o el caracter s, o el caracter n para indicar deseo de jugar con tiempo");
                }
            } while (orden != 's' && orden != 'n'); //ciclo que valida el ingreso de un caracter correcto

            //una ves validada la opcion...
            if(orden=='s'){
                do{
                    System.out.print("*Ingrese el tiempo por turno\n->");
                    tiempo = sc.nextInt();

                    if(tiempo<=0 && tiempo>50){
                        System.out.println("ERROR!, el tiempo ingresado no es optimo");
                    }

                }while(tiempo<=0 && tiempo>50); //validacion de un tiempo optimo
            }else{
                tiempo=0; //de no querer jugar con tiempo, este sera cero...
            }
            
            //Opcion de iniciar juego solicitada...
            do {
                System.out.print("*Ingrese 1 para INICIAR\n");
                System.out.print("*Ingrese 0 para Volver al menu principal\n->");
                iniciar=sc.nextInt();
                
            } while (iniciar<0 || iniciar>1); //ciclo que valida una opcion correcta
            
       }while(iniciar!=1);
        
       if(iniciar==1){
       //al confirmar la partida, se pasan los valores a la lista
            jugadores.add(new Jugador(jugador1, 'j'));
            jugadores.add(new Jugador(jugador2, 'j'));
            jugadores.add(new Jugador(jugador3, 'a'));
            
            jugar(jugador1, jugador2, jugador3, tiempo); //invocacion del metodo jugar
       }else{
           System.out.print("Volviendo al menu principal\n"); //mensaje de volviendo al menu principal...
       }
       

    }
    
    //metodo que elige el orden inicial de turno (genera un numero al azar entre 0 y 2 excluyendo al 2)
    public static int Turno(){
        int n=0;
        
        n=(int) (Math.random()*2); 
        
        return n;
    }
    
    //metodo que ejecuta el juego
    public static void jugar(String jugador1, String jugador2, String jugador3, int tiempo){
        
    }
    
    
}
    
    

