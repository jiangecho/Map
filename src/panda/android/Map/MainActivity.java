package panda.android.Map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import panda.android.Map.MapView.MapMark;
import panda.android.Map.MapView.MapMarkClickListenrer;
import panda.android.Map.MapView.MapView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			MapView mapView = new MapView(this);
			List<MapMark> mapMarks = new ArrayList<MapMark>();
			
			Bitmap mapBitmap = BitmapFactory.decodeStream(getAssets().open(
					"map.png"));
			Bitmap markBitmap = BitmapFactory.decodeStream(getAssets().open(
					"mark.png"));

			// 添加坐标点
			mapMarks.add(new MapMark(this, 158, 197, markBitmap.getWidth(),
					markBitmap.getHeight(),"云栖塔院"));
			mapMarks.add(new MapMark(this, 314, 234, markBitmap.getWidth(),
					markBitmap.getHeight(),"玉佛楼"));
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
				public void MapMarkClick(MapMark mark) {
					//返回的为坐标信息
					Toast.makeText(MainActivity.this, mark.getTitle(), Toast.LENGTH_LONG)
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
