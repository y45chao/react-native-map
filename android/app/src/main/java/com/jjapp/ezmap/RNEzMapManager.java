package com.jjapp.ezmap;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.easymap.android.maps.v3.EzMap;
import com.easymap.android.maps.v3.MapView;
import com.easymap.android.maps.v3.geometry.GeoPoint;
import com.easymap.android.maps.v3.geometry.SpatialReference;
import com.easymap.android.maps.v3.layers.GraphicsLayer;
import com.easymap.android.maps.v3.layers.ezmap.EzMapVMLayer;
import com.easymap.android.maps.v3.layers.ogc.WMTSLayer;
import com.easymap.android.maps.v3.layers.ogc.WMTSLayerInfo;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.jjapp.MainApplication;


/**
 * Created by yc-founder on 2017/10/16.
 */

public class RNEzMapManager extends SimpleViewManager<MapView> implements EzMap.OnStatusChangeListener {

    private static final String REACT_CLASS="RNEzMapView";
    private static final  String LIC_PATH="/EzMap/lic/EzServiceClient4Android.lic";
    private ThemedReactContext aContext;
    public static MapView mapView;//地图容器
    private MainApplication application;
    private EzMap ezMap;
    private GraphicsLayer graphicsLayer;
    WMTSLayer wmtstdt;
    EzMapVMLayer vmLayer;
    RNEzMapEntity  ezMapEntity;
    /**
     * 设置Js引用名
     * @return String
     */
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    /**
     * 创建UI组件实例
     * @param reactContext
     * @return MapView
     */
    @Override
    protected MapView createViewInstance(ThemedReactContext reactContext) {
        aContext=reactContext;
        mapView = new MapView(reactContext);
        mapView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        application = (MainApplication) reactContext.getCurrentActivity().getApplication();
        if (mapView != null) {
            //初始化许可
            mapView.initLicenseAsDevelopement(Constants.EZMAP_LIC_PATH+"/"+Constants.EZMAP_LIC_FILE);
            // 注册地图准备就绪监听
            mapView.setOnStatusChangeListener(this);
            mapView.onCreate(application,new Bundle());
        }

        mapView.setOnStatusChangeListener(this);
        mapView.onResume();
        return mapView;
    }


    @Override
    public void onStatusChanged(STATUS status) {
        //得到地图对象
        ezMap = mapView.getMap();
        //设置地图中心点
        ezMap.centerAt(new GeoPoint(116.305383164063, 39.9830441051801), false);
        //设置地图级别
        ezMap.zoomTo(15, false);
        //————————————————————————————————————————————————初始化wmts图层————————————————————————————————————————————
        //————————————————————————————————————————————————————————————————————————————————————————————————————————
        //设置图层info
        WMTSLayerInfo wmtslayerinfo = new WMTSLayerInfo();
        wmtslayerinfo.setVersion("1.0.0");
        wmtslayerinfo.setFormat("tiles");
        wmtslayerinfo.setStyle("default");
        wmtslayerinfo.setLayerName("vec");
        wmtslayerinfo.setTileMatrixSet("c");
        // 实例化WMTSLayer 地图
        wmtstdt = new WMTSLayer("http://t0.tianditu.com/vec_c/wmts",
                wmtslayerinfo, "tdt_vec.cache", Environment.getExternalStorageDirectory()
                + "/EzMap/st");
        wmtstdt.setVisible(true);
        //加载wmts地图图层
        ezMap.addLayer(wmtstdt);
        //————————————————————————————————————————————————初始化矢量图层————————————————————————————————————————————
        //———————————————————————————————————————————————————————————————————————————————————————————————————————
        vmLayer = new EzMapVMLayer("http://sanqiang.laohubaodian.cn:8080/quantum/string", null, Environment.getExternalStorageDirectory() + "/yt");
        //加载矢量地图图层
        vmLayer.setVisible(false);
        ezMap.addLayer(vmLayer);


        graphicsLayer = new GraphicsLayer(SpatialReference.create(4326), ezMap.getExtent());
        //添加图层,可以通过  getLayer(100); 获得
        ezMap.addLayer(graphicsLayer);
        ezMap.setOnMapClickListener(new EzMap.OnMapClickListener() {
            @Override
            public void onMapClick(GeoPoint geoPoin) {
                Log.e("onMap","onMapClick");            }
        });

        ezMapEntity.setMapView(mapView);
        ezMapEntity.setGraphicsLayer(graphicsLayer);
        ezMapEntity.setWmtsLayer(wmtstdt);
        ezMapEntity.setEzMapVMLayer(vmLayer);
        Log.e("mapview","application -onstatus");
    }

}



