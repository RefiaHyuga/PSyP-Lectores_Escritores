
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedro
 */

public class LECanvas extends JPanel {

    //private ParticulaLector[] particles = new ParticulaLector[0];
    private estadop[] posicionesL;
    private estadop[] posicionesE;
    private int numlectores;
    private int numescritores;

    public LECanvas(Dimension size, int numlect, int numesc) {
        this.setSize(size);
        this.setBackground(Color.white);
        numlectores = numlect;
        numescritores = numesc;
        posicionesL = new estadop[numlectores];
        posicionesE = new estadop[numescritores];
        for (int i = 0; i < numlectores; i++) {
            try {
                posicionesL[i] = new estadop();
                posicionesL[i].posx = 0;
                posicionesL[i].posy = 0;
                posicionesL[i].quiere = false;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        for (int i = 0; i < numescritores; i++) {
            posicionesE[i] = new estadop();
            posicionesE[i].posx = 0;
            posicionesE[i].posy = 0;
            posicionesE[i].quiere = false;
        }
    }
    // Intended to be called by applet

    public synchronized void setLector(int id, estadop estado) {
        posicionesL[id].posx = estado.posx;
        posicionesL[id].posy = estado.posy;
        posicionesL[id].quiere = estado.quiere;
        repaint();
    }

    public synchronized void setEscritor(int id, estadop estado) {
        posicionesE[id].posx = estado.posx;
        posicionesE[id].posy = estado.posy;
        posicionesE[id].quiere = estado.quiere;
        repaint();
    }

    // IMPLEMENTA DOBLE BUFFER PARA EVITAR EL PARPADEO
    // Llama directamente a paint(), evitando el borrado del componente
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        // Se crea una imagen del mismo tamaño que el Canvas
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), ColorModel.OPAQUE);
        Graphics gbuf = imagen.getGraphics();

        // Se dibuja en la imagen

        //Particula[] ps = particles;//getParticles();

        gbuf.setColor(Color.white);
        gbuf.fillRect(0, 0, this.getWidth(), this.getHeight());
        gbuf.setColor(Color.BLACK);
        gbuf.fillRect(0, 198, 400, 4);
        //Pintamos los lectores
        for (int i = 0; i < numlectores; i++) {
            gbuf.setColor(Color.blue);
            gbuf.fillOval(posicionesL[i].posx, posicionesL[i].posy, 20, 20);
            if (posicionesL[i].quiere) {
                gbuf.setColor(Color.BLACK);
                gbuf.fillRect(posicionesL[i].posx + 5, posicionesL[i].posy + 5, 10, 10);
            }

        }
        //Pintamos los escritores
        for (int i = 0; i < numescritores; i++) {
            gbuf.setColor(Color.red);
            gbuf.fillOval(posicionesE[i].posx, posicionesE[i].posy, 20, 20);
            if (posicionesE[i].quiere) {
                gbuf.setColor(Color.BLACK);
                gbuf.fillRect(posicionesE[i].posx + 5, posicionesE[i].posy + 5, 10, 10);
            }

        }


        g.drawImage(imagen, 0, 0, this);
    }
}
