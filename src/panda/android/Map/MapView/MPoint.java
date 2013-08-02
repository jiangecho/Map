package panda.android.Map.MapView;

/**
 * 坐标点
 * @author gejw
 *
 */
public class MPoint {
	private float x;
	private float y;

	public MPoint() {
	}

	public MPoint(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
