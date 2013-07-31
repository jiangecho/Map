package com.example.mapdemo.map;

/**
 * @author gejw
 *
 */
public class MapUtils {

	public static float DefaultWidth = 0;
	public static float DefaultHeight = 0;

	public static float CalcNewWidth(float newHeight) {
		return DefaultWidth * newHeight / DefaultHeight;
	}

	public static float CalcNewHeight(float newWidth) {
		return  (DefaultHeight * newWidth)/DefaultWidth;
	}
}
