package br.com.mvbos.jeg.element;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import br.com.mvbos.jeg.engine.Engine;
import java.io.Serializable;

/**
 *
 * @author Marcus Becker
 *
 */
public class ElementModel implements Serializable {

    public static final String EMPTY = "";
    protected int id;
    protected float pX;
    protected float pY;
    protected int width;
    protected int height;
    protected String name = EMPTY;
    protected boolean visible = true;
    protected boolean enabled = true;
    protected boolean destroyed = false;
    protected ImageIcon image;
    protected Color color = Color.BLUE;

    public void loadElement() {
        Engine.log("Nothing to load.");
    }

    public ElementModel() {
    }

    public ElementModel(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public ElementModel(float positionX, float positionY, int width, int height,
            String name) {
        this.pX = positionX;
        this.pY = positionY;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPx() {
        return (int) pX;
    }

    public void setPx(float positionX) {
        this.pX = positionX;
    }

    public void incPx(float positionX) {
        this.pX += positionX;
    }

    public int getPy() {
        return (int) pY;
    }

    public void setPy(float positionY) {
        this.pY = positionY;
    }

    public void incPy(float positionY) {
        this.pY += positionY;
    }

    public void setPxy(float x, float y) {
        this.pX = x;
        this.pY = y;
    }

    public void drawMe(Graphics2D g) {
        if (!isVisible()) {
            return;
        }

        if (isValidImage()) {
            g.drawImage(getImage().getImage(), getPx(), getPy(), null);

        } else {
            if (Engine.isPaused()) {
                g.setColor(Color.RED);

            } else {
                g.setColor(getColor());
            }

            g.drawRect(getPx(), getPy(), getWidth(), getHeight());

        }
    }

    public void drawBorders(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawRect(getPx(), getPy(), getWidth(), getHeight());

        if (getImage() != null) {
            g.setColor(Color.GREEN);
            g.drawRect(getPx(), getPy(), getImage().getIconWidth(),
                    getImage().getIconHeight());
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void show(boolean s) {
        this.visible = s;
        this.enabled = s;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;

        if (isValidImage()) {
            setSize(image.getIconWidth(), image.getIconHeight());
        }
    }

    public void update() {
    }

    public int getAllWidth() {
        return getPx() + getWidth();
    }

    public int getAllHeight() {
        return getPy() + getHeight();
    }

    public float getAllSize() {
        return getPx() + getWidth() + getPy() + getHeight();
    }

    public float getCenterX() {
        return getPx() + (getWidth() / 2);
    }

    public float getCenterY() {
        return getPy() + (getHeight() / 2);
    }

    @Override
    public String toString() {
        return "ElementModel{" + "positionX=" + pX + ", positionY=" + pY
                + ", width=" + width + ", height=" + height + ", name=" + name
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ElementModel other = (ElementModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name
                .equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /*
     * public Rectangle getBounds() { return new Rectangle((int) getPositionX(),
     * (int) getPositionY(), getWidth(), getHeight()); }
     */
    public void destroy() {
        visible = false;
        enabled = false;
        destroyed = true;
    }

    public void setSize(int w, int h) {
        setWidth(w);
        setHeight(h);
    }

    /**
     * <p>
     * Used to notification that this Element can be replaced.
     * </p>
     * <p>
     * Usado para dizer que este Element pode ser substituido
     * </p>
     *
     * @return
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void setAll(int x, int y, int w, int h) {
        setSize(w, h);
        setPxy(x, y);
    }

    /**
     * Os metodos hitX, hitY, hitWidth, hitHeight sao usado no detector de
     * colisao para detectar colisao nos elementos independente do tamanho e
     * posicao origianl
     *
     * @return
     */
    public float getHitX() {
        return getPx();
    }

    public float getHitY() {
        return getPy();
    }

    public int getHitWidth() {
        return getWidth();
    }

    public int getHitHeight() {
        return getHeight();
    }

    /**
     * Define a largura e altura do elemento igual a largura e altura da imagem
     */
    public void copyImgeSize() {
        if (getImage() != null) {
            setSize(getImage().getIconWidth(), getImage().getIconHeight());
        }
    }

    /**
     * Define a imagem e seta os valores de largura e altura com base nas
     * dimensoes da imagem
     */
    public void setImageAndSize(ImageIcon image) {
        setImage(image);
        copyImgeSize();
    }

    public int getHalfWidth() {
        return getWidth() / 2;
    }

    public int getHalfHeight() {
        return getHeight() / 2;
    }

    public boolean isValidImage() {
        return image != null && image.getImage() != null;
    }

}
