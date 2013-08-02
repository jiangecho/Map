package panda.android.Map.MapView;

import android.content.Context;
import android.widget.Toast;

/**
 * 地图标记类
 * @author gejw
 *
 */
public class MapMark extends MPoint{
	private Context mContext;
	private float width;
	private float height;
	private String title;

	public MapMark() {
	}

	/**
	 * @param context
	 * @param x
	 * @param y
	 */
	public MapMark(Context context, float x, float y) {
		this.mContext = context;
		this.x = x;
		this.y = y;
	}

	/**
	 * @param context
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public MapMark(Context context, float x, float y, float width,
			float height) {
		this.mContext = context;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * @param context
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param title
	 */
	public MapMark(Context context, float x, float y, float width,
			float height, String title) {
		this.mContext = context;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.title = title;
	}


	/**
	 * 判断是否点击到了
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isTouch(float touchX, float touchY, float scale,
			float mapLeft, float mapTop) {
		touchX = (touchX - mapLeft) / scale;
		touchY = (touchY - mapTop) / scale;
		if (touchX > x - width / 2 && touchX < x + width / 2
				&& touchY > y - height && touchY <= y) {
			return true;
		}
		return false;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
