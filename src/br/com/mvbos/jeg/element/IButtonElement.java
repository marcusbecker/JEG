/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvbos.jeg.element;

/**
 *
 * @author mbecker
 */
public interface IButtonElement {

    public void onClick();

    public void onPress();

    public void onRelease();

    public void onFocus();

    public void onLoseFocus();
}
