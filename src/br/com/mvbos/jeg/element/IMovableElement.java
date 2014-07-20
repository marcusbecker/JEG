package br.com.mvbos.jeg.element;

/**
 * 
 * @author Marcus Becker
 */
public interface IMovableElement {

	public int getInitPositionX();

	public void setInitPositionX(int initPositionX);

	public int getInitPositionY();

	public void setInitPositionY(int initPositionY);

	public int getLastPositionX();

	public void setLastPositionX(int lastPositionX);

	public int getLastPositionY();

	public void setLastPositionY(int lastPositionY);
}
