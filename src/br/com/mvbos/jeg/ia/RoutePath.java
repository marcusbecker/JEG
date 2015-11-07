/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvbos.jeg.ia;

/**
 *
 * @author mbecker
 */
public class RoutePath {

    private float px;
    private float py;
    private RoutePath next;

    public RoutePath(float px, float py) {
        this(px, py, null);
    }

    public RoutePath(float px, float py, RoutePath next) {
        this.px = px;
        this.py = py;
        this.next = next;
    }

    public void change(float px, float py, RoutePath next) {
        this.px = px;
        this.py = py;
        this.next = next;
    }

    public void setNext(RoutePath next) {
        this.next = next;
    }

    public RoutePath getNext() {
        return next;
    }

    public float getPx() {
        return px;
    }

    public float getPy() {
        return py;
    }

    public void insertRoute(RoutePath r) {
        RoutePath n = this.getNext();

        this.setNext(r);
        r.setNext(n);
    }

    @Override
    public String toString() {
        return "RoutePath{" + "px=" + px + ", py=" + py + ", hasNext=" + (next != null) + '}';
    }

}
