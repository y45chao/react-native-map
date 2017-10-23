package com.jjapp.ezmap;

import android.os.Environment;

/**
 * Created by yc-founder on 2017/10/19.
 */

public class Constants {
    /**
     * 地图文件主目录
     */
    public static final String EZMAP_BASE_PATH=Environment.getExternalStorageDirectory().getPath()+"/EzMap";
    /**
     * 地图许可文件目录
     */
    public static final String EZMAP_LIC_PATH= EZMAP_BASE_PATH+"/lic/EzServiceClient4Android.lic";
    /**
     * 离线矢量地图文件主目录
     */
    public static final String EZMAP_LXSL_NAME="mapsl";
    /**
     * 离线影像地图文件主目录
     */
    public static final String EZMAP_LXYX_NAME="mapyx";
    /**
     * 在线矢量地图文件主目录
     */
    public static final String EZMAP_ZXSL_NAME="mapslonline";
    /**
     * 在线影像地图文件主目录
     */
    public static final String EZMAP_ZXYX_NAME="mapyxonline";
    /**
     * 地图图层组
     */
    public static final String[] EZMAP_LAYER_GROUP={EZMAP_LXSL_NAME,EZMAP_LXYX_NAME,EZMAP_ZXSL_NAME,EZMAP_ZXYX_NAME};
}
