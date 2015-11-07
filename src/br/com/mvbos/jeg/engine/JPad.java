/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvbos.jeg.engine;

import br.com.mvbos.jeg.scene.Click;

/**
 *
 * @author Marcus Becker
 */
public class JPad {

    private static JPad p1;

    private final Click click = new Click();

    public static JPad p1() {
        if (p1 == null) {
            p1 = new JPad();
        }

        return p1;
    }

    public void click(int x, int y) {
        click(x, y, 0, 1);
    }

    public void click(int x, int y, int button, int clickCount) {
        click.setClick(x, y, button, clickCount);
        click.setNewClick(true);
    }

    public Click getClick() {
        return click;
    }

}
