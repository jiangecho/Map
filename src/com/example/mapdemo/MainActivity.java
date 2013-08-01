package com.example.mapdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mapdemo.map.MapMark;
import com.example.mapdemo.map.MapMarkClickListenrer;
import com.example.mapdemo.map.MapView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MapView mapView = new MapView(this);
		List<MapMark> mapMarks = new ArrayList<MapMark>();
		try {
			Bitmap mapBitmap = BitmapFactory.decodeStream(getAssets().open(
					"map.png"));
			Bitmap markBitmap = BitmapFactory.decodeStream(getAssets().open(
					"mark.png"));
			Bitmap compassBitmap = BitmapFactory.decodeStream(getAssets().open(
					"compass.png"));

			mapMarks.add(new MapMark(this, 100, 100, markBitmap.getWidth(),
					markBitmap.getHeight()));
			mapMarks.add(new MapMark(this, 160, 160, markBitmap.getWidth(),
					markBitmap.getHeight()));
			mapMarks.add(new MapMark(this, 428, 170, markBitmap.getWidth(),
					markBitmap.getHeight(), "藏经楼"));
			// 加载标记坐标集
			mapView.setMapMarks(mapMarks);
			// 加载地图bmp
			mapView.setMapBitmap(mapBitmap);
			// 加载标记bmp
			mapView.setMarkBitmap(markBitmap);
			// 添加标记监听事件
			mapView.setMapMarkClickListenrer(new MapMarkClickListenrer() {

				@Override
				public void MapMarkClick(String str) {
					Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG)
							.show();
				}
			});
			setContentView(mapView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
