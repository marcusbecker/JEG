package br.com.mvbos.jeg.scene;

public class Pxy {

	public float x;
	public float y;

	public Pxy() {
		super();
	}

	public Pxy(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return (int) x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pxy other = (Pxy) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pxy [x=" + x + ", y=" + y + "]";
	}

}
