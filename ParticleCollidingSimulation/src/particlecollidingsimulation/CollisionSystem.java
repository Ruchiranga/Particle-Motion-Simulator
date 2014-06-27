/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package particlecollidingsimulation;

import java.util.PriorityQueue;

/**
 *
 * @author Ruchiranga
 */
public class CollisionSystem {
    private PriorityQueue<Event> pq;
    private double t=0.0;
    private Particle[] particles;

    public CollisionSystem(Particle[] particles) {
        //this.particles = particles;
    }
    private void predict(Particle a){
        if(a==null) return;
        for (int i = 0; i < particles.length; i++) {
            double dt=a.timeToHit(particles[i]);
            pq.add(new Event(t+dt, a, particles[i]));
        }
        pq.add(new Event(t+a.timeToHiVerticalWall(), a,null));
        pq.add(new Event(t+a.timeToHitHorizontalWall(), null,a));
    }
    private void reDraw(){
        
    }
    public void simulate(){
        pq=new PriorityQueue<Event>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i]);
        }
        pq.add(new Event(0, null, null));
        while(!pq.isEmpty()){
            Event event=pq.poll();
            if(!event.isValid()) continue;
            Particle a=event.a;
            Particle b=event.b;

            for (int i = 0; i < particles.length; i++) {
                particles[i].move(event.time-t);
            }
            t=event.time;

            if(a!=null&&b!=null) a.bounceOff(b);
            if(a!=null&&b==null) a.bounceOffVerticalWall();
            if(a==null&&b!=null) a.bounceOffHorizontalWall();
            if(a==null&&b==null) reDraw();

            predict(a);
            predict(b);

        }
    }
}
