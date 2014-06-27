/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particlecollidingsimulation;

import java.util.Random;

/**
 *
 * @author Ruchiranga
 */
public class Particle {

    private double rx, ry;
    private double vx, vy;
    private final double radius;
    private final double mass;
    private int count;

    public Particle() {
        radius =mass = 4;
        Random input = new Random();
        rx = input.nextInt(400 - 2 * (int) radius) + radius;
        ry = input.nextInt(400 - 2 * (int) radius) + radius;
        while (vx == 0 || vy == 0) {
            vy = input.nextInt(10) - 5;
            vx = input.nextInt(10) - 5;
        }


    }

    public Particle(double rx, double ry, double vx, double vy, double radius) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        mass=4;
    }

    public void move(double dt) {
        if (getRx() + getVx() * dt > 400 - getRadius() || getRx() + getVx() * dt < radius) {
            vx = -getVx();
        }
        if (getRy() + getVy() * dt > 400 - getRadius() || getRy() + getVy() * dt < radius) {
            vy = -getVy();
        }
        rx += getVx() * dt;
        ry += getVy() * dt;
    }

    /**
     * @return the rx
     */
    public double getRx() {
        return rx;
    }

    /**
     * @return the ry
     */
    public double getRy() {
        return ry;
    }

    /**
     * @return the vx
     */
    public double getVx() {
        return vx;
    }

    /**
     * @return the vy
     */
    public double getVy() {
        return vy;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    public double timeToHit(Particle that){
        if(this==that) return -1;
        double dx=that.rx-this.rx;
        double dy=that.ry-this.ry;
        double dvx=that.vx-this.vx;
        double dvy=that.vy-this.vy;
        double dvdr=dx*dvx+dy*dvy;
        if(dvdr>0) return -1;
        double dvdv=dvx*dvx+dvy*dvy;
        double drdr=dx*dx+dy*dy;
        double sigma=this.radius+that.radius;
        double d=(dvdr*dvdr)-dvdv*(drdr-sigma*sigma);
        if(d<0)return -1;
        return -(dvdr+Math.sqrt(d))/dvdv;
    }
    public double timeToHitHorizontalWall(){
        return 0;
    }
    public double timeToHiVerticalWall(){
        return 0;
    }

    public void bounceOff(Particle that){
        double dx=that.rx-this.rx;
        double dy=that.ry-this.ry;
        double dvx=that.vx-this.vx;
        double dvy=that.vy-this.vy;
        double dvdr=dx*dvx+dy*dvy;
        double dist=this.radius+that.radius;
        double J=2*this.mass*that.mass*dvdr/((this.mass+that.mass)*dist);
        double Jx=J*dx/dist;
        double Jy=J*dy/dist;
        this.vx+=Jx/this.mass;
        this.vy+=Jy/this.mass;
        this.vx-=Jx/this.mass;
        this.vy-=Jy/this.mass;
        this.count++;
        that.count++;
    }
    public void bounceOffVerticalWall(){

    }
    public void bounceOffHorizontalWall(){

    }
}
