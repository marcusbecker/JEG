/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvbos.jeg.scene;

import br.com.mvbos.jeg.element.SelectorElement;

/**
 *
 * @author Marcus Becker
 */
public interface ISelectorScene {

    public void startSelection(SelectorElement selectorElement);

    public void endSelection(SelectorElement selectorElement);

}
