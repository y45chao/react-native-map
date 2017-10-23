package com.jjapp.ezmap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.easymap.android.maps.v3.EzMap;
import com.easymap.android.maps.v3.MapView;
import com.easymap.android.maps.v3.geometry.GeoPoint;
import com.easymap.android.maps.v3.graphics.BitmapDescriptor;
import com.easymap.android.maps.v3.graphics.BitmapDescriptorFactory;
import com.easymap.android.maps.v3.graphics.Marker;
import com.easymap.android.maps.v3.layers.GraphicsLayer;
import com.jjapp.MainActivity;
import com.jjapp.R;

public class EzMapMainActivity extends AppCompatActivity implements EzMap.OnStatusChangeListener {
    private EzMapLayerManager mapManager;
    private MapView mapView;//地图容器
    private TextView tv_jb;
    private TextView tv_position;
    private Button but_lxyxdt,but_lxsldt,but_zxyxdt,but_zxsldt;

    private GraphicsLayer gl;
    private BitmapDescriptor bd;
    private static String currentMapType;
    private static float currentMapLevel;
    private static double currentMapX = 114.92624,currentMapY = 25.83503;
    private static double collectX = 0, collectY = 0; //用户采集的坐标
    private static boolean isNeedReviewCollect = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      savedInstanceState  setContentView(R.layout.activity_ez_map_main);
//        EzMapLayerManager.initFileDirectory();
//        mapManager = new EzMapLayerManager();
//        mapView = (MapView)findViewById(R.id.MapView);
//        tv_jb = (TextView) findViewById(R.id.showtext_jb);
//        tv_position = (TextView) findViewById(R.id.showtext_position);
//        but_lxsldt = (Button) findViewById(R.id.but_sldt);
//        but_lxyxdt = (Button) findViewById(R.id.but_yxdt);
//        but_zxsldt = (Button) findViewById(R.id.but_sldt_online);
//        but_zxyxdt = (Button) findViewById(R.id.but_yxdt_online);
//
//        but_lxsldt.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                MapView mapView = (MapView)findViewById(R.id.MapView);
//                if(mapView!=null){
//                    final EzMap map = mapView.getMap();
//                    if(map!=null) currentMapType = mapManager.chooseToLxSldt(map);
//                }
//            }
//        });
//        but_lxyxdt.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                MapView mapView = (MapView)findViewById(R.id.MapView);
//                if(mapView!=null){
//                    final EzMap map = mapView.getMap();
//                    if(map!=null) currentMapType = mapManager.chooseToLxYxdt(map);
//                }
//            }
//        });
//        but_zxsldt.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                MapView mapView = (MapView)findViewById(R.id.MapView);
//                if(mapView!=null){
//                    final EzMap map = mapView.getMap();
//                    if(map!=null) currentMapType = mapManager.chooseToZxSldt(map);
//                }
//            }
//        });
//        but_zxyxdt.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                MapView mapView = (MapView)findViewById(R.id.MapView);
//                if(mapView!=null){
//                    final EzMap map = mapView.getMap();
//                    if(map!=null) currentMapType = mapManager.chooseToZxYxdt(map);
//                }
//            }
//        });
//        if(mapView!=null){
//            //加载授权文件。注意：所有厂商在调用时要一致用Environment.getExternalStorageDirectory().getPath()+"/EzMap/lic/EzServiceClient4Android.lic"
//            mapView.initLicenseAsDevelopement(Environment.getExternalStorageDirectory().getPath()+"/EzMap/lic/EzServiceClient4Android.lic");
//            // 注册地图准备就绪监听
//            mapView.setOnStatusChangeListener(this);
//            mapView.onCreate(savedInstanceState,new Bundle());
//        }
    }

    @Override
    public void onStatusChanged(STATUS status) {

        final EzMap map = mapView.getMap();
        if(map==null){
            return;
        }
        map.setMinZoomLevel(7);
        map.setMaxZoomLevel(19);
        map.setMyLocationControlVisible(false);
        //map.setScaleViewVisible(false);
        //map.setZoomControlsVisible(false);
        map.setCompassVisible(false);
        map.setTiltableByGesture(false);
        map.setRotatableByGesture(false);
        //map.setPointPickerVisible(true);

        mapManager.initLoadAllLayer(map);//初始化加载所有类型的地图

        if(currentMapType==null){//如果是首次进入该视窗
            currentMapType = mapManager.chooseToLxSldt(map);
            map.centerAt(new GeoPoint(currentMapX,currentMapY),false);
            map.zoomTo(10, false);
        }else{
            if(currentMapType.equals("lxsl")) mapManager.chooseToLxSldt(map);
            else if(currentMapType.equals("lxyx"))  mapManager.chooseToLxYxdt(map);
            else if(currentMapType.equals("zxsl"))  mapManager.chooseToZxSldt(map);
            else if(currentMapType.equals("zxyx"))  mapManager.chooseToZxYxdt(map);
            map.centerAt(new GeoPoint(currentMapX,currentMapY),false);
            map.zoomTo(currentMapLevel, false);
        }
        map.setOnMapChangeListener(new EzMap.OnMapChangeListener() {
            @Override
            public void onMapChanged(GeoPoint arg0, float zoom, float rotate, float tilt) {
                tv_jb.setText("当前级别:"+(int)map.getZoom());
                currentMapLevel = map.getZoom();
                currentMapX = arg0.getCoordinate().x;
                currentMapY = arg0.getCoordinate().y;
            }
        });
        map.setOnMapLongClickListener(new EzMap.OnMapLongClickListener(){
            @Override
            public void onMapLongClick(GeoPoint arg0) {
                recycleMarker(map);//清除之前的定位位置图标元素
                collectX = arg0.getCoordinate().x;
                collectY = arg0.getCoordinate().y;
                //在地图上添加新图标元素
                createMarker(map,collectX,collectY);
                tv_position.setText("当前采集位置  x:"+collectX+" y:"+collectY);
            }
        });

        //还原显示用户之前采集的点位
        if(isNeedReviewCollect){
            recycleMarker(map);//清除之前的定位位置图标元素
            createMarker(map,collectX,collectY);
            tv_position.setText("上次采集位置  x:"+collectX+" y:"+collectY);
            isNeedReviewCollect = false;
        }
    }

    /** 创建一个叠加元素，并显示在地图上 */
    private void createMarker(EzMap map,double x,double y){
        if(bd==null&&gl==null){
            bd = BitmapDescriptorFactory.fromAsset("A.png");
            Marker m = new Marker(new GeoPoint(x,y), bd);
            gl = new GraphicsLayer(map.getSpatialReference(),map.getEnvelopeOfVisibleMap());
            gl.addGraphic(m);
            gl.setMinLevel(7);
            gl.setMaxLevel(19);
            map.addLayer(gl);
        }
    }
    /** 回收叠加元素，并从地图上移除显示 */
    private void recycleMarker(EzMap map){
        if(gl!=null){
            gl.setVisible(false);
            gl.recycle();
            if(map!=null) map.removeLayer(gl);
        }
        if(bd!=null)bd.recycle();
        gl = null; bd = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(collectX>0&&collectY>0){
            isNeedReviewCollect = true;
        }
    }

    //     @Override
    //     public boolean onKeyDown(int keyCode, KeyEvent event) {
    //         if(keyCode == KeyEvent.KEYCODE_BACK){
    //
    //         }
    //         return false;
    //     }
    // private void exitDialog(){
    //     AlertDialog.Builder builder = new AlertDialog.Builder(EzMapMainActivity.this);
    //     builder.setMessage("确认要退出么？");
    //     builder.setTitle("提示");
    //     builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
    //         public void onClick(DialogInterface dialog, int which) {
    //             dialog.dismiss();
    //             finish();
    //             android.os.Process.killProcess(android.os.Process.myPid());
    //             System.exit(0);
    //         }
    //     });
    //     builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
    //         public void onClick(DialogInterface dialog, int which) {
    //             dialog.dismiss();
    //         }
    //     });
    //     builder.show();
    // }

}
