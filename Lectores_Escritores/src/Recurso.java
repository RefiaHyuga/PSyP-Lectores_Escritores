
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedro
 */
public class Recurso {

    private int numlectores = 0;
    private boolean hayescritor = false;

    public synchronized void quiereleer() {
        while (hayescritor) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        numlectores++;

    }

    public synchronized void finleer() {
        numlectores--;
        if (numlectores == 0) {
            notifyAll();
        }
    }

    public synchronized void quiereescribir() {
        while (hayescritor || (numlectores > 0)) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        hayescritor=true;
    }
    
    public synchronized void finescribir(){
        hayescritor=false;
        notifyAll();
    }
}