模仿景点通的一个导航地图功能   可以点击地图某点  实现跳转

使用：
    		
    		MapView mapView = new MapView(this);
    		List<MapMark> mapMarks = new ArrayList<MapMark>();
			
			Bitmap mapBitmap = BitmapFactory.decodeStream(getAssets().open(
					"map.png"));
			Bitmap markBitmap = BitmapFactory.decodeStream(getAssets().open(
					"mark.png"));

			// 添加坐标点 (坐标点原则：根据地图的原始大小，对应的坐标  代码会自动适配)
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
				public void MapMarkClick(MapMark mark) {
					//返回的为坐标信息
					Toast.makeText(MainActivity.this, mark.getTitle(), Toast.LENGTH_LONG)
							.show();
				}
			});
            
            