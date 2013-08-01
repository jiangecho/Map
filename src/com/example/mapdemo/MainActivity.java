package com.example.mapdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.mapdemo.map.MapMark;
import com.example.mapdemo.map.MapView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;

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

			mapMarks.add(new MapMark(this, 100, 100, markBitmap.getWidth(),
					markBitmap.getHeight()));
			mapMarks.add(new MapMark(this, 160, 160, markBitmap.getWidth(),
					markBitmap.getHeight()));
			mapMarks.add(new MapMark(this, 428, 170, markBitmap.getWidth(),
					markBitmap.getHeight(), "藏经楼"));
			mapView.setMapMarks(mapMarks);
			mapView.setMapBitmap(mapBitmap);
			mapView.setMarkBitmap(markBitmap);
			setContentView(mapView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
