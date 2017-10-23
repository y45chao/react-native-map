package com.jjapp.ezmap;

import android.app.Activity;

import com.easymap.android.maps.v3.MapView;
import com.easymap.android.maps.v3.layers.GraphicsLayer;
import com.easymap.android.maps.v3.layers.ezmap.EzMapVMLayer;
import com.easymap.android.maps.v3.layers.ogc.WMTSLayer;
import com.jjapp.MainApplication;

/**
 * Created by yc-founder on 2017/10/19.
 */

public class RNEzMapEntity {
    private static MapView mapView;

    private static GraphicsLayer graphicsLayer;

    private static WMTSLayer wmtsLayer;

    private static EzMapVMLayer ezMapVMLayer;

    private static Activity activity;

    public static MapView getMapView() {
        return mapView;
    }

    public static void setMapView(MapView mapView) {
        RNEzMapEntity.mapView = mapView;
    }

    public static GraphicsLayer getGraphicsLayer() {
        return graphicsLayer;
    }

    public static void setGraphicsLayer(GraphicsLayer graphicsLayer) {
        RNEzMapEntity.graphicsLayer = graphicsLayer;
    }
    public static WMTSLayer getWmtsLayer() {
        return wmtsLayer;
    }

    public static void setWmtsLayer(WMTSLayer wmtsLayer) {
        RNEzMapEntity.wmtsLayer = wmtsLayer;
    }

    public static EzMapVMLayer getEzMapVMLayer() {
        return ezMapVMLayer;
    }

    public static void setEzMapVMLayer(EzMapVMLayer ezMapVMLayer) {
        RNEzMapEntity.ezMapVMLayer = ezMapVMLayer;
    }
    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(Activity activity) {
        RNEzMapEntity.activity = activity;
    }
    public static void setVMVisibility(boolean b) {
        RNEzMapEntity.ezMapVMLayer.setVisible(b);
        RNEzMapEntity.wmtsLayer.setVisible(!b);
    }
}
