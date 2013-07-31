package com.example.mapdemo.map;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MapView extends View {
	public enum TouchType {
		_NONE, _DRAG, _ZOOM;
	}

	public MapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MapView(Context context) {
		super(context);
		init(context);
	}

	// 触摸类型
	private TouchType touchType = TouchType._NONE;
	private Context mContext;
	// 地图的bmp
	private Bitmap mapBitmap;
	// 当前的缩放比例
	private float scale = 1.0f;
	// 最小的缩放比例
	private float minScale = 1.0f;
	// 最大的缩放比例
	private float maxScale = 1.0f;
	// 控件宽度
	private int viewWidth;
	// 控件高度
	private int viewHeight;
	// 地图bmp宽度
	private float mapWidth;
	// 地图bmp高度
	private float mapHeight;

	private int mapLeft;
	private int mapTop;

	private void init(Context context) {
		this.mContext = context;
		initPaint();
		setFocusable(true);
	}

	private Paint bmpPaint;

	private void initPaint() {
		bmpPaint = new Paint();
		bmpPaint.setAntiAlias(true);
	}

	private void initBitmap() {
		if (mapBitmap != null)
			return;
		try {
			mapBitmap = BitmapFactory.decodeStream(mContext.getAssets().open(
					"map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MapUtils.DefaultWidth = mapBitmap.getWidth();
		MapUtils.DefaultHeight = mapBitmap.getHeight();
		viewWidth = getWidth();
		viewHeight = getHeight();

		float ScaleW = viewWidth / MapUtils.DefaultWidth;
		float ScaleH = viewHeight / MapUtils.DefaultHeight;
		minScale = ScaleW > ScaleH ? ScaleW : ScaleH;
		maxScale = minScale * 4.0f;

		mapWidth = viewWidth;
		mapHeight = (int) MapUtils.CalcNewHeight(mapWidth);
		mapLeft = 0;
		mapTop = (int) ((viewHeight - mapHeight) / 2.0f);
	}

	public void Draw(Canvas canvas) {
		canvas.drawBitmap(mapBitmap, null, new Rect(mapLeft, mapTop,
				mapLeft+(int) mapWidth, mapTop + (int) mapHeight), bmpPaint);
	}

	private MapPoint last = new MapPoint();

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		float curX = event.getX();
		float curY = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchType = TouchType._DRAG;
			break;
		case MotionEvent.ACTION_MOVE:
			if (touchType == TouchType._DRAG) {
				// 移动
				mapLeft += (int) (curX - last.getX());
				mapTop += (int) (curY - last.getY());
				System.out.println(String.format("l = %s  t = %s", mapLeft,
						mapTop));
				last.set(curX, curY);
			}

			break;
		case MotionEvent.ACTION_UP:
			touchType = TouchType._NONE;
			last.set(curX, curY);
			break;
		default:
			break;
		}

		postInvalidate();
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(0xffffff);
		initBitmap();
		Draw(canvas);
	}

}
