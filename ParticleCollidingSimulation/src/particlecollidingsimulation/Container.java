/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particlecollidingsimulation;

import java.awt.Graphics;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ruchiranga
 */
public class Container extends JPanel {

    public final int N = 50;
    Particle particles[];
    Window frame;
    JFrame jframe;

    public Container() {
        particles = new Particle[N];
        for (int i = 0; i < N; i++) {
            particles[i] = new Particle();
        }

        jframe = new JFrame("Particle System");
        frame=new Window(jframe);



        frame.setSize(400, 400);
        jframe.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        jframe.setLocationRelativeTo(null);
        frame.add(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setUndecorated(true);
//        frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        frame.setVisible(true);
        jframe.setVisible(true);

        repaint();
        while (true) {
            for (int i = 0; i < N; i++) {
                particles[i].move(0.1);
            }
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < N; i++) {
            g.drawOval((int) particles[i].getRx(), (int) particles[i].getRy(), (int)particles[i].getRadius(), (int)particles[i].getRadius());
            g.fillOval((int) particles[i].getRx(), (int) particles[i].getRy(), (int)particles[i].getRadius(), (int)particles[i].getRadius());
        }
    }
}
