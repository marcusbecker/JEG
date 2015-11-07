package br.com.mvbos.jeg.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.engine.Engine;
import br.com.mvbos.jeg.scene.IScene;

public class LoadImpl {

    private int isLoading = -1;
    private int loadElementCount;
    private IScene scene;

    public boolean inLoad() {
        return isLoading != -1;
    }

    public void loadNext(Graphics2D g2d) {
        Engine.FREEZE = true;

        if (isLoading >= 0) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 16));
            g2d.setColor(Color.BLACK);

            if (isLoading == 0) {
                if (Engine.DEBUG_MODE) {
                    Engine.log(scene.toString());
                    Engine.log("start " + System.currentTimeMillis());
                }

                g2d.drawString("CARREGANDO CENARIO", getWidth(), getHeight());

                /*
                 * GraphicUtil.drawString((Graphics2D) g, GraphicUtil.BIG_FONT,
                 * "Carregando cenario", getWidth() / 3, getHeight() / 2);
                 */
                scene.startScene();
                isLoading++;

            } else if (isLoading == 1) {
                g2d.drawString("CARREGANDO PLANO DE FUNDO", getWidth(), getHeight());

                isLoading++;

            } else if (isLoading == 2 && loadElementCount < scene.getElements()[0].getElementCount()) {

                g2d.drawString("CARREGANDO ELEMENTOS", getWidth(), getHeight());

                ElementModel e = scene.getElements()[0].getByElement(loadElementCount++);
                e.loadElement();

            } else {
                isLoading = -1;
                Engine.FREEZE = false;
                if (Engine.DEBUG_MODE) {
                    Engine.log("end " + System.currentTimeMillis());
                }
            }
        }
    }

    private int getHeight() {
        return Engine.getIWindowGame().getWindowHeight() / 2;
    }

    private int getWidth() {
        return 15;
    }

    public void reload() {

    }

    public void reload(IScene scene) {
        isLoading = 0;
        loadElementCount = 0;
        this.scene = scene;
    }

}
