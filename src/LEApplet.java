/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author pedro
 */
public class LEApplet extends JFrame {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    private int cantidadL = 6;
    private int cantidadE = 3;
    private Thread[] lectores;
    private Thread[] escritores;

    public void init() {
        // TODO start asynchronous download of heavy resources
        Dimension tamaño = new Dimension(400, 400);
        this.setSize(tamaño);
        this.setBackground(Color.white);
        LECanvas vista = new LECanvas(tamaño, cantidadL, cantidadE);
        add(vista);
        Recurso zona = new Recurso();
        lectores = new Thread[cantidadL];
        escritores = new Thread[cantidadE];
        for (int i = 0; i < cantidadL; i++) {
            lectores[i] = new ParticulaLector(vista, zona, i);
        }
        for (int i = 0; i < cantidadE; i++) {
            escritores[i] = new ParticulaEscritor(vista, zona, i);
        }
    }
    // TODO overwrite start(), stop() and destroy() methods

    public void start() {
        for (int i = 0; i < cantidadL; i++) {
            lectores[i].start();
        }
        for (int i = 0; i < cantidadE; i++) {
            escritores[i].start();
        }

    }

    public void stop() {
        for (int i = 0; i < cantidadL; i++) {
            lectores[i].interrupt();
        }
        for (int i = 0; i < cantidadE; i++) {
            escritores[i].interrupt();
        }
    }
    
    public static void main(String[] args) {
        LEApplet frame = new LEApplet();
        frame.setVisible(true);
        frame.init();
        frame.start();
    }
}
