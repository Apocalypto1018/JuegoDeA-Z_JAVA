
package juegoaz;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class JuegoAZ {
    private static char[] letra=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
    
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
            System.out.print("*Ingrese 1 para registrar jugadores y jugar\n");
            System.out.print("*Ingrese 2 para ir al Menu de jugadores\n");
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
                
                case 2:{
                    menuJugadores();
                    break;
                }
                
                default:{
                    System.out.println("Parametro no valido, intente de nuevo");
                    break;
                }
            }

        } while (!salir);
         
        System.exit(0); //si se sale del bucle principal, entonces termina la aplicacion

    }
    
    //metodo de validacion del registro
    public static void registro(){
        //var locales
        int registro=0;
        int iniciar=-1;
        int turno=0;
        
       do{
            //validar registro
            do{
                //solicitud de los datos de los jugadores y el tiempo por turno
                System.out.print("*Ingrese el alias del Jugador 1\n->");
                jugador1 = sc.nextLine();

                System.out.print("*Ingrese el alias del Jugador 2\n->");
                jugador2 = sc.nextLine();

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

                }while(tiempo<=0 || tiempo>25); //validacion de un tiempo optimo
            }else{
                tiempo=0; //de no querer jugar con tiempo, este sera cero...
            }
            
            //Opcion de iniciar juego solicitada...
            do {
                System.out.print("*Ingrese 1 para INICIAR\n");
                System.out.print("*Ingrese 0 para Volver al menu principal\n->");
                iniciar=sc.nextInt();
                
            } while (iniciar<0 || iniciar>1); //ciclo que valida una opcion correcta
            
            sc.nextLine(); //se limpia el buffer
       }while(iniciar<0 || iniciar>1);
         
       if(iniciar==1){
       //al confirmar la partida, se pasan los valores a la lista
            jugadores.add(new Jugador(jugador1, 'j',0,0));
            jugadores.add(new Jugador(jugador2, 'j',0,0));
            jugadores.add(new Jugador(jugador3, 'a',0,0));
            
            run(jugador1, jugador2, jugador3, tiempo, turno); //metodo que corre el juego en si
       }else{
           System.out.print("Volviendo al menu principal\n"); //mensaje de volviendo al menu principal...
       }
       
    }
    
    //metodo que corre el juego
    public static void run(String jugador1, String jugador2, String jugador3, int tiempo, int turno){

        //var locales del metodo
        int iterador=0;
        int i=0;
        String palabra=" "; 
    
        int puntosJugador1=0;
        int puntosJugador2=0;
        
        int aciertos1=0;
        int aciertos2=0;
        
        int fallos1=0;
        int fallos2=0;
        
        i=turno;
        
        while(!palabra.equals("fin")){ //ciclo que sirve como loop del juego
            
            //si se juega con tiempo, se cuentan los segundos en el turno del jugador
            Tiempo t=new Tiempo();
            t.start();

            if(i==0){
                
                marcador(jugador1, tiempo, iterador, puntosJugador1); 
                
                System.out.print("\n*Ingrese la palabra -> ");
                palabra=sc.nextLine();
                
                if(!palabra.equals("fin")){
  
                    if(tiempo>0){ //si se juega con tiempo...
                        if(t.getN()>tiempo){ //se valida que no se exceda el tiempo
                            System.out.print("\nTiempo excedido perdera un punto, gasto: "+t.getN() +" Segundos. *El tiempo disponible era: "+tiempo);
                            puntosJugador1--;
                            fallos1++;
                        }else{ // de no excederse...
                            if(validarPalabra(palabra, jugador3)){
                                System.out.print("\n*Correcto. Ha ganado un punto extra\n");
                                puntosJugador1++;
                                aciertos1++;
                            }else{
                                System.out.print("\n*Incorrecto. Perdera un punto como penalizacion\n");
                                puntosJugador1--;
                                fallos1++;
                            }
                        }
                    }else{ //sino se juega con tiempo..
                        if(validarPalabra(palabra, jugador3)){
                            System.out.print("\n*Correcto. Ha ganado un punto extra\n");
                            puntosJugador1++;
                            aciertos1++;
                        }else{
                            System.out.print("\n*Incorrecto. Perdera un punto como penalizacion\n");
                            puntosJugador1--;
                            fallos1++;
                        }
                    }
                
                    i=1; //indica que el siguiente turno sera para el otro jugador
                }
    
            }else{
                
                marcador(jugador2, tiempo, iterador, puntosJugador2);
                
                System.out.print("\n*Ingrese la palabra -> ");
                palabra=sc.nextLine();
                
                if(!palabra.equals("fin")){

                   if(tiempo>0){ //si se juega con tiempo...
                        if(t.getN()>tiempo){ //se valida que no se exceda el tiempo
                            System.out.print("\nTiempo excedido perdera un punto, gasto: "+t.getN() +" Segundos. *El tiempo disponible era: "+tiempo);
                            puntosJugador2--;
                            fallos2++;
                        }else{ // de no excederse...
                            if(validarPalabra(palabra, jugador3)){
                                System.out.print("\n*Correcto. Ha ganado un punto extra\n");
                                puntosJugador2++;
                                aciertos2++;
                            }else{
                                System.out.print("\n*Incorrecto. Perdera un punto como penalizacion\n");
                                puntosJugador2--;
                                fallos2++;
                            }
                        }
                    }else{ //sino se juega con tiempo..
                        if(validarPalabra(palabra, jugador3)){
                            System.out.print("\n*Correcto. Ha ganado un punto extra\n");
                            puntosJugador2++;
                            aciertos2++;
                        }else{
                            System.out.print("\n*Incorrecto. Perdera un punto como penalizacion\n");
                            puntosJugador2--;
                            fallos2++;
                        }
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
            
            t.setN(0); // se resetea la n del hilo que cuenta... para contar desde cero para el siguiente turno del otro jugador
        }
 
        int itera = 0;
        int captura = -1;
        for (Jugador actual : jugadores) {
            if (actual.getNombre().equals(jugador1)) {
                captura = itera;
            }
            itera++;
        }
        
        jugadores.get(captura).setAciertos(aciertos1);
        jugadores.get(captura).setFallos(fallos1);
        
        int capj1=captura;
        
        itera = 0;
        captura = -1;
        
        for (Jugador actual : jugadores) {
            if (actual.getNombre().equals(jugador2)) {
                captura = itera;
            }
            itera++;
        }
        
        jugadores.get(captura).setAciertos(aciertos2);
        jugadores.get(captura).setFallos(fallos2);
        
        int capj2=captura;
        
        //rutina del reporte....]
        System.out.print("\n     *Reporte\n\n");
        
        if(aciertos1>aciertos2){ //si gana el jugador 1...
            System.out.println("\nGanador:\n");
            System.out.println("Nombre: "+jugadores.get(capj1).getNombre());
            System.out.println("Aciertos: "+jugadores.get(capj1).getAciertos());
            System.out.println("Fallos: "+jugadores.get(capj1).getFallos());
            
            System.out.println("\nOtro jugador:\n");
            System.out.println("Nombre: "+jugadores.get(capj2).getNombre());
            System.out.println("Aciertos: "+jugadores.get(capj2).getAciertos());
            System.out.print("Fallos: "+jugadores.get(capj2).getFallos());
            
            System.out.println("\nArbitro:\n");
            System.out.println("Nombre: "+jugador3);

        }else if(aciertos1>aciertos2){ //si gana el jugador 2...
            System.out.println("\nGanador:\n");
            System.out.println("Nombre: "+jugadores.get(capj2).getNombre());
            System.out.println("Aciertos: "+jugadores.get(capj2).getAciertos());
            System.out.print("Fallos: "+jugadores.get(capj2).getFallos());
            
            System.out.println("\nOtro jugador:\n");
            System.out.println("Nombre: "+jugadores.get(capj1).getNombre());
            System.out.println("Aciertos: "+jugadores.get(capj1).getAciertos());
            System.out.print("Fallos: "+jugadores.get(capj1).getFallos());
            
            System.out.println("\nArbitro:\n");
            System.out.println("Nombre: "+jugador3);
            
        }else{ //empate
            System.out.println("\nEmpate:\n");
            
            System.out.println("\nJugador1:\n");
            System.out.println("Nombre: "+jugadores.get(capj1).getNombre());
            System.out.println("Aciertos: "+jugadores.get(capj1).getAciertos());
            System.out.print("Fallos: "+jugadores.get(capj1).getFallos());
            
            System.out.println("\nJugador2:\n");
            System.out.println("Nombre: "+jugadores.get(capj2).getNombre());
            System.out.println("Aciertos: "+jugadores.get(capj2).getAciertos());
            System.out.print("Fallos: "+jugadores.get(capj2).getFallos());
            
            System.out.println("\nArbitro:\n");
            System.out.println("Nombre: "+jugador3);
        }
        
        System.out.print("\nEsperando enter...");
         sc.nextLine(); //se limpia el buffer
        //mensaje de salida
        System.out.print("Volviendo al menu principal\n\n");
        
    }
    
    public static void marcador(String jugador, int tiempo, int iterador, int puntos){
        System.out.print("\n\n        Jugando de la A a la Z!!\n\n");
     
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
    
    //menu de jugadores
    public static void menuJugadores(){
        String id;
        String nombre, tipo;
        char respuesta = ' ';
        int opcionCliente;
        do {
            System.out.print("1. Mostrar Lista de Jugadores.\n2. Eliminar Jugador por ID. \n3. Modificar Jugador."
                    + "\n0. Regrasar al menu principa.\n->");
            opcionCliente = sc.nextInt();
            switch (opcionCliente) {
                case 0: {
                    System.out.println("Volviendo al menu Principal");
                    break;
                }
                case 1:{
                        System.out.println("             Lista de Jugadores Totales.");
                     for (Jugador actual : jugadores) {
                        
                        System.out.println("\n\n*ID: " + actual.getID());
                        System.out.println("*Nombre: " + actual.getNombre());
                        if(actual.getTipo()=='j'){
                            System.out.println("*Tipo: Jugador" );
                            System.out.println("*Aciertos: " + actual.getAciertos());
                            System.out.println("*Fallos: " + actual.getFallos());
                        }else{
                           System.out.println("*Tipo: Arbitro" ); 
                        }
                        

                    }
        
                    System.out.print("\n\n");
                    break;
                }
                   
                case 2:{
                    sc.nextLine();
                    
                    System.out.println("            Eliminar Jugador.");
                    System.out.print("\n*Ingrese el ID del jugador a aliminar\n->");
                    id = sc.nextLine();

                    int itera = 0;
                    int captura = -1;
                    for (Jugador actual : jugadores) {
                        if (actual.getID().equals(id)) {
                            captura = itera;
                        }
                        itera++;
                    }
                    if (captura != -1) {
                        do{
                            System.out.println("\n\n*ID: " + jugadores.get(captura).getID());
                            System.out.println("*Nombre: " + jugadores.get(captura).getNombre());
                            System.out.println("*Tipo: " + jugadores.get(captura).getTipo());
                            System.out.println("*Aciertos: " + jugadores.get(captura).getAciertos());
                            System.out.println("*Fallos: " + jugadores.get(captura).getFallos());
                            System.out.println("Desea eliminar a este Jugador? s/n");
                            respuesta = sc.next().charAt(0);
                            
                            if(respuesta!='s' && respuesta!='n'){
                                System.out.println("*\nSolo puede ingresar 's' o 'n'\n" );
                            }
                        }while(respuesta!='s' && respuesta!='n');
                        
                        if (respuesta == 's') {
                            jugadores.remove(captura);
                            System.out.println("Jugador eliminado exitosamente");
                        } else {
                            break;
                        }
                    }else{
                        System.out.println("No existen datos para el ID ingresado");
                    }
                    
                    break;
                }
                
                case 3:{
                    sc.nextLine();
                    System.out.println("Modificar Jugador");
                    System.out.println("Ingrese ID del Jugador");
                    id = sc.nextLine();
                     
                    int itera = 0;
                    int captura = -1;
                    for (Jugador actual : jugadores) {
                        if (actual.getID().equals(id)) {
                            captura = itera;
                        }
                        itera++;
                    }
                    if (captura != -1) {
                        modificaJugador(captura);
                    } else {
                        System.out.println("No existen datos para el ID ingresado");
                    }
                    
                    break;
                }

                default:
                    System.out.println("Opcion no valida");

            }
        } while (opcionCliente != 0);
    }
    
    //sub menu para modificar los jugadores
    public static void modificaJugador(int index) {
        int opcionModifica = -1;
        String nombre;
        char tipo;
        do {
            System.out.print("1.Nombre.\n2.Tipo.\n3.Nuevo ID aleatorio"
                    + "\n0. volver.\n->");
            opcionModifica = sc.nextInt();
            sc.nextLine();
            switch (opcionModifica) {
                case 0:
                    System.out.println("*Volviendo al menu anterior");
                    break;
                case 1:
                    System.out.println("*Nuevo Nombre:");
                    nombre = sc.nextLine();
                    jugadores.get(index).setNombre(nombre);
                    break;
                case 2:
        
                    do {
                        System.out.println("*Nuevo Tipo: a= arbitro / j= jugador");
                        tipo = sc.next().charAt(0);

                        if (tipo != 'a' && tipo != 'j') {
                          System.out.println("ERROR!, debe introducir o el caracter a, o el caracter j para indicar tipo de jugador");
                        }
                    } while (orden != 'a' && orden != 'j'); //ciclo que valida el ingreso de un caracter correcto
                    jugadores.get(index).setTipo(tipo);
                    break;
                case 3:
                    jugadores.get(index).nuevoID();
                    System.out.println("*Nuevo id: "+jugadores.get(index).getID());
                
                default:{
                    System.out.println("Ingrese una opcion valida");
                    break;
                }
                    
                    

            }
        } while (opcionModifica != 0);
    }
    
    //metodo que elige el orden inicial de turno (genera un numero al azar entre 0 y 2 excluyendo al 2)
    public static int Turno(){
        int n=0;
        
        n=(int) (Math.random()*2); 
        
        return n;
    }
}
    
    

