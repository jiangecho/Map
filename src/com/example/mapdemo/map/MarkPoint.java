package com.example.mapdemo.map;

public class MarkPoint {
	private float x;
	private float y;

	public MarkPoint() {
	}

	public MarkPoint(float x, float y) {
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

	public boolean isTouch(float x, float y) {
		return true;
	}
}
