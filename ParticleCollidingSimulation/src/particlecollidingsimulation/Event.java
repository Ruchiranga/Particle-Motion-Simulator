/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package particlecollidingsimulation;

/**
 *
 * @author Ruchiranga
 */
public class Event implements Comparable<Event>{
    double time;
    Particle a,b;
    private int countA,countB;

    public Event(double t, Particle a, Particle b) {
        this.time = time;
        this.a = a;
        this.b = b;
        this.countA = countA;
        this.countB = countB;
    }



    public int compareTo(Event that) {
        return (int) (this.time - that.time);
    }
    public boolean isValid(){
        return false;
    }

}
