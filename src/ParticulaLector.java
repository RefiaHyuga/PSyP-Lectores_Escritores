
import java.util.Random;
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
public class ParticulaLector extends Thread {

    private int id;
    private estadop estado = new estadop();
    protected final Random rng = new Random();
    private LECanvas canvas;
    private Recurso Zona;

    public ParticulaLector(LECanvas canvas, Recurso zona, int i) {
        estado.posx = rng.nextInt(399);
        estado.posy = rng.nextInt(190);
        estado.quiere=false;
        this.canvas = canvas;
        Zona = zona;
        id = i;
    }

    @Override
    public void run() {

        while (true) {
            moveup();
            estado.quiere = true;
            canvas.setLector(id, estado);
            Zona.quiereleer();
            movedown();
            Zona.finleer();
            estado.quiere = false;
        }
    }

    public void moveup() {

        long espera = (rng.nextInt(20) + 3) * 1000;
        long empieza = System.currentTimeMillis();//instante.getTime();
        estado.posx = rng.nextInt(399);
        estado.posy = rng.nextInt(190);
        while (System.currentTimeMillis() - empieza < espera) {
            estado.posx += rng.nextInt(20) - 10;
            estado.posy += rng.nextInt(20) - 10;
            if (estado.posy < 0) {
                estado.posy = 0;
            }
            if (estado.posx < 0) {
                estado.posx = 0;
            }
            if (estado.posx > 379) {
                estado.posx = 379;
            }
            if (estado.posy > 169) {
                estado.posy = 169;
            }
            canvas.setLector(id, estado);
            try {
                Thread.sleep(rng.nextInt(100) + 100); // 100msec is arbitrary
            } catch (InterruptedException ex) {
                Logger.getLogger(ParticulaLector.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

        }
    }

    public void movedown() {
        long espera = (rng.nextInt(5) + 1) * 1000;
        long empieza = System.currentTimeMillis();//instante.getTime();
        estado.posx = rng.nextInt(399);
        estado.posy = rng.nextInt(190)+210;

        while (System.currentTimeMillis() - empieza < espera) {
            estado.posx += rng.nextInt(20) - 10;
            estado.posy += rng.nextInt(20) - 10;
            if (estado.posy < 220) {
                estado.posy = 220;
            }
            if (estado.posx < 0) {
                estado.posx = 0;
            }
            if (estado.posx > 379) {
                estado.posx = 379;
            }
            if (estado.posy > 379) {
                estado.posy = 379;
            }
            canvas.setLector(id, estado);
            try {
                Thread.sleep(rng.nextInt(100) + 100); // 100msec is arbitrary
            } catch (InterruptedException ex) {
                Logger.getLogger(ParticulaLector.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }

    }
}
