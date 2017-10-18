package com.jjapp.ezmap;

import android.os.Environment;

import com.easymap.android.maps.v3.EzMap;
import com.easymap.android.maps.v3.geometry.GeoPoint;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.image.ImageResizeMode;
import com.facebook.react.views.image.ReactImageView;

/**
 * Created by yc-founder on 2017/10/16.
 */

public class RNEzMapManager extends SimpleViewManager<RNEzMapView> {

    private static final String REACT_CLASS="RNEzMapView";
    private static final  String LIC_PATH="/EzMap/lic/EzServiceClient4Android.lic";
    private ThemedReactContext aContext;
    public static EzMapLayerManager mapManager;
    public static RNEzMapView mapView;//地图容器

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
     * @return RNEzMapView
     */
    @Override
    protected RNEzMapView createViewInstance(ThemedReactContext reactContext) {
        aContext=reactContext;
        mapView=new RNEzMapView(reactContext);
        EzMapLayerManager.initFileDirectory();
        mapManager = new EzMapLayerManager();
        mapView.initLicenseAsDevelopement(Environment.getExternalStorageDirectory().getPath()+LIC_PATH);
        return mapView;
    }


}



