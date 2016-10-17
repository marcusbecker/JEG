/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvbos.jeg.scene.impl;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.element.SelectorElement;
import br.com.mvbos.jeg.engine.GraphicTool;
import br.com.mvbos.jeg.window.Camera;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Marcus Becker
 */
public class DragAndDropPanel extends JPanel {

    protected enum SelectorMode {

        SELECTOR, HAND, SUB_SEL_0, SUB_SEL_1, SUB_SEL_2, SUB_SEL_3, SUB_SEL_4, SUB_SEL_5, SUB_SEL_6, SUB_SEL_7, SUB_SEL_8, SUB_SEL_9
    }

    private class MyDispatcher implements KeyEventDispatcher {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            setAltDown(e.isAltDown());
            setShiftDown(e.isShiftDown());
            setControlDown(e.isControlDown());

            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(DragAndDropPanel.this);

            if (topFrame != null && topFrame.getFocusOwner() == null) {
                return false;
            }

            if (isAltDown()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            } else {
                setCursor(Cursor.getDefaultCursor());
            }

            actionPreKeyEvent(e);

            //Ignore keys if table or textfield editions
            if (ignoreKey(e)) {
                return false;
            }

            if (e.getID() == KeyEvent.KEY_PRESSED) {
                keyPressed(e);

            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                keyReleased(e);

            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                keyTyped(e);
            }

            return false;
        }

    }

    private boolean altDown;
    private boolean shiftDown;
    private boolean controlDown;

    private ElementModel stageElement;
    private ElementModel mouseElement;
    private SelectorElement selectorElement;
    private final ElementModel[] selectedElements;

    private SelectorMode mode = SelectorMode.SELECTOR;

    private Camera camera;

    private MouseListener mouseListener;
    private KeyEventDispatcher keyEventDispatcher;

    private Point startDrag;
    private final Point mousePos = new Point();

    public DragAndDropPanel() {
        this(30);
    }

    public DragAndDropPanel(int elementsCount) {
        init();
        selectedElements = new ElementModel[elementsCount];
    }

    private void init() {
        camera = createCamera();
        createStageElement();
        createMouseElement();
        createSelectorElement();
        setBackground(new Color(235, 235, 235));

        createKeyEventDispatcher();
        addMouseListener(createMouseListener());
    }

    @Override
    protected void paintComponent(Graphics gg) {
        super.paintComponent(gg);

        Graphics2D g = (Graphics2D) gg;
        preDraw(g);
        drawDragElement(g);

        updatePreDraw(g);

        drawTime(g);

        drawSelector(g);
        drawSelectedElements(g);

        posDraw(g);
    }

    public ElementModel[] getSelectedElements() {
        return selectedElements;
    }

    protected KeyEventDispatcher createKeyEventDispatcher() {
        keyEventDispatcher = new MyDispatcher();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(keyEventDispatcher);

        return keyEventDispatcher;
    }

    public KeyEventDispatcher getKeyEventDispatcher() {
        return keyEventDispatcher;
    }

    public void setKeyEventDispatcher(KeyEventDispatcher keyEventDispatcher) {
        this.keyEventDispatcher = keyEventDispatcher;
    }

    protected MouseListener createMouseListener() {
        mouseListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                actionButton(e.getButton());

                if (e.getButton() == MouseEvent.BUTTON1) {
                    actionFirstButton(e);
                } else {
                    actionAnotherButton(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                actionMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (selectorElement.isEnabled()) {
                        actionSelectorRelease(e);

                    } else if (startDrag != null) {
                        actionDragRelease(e);
                    }

                    actionRelease(e);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mousePos.x = -1;
            }
        };

        return mouseListener;
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    protected void actionButton(int button) {
    }

    protected void actionFirstButton(MouseEvent e) {
        camera.fixPosition(mouseElement, e.getX(), e.getY());

        /*
         switch (mode) {
         case SELECTOR:
         selectElementOnStage(hasColision(mouseElement));
         return;
         case RELATION:
         addRelationship(hasColision(mouseElement));
         //return;
         case HAND:
         //return;
         default:;
         }*/
    }

    protected void actionAnotherButton(MouseEvent e) {
        //int button = e.getButton();
        selectorElement.setEnabled(false);
        singleSelection(null);
    }

    protected void actionMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            mouseElement.setPxy(e.getX() + camera.getCpx(), e.getY() + camera.getCpy());

            if (!isAltDown()) {

                if (!isValidSelecion(mouseElement)) {
                    selectorElement.setEnabled(true);
                    selectorElement.setPxy(e.getX(), e.getY());
                } else {
                    startDrag = e.getPoint();
                }

            } else /*if (EditTool.HAND == EditTool.SELECTOR)*/ {
                //camera.move(e.getX() - mousePos.x, getY() - mousePos.y);
            }
        }
    }

    protected void actionSelectorRelease(MouseEvent e) {
        selectorElement.adjustInvertSelection();
        selectorElement.setPx(selectorElement.getPx() + camera.getCpx());
        selectorElement.setPy(selectorElement.getPy() + camera.getCpy());

        singleSelection(null);

        /*
         for (ElementModel el : dbEntity.getTableList()) {

         if (GraphicTool.g().bcollide(el, selectorElement)) {
         //TODO create GraphicTool.g().populeArray(selectior, dbEntity.getTableList(), selectedElements)
         multiSelect(el);
         }
         }
         */
    }

    protected void actionDragRelease(MouseEvent e) {
        int npx = e.getPoint().x - startDrag.x;
        int npy = e.getPoint().y - startDrag.y;
        for (ElementModel el : selectedElements) {
            if (el == null) {
                break;
            }
            el.incPx(npx);
            el.incPy(npy);
        }
    }

    protected void actionRelease(MouseEvent e) {
        selectorElement.setEnabled(false);
        startDrag = null;
    }

    protected boolean isValidSelecion(ElementModel element) {
        for (ElementModel el : selectedElements) {
            if (el == null) {
                return false;
            }

            if (GraphicTool.g().bcollide(el, element)) {
                return true;
            }
        }

        return false;
    }

    public boolean isAltDown() {
        return altDown;
    }

    public void setAltDown(boolean altDown) {
        this.altDown = altDown;
    }

    public boolean isShiftDown() {
        return shiftDown;
    }

    public void setShiftDown(boolean shiftDown) {
        this.shiftDown = shiftDown;
    }

    public boolean isControlDown() {
        return controlDown;
    }

    public void setControlDown(boolean controlDown) {
        this.controlDown = controlDown;
    }

    public SelectorMode getMode() {
        return mode;
    }

    public void setMode(SelectorMode mode) {
        this.mode = mode;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public ElementModel getStageElement() {
        return stageElement;
    }

    public void setStageElement(ElementModel stageElement) {
        this.stageElement = stageElement;
    }

    public SelectorElement getSelectorElement() {
        return selectorElement;
    }

    public void setSelectorElement(SelectorElement selectorElement) {
        this.selectorElement = selectorElement;
    }

    public ElementModel getMouseElement() {
        return mouseElement;
    }

    public void setMouseElement(ElementModel mouseElement) {
        this.mouseElement = mouseElement;
    }

    private void createStageElement() {
        stageElement = new ElementModel() {
            @Override
            public void drawMe(Graphics2D g) {
                g.setColor(getColor());
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    private void createMouseElement() {
        mouseElement = new ElementModel(10, 10, "mouseElement");
    }

    private void createSelectorElement() {
        selectorElement = new SelectorElement("selector");
    }

    private Camera createCamera() {
        return Camera.c();
    }

    protected void preDraw(Graphics2D g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        //stageElement.setColor(btnCanvasColor.getBackground());
        //camera.draw(g, stageElement);
    }

    protected void drawDragElement(Graphics2D g) {
        g.setColor(getDragColor());

        if (startDrag != null) {
            int npx = mousePos.x - startDrag.x;
            int npy = mousePos.y - startDrag.y;
            for (ElementModel el : getSelectedElements()) {
                if (el == null) {
                    break;
                }

                g.drawRect(camera.fx(el.getPx() + npx), camera.fy(el.getPy() + npy), el.getWidth(), el.getHeight());
            }
        }
    }

    public Color getDragColor() {
        return Color.BLACK;
    }

    protected void updatePreDraw(Graphics2D g) {
        /*for (ElementModel el : dbEntity.getTableList()) {
         el.update();
         }*/
    }

    protected void drawTime(Graphics2D g) {
        /*for (ElementModel el : dbEntity.getTableList()) {
         camera.draw(g, el);
         }*/
    }

    protected void drawSelector(Graphics2D g) {
        selectorElement.drawMe(g);
        //drawRelationPointer(g);
    }

    protected void drawSelectedElements(Graphics2D g) {
        for (ElementModel el : selectedElements) {
            if (el == null) {
                break;
            }

            preDrawSingleElement(g, el);
            drawSingleElement(g, el);
            posDrawSingleElement(g, el);

        }
    }

    protected void preDrawSingleElement(Graphics2D g, ElementModel el) {
    }

    protected void drawSingleElement(Graphics2D g, ElementModel el) {
        camera.draw(g, el);
    }

    protected void posDrawSingleElement(Graphics2D g, ElementModel el) {
        final int border = selectorElement.getBorderSpace();
        g.setColor(Color.BLACK);
        g.drawRect(camera.fx(el.getPx() - border), camera.fy(el.getPy() - border), el.getWidth() + border * 2, el.getHeight() + border * 2);
    }

    protected void posDraw(Graphics2D g) {
        //Camera.c().draw(g, findElement);
    }

    protected void singleSelection(ElementModel el) {
        for (int i = 1; i < selectedElements.length; i++) {
            selectedElements[i] = null;
        }

        selectedElements[0] = el;
    }

    protected void multiSelect(ElementModel el) {
        for (int i = 0; i < selectedElements.length; i++) {

            if (selectedElements[i] == el) {
                break;
            }

            if (selectedElements[i] != null) {
                continue;
            }

            selectedElements[i] = el;
            break;
        }
    }

    protected boolean ignoreKey(KeyEvent e) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(DragAndDropPanel.this);
        Component c = topFrame.getFocusOwner();
        //return c instanceof JTable || c instanceof JTextComponent || c instanceof JList;
        return !(c instanceof JPanel);
    }

    protected void actionPreKeyEvent(KeyEvent e) {
        //FieldTableModel model = (FieldTableModel) tbFields.getModel();
        //model.disableEdition(isControlDown);
    }

    protected boolean isScrollCamKey(KeyEvent e) {
        return KeyEvent.VK_PAGE_DOWN == e.getKeyCode() || KeyEvent.VK_PAGE_UP == e.getKeyCode();
    }

    protected boolean isEscapeKey(KeyEvent e) {
        return KeyEvent.VK_ESCAPE == e.getKeyCode();
    }

    protected void keyPressed(KeyEvent e) {
        if (isScrollCamKey(e)) {
            scrollKeyPressed(e);
        } else if (isEscapeKey(e)) {
            mode = SelectorMode.SELECTOR;
            cancelKeyPressed();
        }
    }

    protected void scrollKeyPressed(KeyEvent e) {
        if (isControlDown()) {
            Camera.c().rollX(KeyEvent.VK_PAGE_DOWN == e.getKeyCode() ? 100 : -100);
        } else {
            Camera.c().rollY(KeyEvent.VK_PAGE_DOWN == e.getKeyCode() ? 100 : -100);
        }

        e.consume();
    }

    protected void cancelKeyPressed() {
        //cancelRelationship();
    }

    protected void keyReleased(KeyEvent e) {
        /*//System.out.println("e.getKeyCode() " + e.getKeyCode());
         if (107 == e.getKeyCode() || KeyEvent.VK_PLUS == e.getKeyCode()) {
         //scale += 0.1f;
         applyZoom(10);

         } else if (109 == e.getKeyCode() || KeyEvent.VK_MINUS == e.getKeyCode()) {
         //scale -= 0.1f;
         applyZoom(-10);

         } else if (KeyEvent.VK_EQUALS == e.getKeyCode()) {
         //scale = 1;
         applyZoom(0);

         } else if (KeyEvent.VK_DELETE == e.getKeyCode()) {
         removeSelectedTables();

         } else {
         //System.out.println(e.getKeyCode());
         }*/
    }

    protected void keyTyped(KeyEvent e) {
    }

}
