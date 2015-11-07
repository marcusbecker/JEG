/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvbos.jeg.ia;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.element.IBlockElement;
import br.com.mvbos.jeg.engine.GraphicTool;
import br.com.mvbos.jeg.window.IMemory;

/**
 *
 * @author mbecker
 */
public class AIUtil {

    public static int MAX_ROUTES = 60;
    private static float px, py, dif;

    public static float getDistance(ElementModel eA, ElementModel eB) {
        return getDistance(eA.getPpx(), eA.getPpy(),
                eB.getPpx(), eB.getPpy());
    }

    public static float getDistance(float x1, float y1, float x2, float y2) {
        x1 -= x2;
        y1 -= y2;
        return (float) Math.sqrt(x1 * x1 + y1 * y1);
    }

    public static RoutePath getSimpleRoute(ElementModel el, float nextX, float nextY, IMemory memory) {
        px = el.getPpx();//elPx
        py = el.getPpy();//elPy

        boolean incX = px < nextX;
        boolean incY = py < nextY;

        int pmov = el.getWidth();

        ElementModel checkElement = new ElementModel(el.getWidth(), el.getHeight(), null);

        RoutePath route = new RoutePath(px, py, null);
        RoutePath lastRoute = null;

        boolean end = false;

        for (int i = 0; i < MAX_ROUTES; i++) {
            dif = 0;

            if (incX && px < nextX) {
                dif = (px + pmov >= nextX ? nextX : px + pmov);
                px = dif;
            } else if (!incX && px > nextX) {
                dif = (px - pmov <= nextX ? nextX : px - pmov);
                px = dif;
            } else {
                px = nextX;
            }

            if (incY && py < nextY) {
                dif = (py + pmov >= nextY ? nextY : py + pmov);
                py = dif;
            } else if (!incY && py > nextY) {
                dif = (py - pmov <= nextY ? nextY : py - pmov);
                py = dif;
            } else {
                py = nextY;
            }

            if (dif == 0) {
                break;
            }

            checkElement.setPxy(nextX, nextY);

            ElementModel o = GraphicTool.g().collide(checkElement, memory);
            if (o != null && o != el && o instanceof IBlockElement) {
                return null;
            }

            checkElement.setPxy(px, py);
            o = GraphicTool.g().collide(checkElement, memory);

            if (o != null && o != el && o instanceof IBlockElement) {

                boolean cldInX;

                if (o.getWidth() != o.getHeight()) {
                    cldInX = o.getWidth() > o.getHeight();
                } else {
                    float ax = nextX - el.getPx();
                    ax = ax < 0 ? ax * -1 : ax;
                    float ay = nextY - el.getPy();
                    ay = ay < 0 ? ay * -1 : ay;

                    cldInX = ax > ay;

                    //GlobalEngine.log("ax ", ax);
                    //GlobalEngine.log("ay  ", ay);
                    //GlobalEngine.log("cldInX  ", cldInX);
                }

                int _tempX, _tempY;

                boolean goUpLft;

                if (cldInX) {
                    goUpLft = false;
                    if (nextX < o.getPx()) {
                        goUpLft = true;
                    } else if (o.getPx() < el.getPx() && o.getAllWidth() > el.getAllWidth()) {
                        if (el.getPx() - o.getPx() < o.getAllWidth() - el.getAllWidth()) {
                            goUpLft = true;
                        }
                    }

                    _tempX = goUpLft ? o.getPx() - el.getWidth() : o.getAllWidth();
                    _tempY = incY ? o.getPy() - el.getHeight() : o.getPy() + el.getHeight();

                    if (_tempX < 0 || _tempY < 0) {
                        return route;
                    }

                } else {
                    goUpLft = false;
                    if (nextY < o.getAllHeight() && el.getPy() < (o.getPy() + 10)) {
                        goUpLft = true;
                    }

                    _tempX = incX ? o.getPx() - el.getWidth() : o.getPx() + el.getWidth();
                    _tempY = goUpLft ? o.getPy() - el.getHeight() : o.getAllHeight();

                    if (_tempX < 0 || _tempY < 0) {
                        return route;
                    }
                }

                if (lastRoute == null) {
                    lastRoute = new RoutePath(_tempX, _tempY, null);
                    route.setNext(lastRoute);

                } else {
                    lastRoute.change(_tempX, _tempY, null);
                }

                if (end) {
                    return route;
                }

                if (cldInX) {
                    _tempY = incY ? o.getPy() + el.getHeight() : o.getPy() - el.getHeight();
                    //GlobalEngine.log("_tempY ", _tempY);
                } else {
                    _tempX = incX ? o.getAllWidth() + el.getWidth() / 2 : o.getPx() - el.getWidth();
                    //GlobalEngine.log("_tempX ", _tempX);
                }
                lastRoute.setNext(new RoutePath(_tempX, _tempY, null));
                lastRoute.getNext().setNext(new RoutePath(nextX, nextY, null));
                lastRoute = lastRoute.getNext().getNext();

                px = _tempX;
                py = _tempY;

            } else {
                if (lastRoute != null) {
                    lastRoute.change(px, py, lastRoute.getNext());
                } else {
                    route.change(px, py, route.getNext());
                }
            }
        }

        return route;
    }
}
