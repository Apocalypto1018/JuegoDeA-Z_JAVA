
package juegoaz;

/**
 *
 * @author 
 */
public class Tiempo extends Thread{
    private int n=0;
    
    @Override
    public void run(){
        for(int i=0;i<=50;i++){
            delaySegundos();
            n++;
        }
    }
    
    private static void delaySegundos(){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            
        }
    }
    
    public int getN(){
        return n;
    }
    
    public void setN(int n){
        this.n=n;
    }
}
