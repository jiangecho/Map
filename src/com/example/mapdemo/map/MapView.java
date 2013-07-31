package com.example.mapdemo.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
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
	
	List<MapPoint> mapPoints = new ArrayList<MapPoint>();

	private ScaleGestureDetector mScaleDetector;

	private void init(Context context) {
		this.mContext = context;
		mScaleDetector = new ScaleGestureDetector(context,
				new ScaleGestureListener());
		setFocusable(true);
		initPaint();
		
		mapPoints.add(new MapPoint(100,100));
		mapPoints.add(new MapPoint(150,150));
		mapPoints.add(new MapPoint(200,200));
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
		minScale =scale= ScaleW > ScaleH ? ScaleH : ScaleW;
		maxScale = minScale * 4.0f;

		mapWidth = viewWidth;
		mapHeight = (int) MapUtils.CalcNewHeight(mapWidth);
		mapLeft = 0;
		mapTop = (int) ((viewHeight - mapHeight) / 2.0f);
	}

	public void Draw(Canvas canvas) {
		canvas.drawBitmap(mapBitmap, null, new Rect(mapLeft, mapTop, mapLeft
				+ (int) mapWidth, mapTop + (int) mapHeight), bmpPaint);
		drawPoint(canvas);
	}
	
	private void drawPoint(Canvas canvas) {
		for (int i = 0; i < mapPoints.size(); i++) {
			bmpPaint.setColor(Color.RED);
			canvas.drawCircle(mapLeft +scale* mapPoints.get(i).getX(),mapTop+ scale*mapPoints.get(i).getY(), 20, bmpPaint);
		}
	}

	private MapPoint last = new MapPoint();

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		float curX = event.getX();
		float curY = event.getY();
		mScaleDetector.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchType = TouchType._DRAG;
			last.set(curX, curY);
			break;
		case MotionEvent.ACTION_MOVE:
			if (touchType == TouchType._DRAG) {
				// 移动
				mapLeft += (int) (curX - last.getX());
				mapTop += (int) (curY - last.getY());

				checkRounds();
				last.set(curX, curY);
			}

			break;
		case MotionEvent.ACTION_UP:
			touchType = TouchType._NONE;
			last.set(mapLeft, mapTop);
			break;
		default:
			break;
		}

		postInvalidate();
		return true;
	}

	private void checkRounds() {
		// 检测上边界
		if (mapTop > 0)
			mapTop = 0;
		// 检测下边界
		if (mapTop + mapHeight < viewHeight)
			mapTop = (int) (viewHeight - mapHeight);
		// 检测左边界
		if (mapLeft > 0)
			mapLeft = 0;
		// 检测右边界
		if (mapLeft + mapWidth < viewWidth)
			mapLeft = (int) (viewWidth - mapWidth);

		if (mapHeight < viewHeight)
			mapTop = (int) ((viewHeight - mapHeight) / 2.0f);
	}

	public class ScaleGestureListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {

		MapPoint last = new MapPoint();

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scale *= detector.getScaleFactor();
			if (scale > maxScale) {
				scale = maxScale;
			} else if (scale < minScale) {
				scale = minScale;
			}

			mapWidth = MapUtils.DefaultWidth * scale;
			mapHeight = MapUtils.DefaultHeight * scale;

			mapLeft = (int) (detector.getFocusX() - detector.getScaleFactor()
					* (detector.getFocusX() - mapLeft));
			mapTop = (int) (detector.getFocusY() - detector.getScaleFactor()
					* (detector.getFocusY() - mapTop));
			checkRounds();
			return true;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			touchType = TouchType._ZOOM;
			last.set(detector.getFocusX(), detector.getFocusY());
			return true;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.parseColor("#DDE4E7"));
		initBitmap();
		Draw(canvas);
	}

}
