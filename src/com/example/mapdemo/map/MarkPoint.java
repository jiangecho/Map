package com.example.mapdemo.map;

import android.content.Context;
import android.widget.Toast;

public class MarkPoint {
	private Context mContext;
	private float x;
	private float y;
	private float width;
	private float height;
	private String title;

	public MarkPoint() {
	}

	public MarkPoint(Context context, float x, float y) {
		this.mContext = context;
		this.x = x;
		this.y = y;
	}

	public MarkPoint(Context context, float x, float y, float width,
			float height) {
		this.mContext = context;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public MarkPoint(Context context, float x, float y, float width,
			float height, String title) {
		this.mContext = context;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.title = title;
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
			Toast.makeText(mContext, title, Toast.LENGTH_LONG).show();
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
