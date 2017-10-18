package com.jjapp.ezmap;

import android.os.Environment;
import android.util.Log;

import com.easymap.android.maps.v3.EzMap;
import com.easymap.android.maps.v3.layers.Layer;
import com.easymap.android.maps.v3.layers.ogc.WMTSLayer;
import com.easymap.android.maps.v3.layers.ogc.WMTSLayerInfo;

import java.io.File;

/**
 * Created by yc-founder on 2017/10/16.
 */

public class EzMapLayerManager {


    private Layer lxsl = null;//离线矢量
    private Layer lxyx = null;//离线影像
    private Layer[] zxsl = null;//在线矢量
    private Layer[] zxyx = null;//在线影像

    /**
     * 【注意：】
     * 创建离线地图所需要的目录，第三方在使用离线地图SDK时也要使用该目录，为方便后期地图自动更新，请不要使用其它路径目录。
     */
    public static void initFileDirectory(){
        Log.d("debug", "initFileDirectory >>>>>");
        File f = new File(Environment.getExternalStorageDirectory().getPath()+"/EzMap");//离线地图包主目录
        File f_lic = new File(Environment.getExternalStorageDirectory().getPath()+"/EzMap/lic");//离线地图包授权文件目录
        File f_sl = new File(Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapsl");//离线矢量地图包目录
        File f_yx = new File(Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapyx");//离线影像地图包目录
        File f_sl_online = new File(Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapslonline");//在线矢量地图包目录
        File f_yx_online = new File(Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapyxonline");//在线影像地图包目录
        File[] allFile = {f,f_lic,f_sl,f_yx,f_sl_online,f_yx_online};
        for(File a:allFile){
            if(!a.exists()&&!a.isDirectory()){
                Log.d("debug", "create Directory "+a.getAbsolutePath());
                a.mkdirs();
            }
        }
    }

    /**
     * 初始化加载所有类型的地图.一次性加载所有种类地图，便于后面在切换地图时效果更流畅
     * @param map
     */
    public void initLoadAllLayer(EzMap map){
        if(lxsl==null) lxsl = addLxWmtsLayer(map,"lxsl",7,19);
        if(lxyx==null) lxyx = addLxWmtsLayer(map,"lxyx",7,19);
        if(zxsl==null) zxsl = addZxWmtsLayer(map,"zxsl");
        if(zxyx==null) zxyx = addZxWmtsLayer(map,"zxyx");
    }

    /**
     * 切换到离线矢量地图
     */
    public String chooseToLxSldt(EzMap map){
        Log.d("debug", "切换到离线矢量地图 >>>>>");
        if(lxsl==null)lxsl = addLxWmtsLayer(map,"lxsl",7,19);
        showMapLayer(lxsl);
        hiddenMapLayer(lxyx);
        hiddenMapLayer(zxsl);
        hiddenMapLayer(zxyx);
        map.refreshMap();
        return "lxsl";
    }
    /**
     * 切换到离线影像地图
     */
    public String chooseToLxYxdt(EzMap map){
        Log.d("debug", "切换到离线影像地图 >>>>>");
        if(lxyx==null)lxyx = addLxWmtsLayer(map,"lxyx",7,19);
        hiddenMapLayer(lxsl);
        showMapLayer(lxyx);
        hiddenMapLayer(zxsl);
        hiddenMapLayer(zxyx);
        map.refreshMap();
        return "lxyx";
    }

    /**
     * 切换到在线矢量地图
     */
    public String chooseToZxSldt(EzMap map){
        Log.d("debug", "切换到在线矢量地图 >>>>>");
        if(zxsl==null)zxsl = addZxWmtsLayer(map,"zxsl");
        hiddenMapLayer(lxsl);
        hiddenMapLayer(lxyx);
        showMapLayer(zxsl);
        hiddenMapLayer(zxyx);
        map.refreshMap();
        return "zxsl";
    }

    /**
     * 切换到在线影像地图
     */
    public String chooseToZxYxdt(EzMap map){
        Log.d("debug", "切换到在线影像地图 >>>>>");
        if(zxyx==null)zxyx = addZxWmtsLayer(map,"zxyx");
        hiddenMapLayer(lxsl);
        hiddenMapLayer(lxyx);
        hiddenMapLayer(zxsl);
        showMapLayer(zxyx);
        map.refreshMap();
        return "zxyx";
    }

    //添加离线地图图层
    private WMTSLayer addLxWmtsLayer(EzMap map,String layerType,int minLevel, int maxLevel ){
        if(layerType.equals("lxsl")){
            //注意：离线地图包存放的目录要求所有厂商在调用时要一致用Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapsl"
            WMTSLayer lyr = createWMTSLayer(map,null,"layer0","lxsl_cache.tmp",Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapsl",minLevel,maxLevel);
            return lyr;
        }else if(layerType.equals("lxyx")){
            //注意：离线地图包存放的目录要求所有厂商在调用时要一致用Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapyx"
            WMTSLayer lyr = createWMTSLayer(map,null,"layer0","lxyx_cache.tmp",Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapyx",minLevel,maxLevel);
            return lyr;
        }
        Log.e("error", "调用添加离线地图图层方法时参数传递错误!");
        return null;
    }

    //添加在线地图图层
    private WMTSLayer[] addZxWmtsLayer(EzMap map,String layerType){
        if(layerType.equals("zxsl")){
            //注意：以下“在线地图URL”、“LayerName”、“每个Layer的级别范围”是省厅PGIS的相关信息，如果是地市的移动警务项目，建议从地市PGIS承建商获取参数
            //注意：以下在线地图URL均为公安内网地址，移动警务手机VPN专网暂时无法访问，后期会由边界厂商协助开通无线访问链路，届时该URL地址会发生变化。请访问http://10.136.7.107:8080/MapsDownload定期关注“警务地图开发工具包”栏目中的更新提示。
            String zxslServerUrl = "http://192.168.10.74:29080/PGIS_S_TileMapServer/Maps/VEC";
            String[] zxslAllLayerNames = {"layer0"};
            String[] zxslAllLayerLevel = {"7-19"};
            String zxslPOIServerUrl = "http://192.168.10.74:29080/PGIS_S_TileMapServer/Maps/VECPOI";
            String[] zxslPOIAllLayerNames = {"layer0"};
            String[] zxslPOIAllLayerLevel = {"7-19"};
            if(zxslAllLayerNames.length!=zxslAllLayerLevel.length||zxslPOIAllLayerNames.length!=zxslPOIAllLayerLevel.length){
                Log.e("error", "LayerNames和LayerLevel数组长度不一致!");
                return null;
            }
            WMTSLayer[] returnWMTSLayerArray = new WMTSLayer[zxslAllLayerNames.length+zxslPOIAllLayerNames.length];
            int i = 0;
            for(int k=0; k<zxslAllLayerNames.length; k++){
                String layerName = zxslAllLayerNames[k];
                int minLevel = Integer.parseInt(zxslAllLayerLevel[k].split("-")[0]);
                int maxLevel = Integer.parseInt(zxslAllLayerLevel[k].split("-")[1]);
                //注意：在线矢量地图缓存存放的目录要求所有厂商在调用时要一致用Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapslonline"
                WMTSLayer lry = createWMTSLayer(map,zxslServerUrl,layerName,"zxsl_cache.tmp",Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapslonline",minLevel,maxLevel);
                returnWMTSLayerArray[i++] = lry;
            }
            for(int k=0; k<zxslPOIAllLayerNames.length; k++){
                String layerName = zxslPOIAllLayerNames[k];
                int minLevel = Integer.parseInt(zxslPOIAllLayerLevel[k].split("-")[0]);
                int maxLevel = Integer.parseInt(zxslPOIAllLayerLevel[k].split("-")[1]);
                //注意：在线矢量地图缓存存放的目录要求所有厂商在调用时要一致用Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapslonline"
                WMTSLayer lry = createWMTSLayer(map,zxslPOIServerUrl,layerName,"zxslpoi_cache.tmp",Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapslonline",minLevel,maxLevel);
                returnWMTSLayerArray[i++] = lry;
            }
            return returnWMTSLayerArray;
        }else if(layerType.equals("zxyx")){
            //注意：以下“在线地图URL”、“LayerName”、“每个Layer的级别范围”是省厅PGIS的相关信息，如果是地市的移动警务项目，建议从地市PGIS承建商获取参数
            String zxyxServerUrl = "http://192.168.10.74:29080/PGIS_S_TileMapServer/Maps/YX";
            String[] zxyxAllLayerNames = {"IMGL7_L14","IMGL15_L17","IMGNCL18_L19"};
            String[] zxyxAllLayerLevel = {"7-14","15-17","18-19"};
            String zxyxPOIServerUrl = "http://192.168.10.74:29080/PGIS_S_TileMapServer/Maps/QSYXPOI";
            String[] zxyxPOIAllLayerNames = {"layer0"};
            String[] zxyxPOIAllLayerLevel = {"7-19"};
            if(zxyxAllLayerNames.length!=zxyxAllLayerLevel.length||zxyxPOIAllLayerNames.length!=zxyxPOIAllLayerLevel.length){
                Log.e("error", "LayerNames和LayerLevel数组长度不一致!");
                return null;
            }
            WMTSLayer[] returnWMTSLayerArray = new WMTSLayer[zxyxAllLayerNames.length+zxyxPOIAllLayerNames.length];
            int i = 0;
            for(int k=0; k<zxyxAllLayerNames.length; k++){
                String layerName = zxyxAllLayerNames[k];
                int minLevel = Integer.parseInt(zxyxAllLayerLevel[k].split("-")[0]);
                int maxLevel = Integer.parseInt(zxyxAllLayerLevel[k].split("-")[1]);
                //注意：在线影像地图缓存存放的目录要求所有厂商在调用时要一致用Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapyxonline"
                WMTSLayer lry = createWMTSLayer(map,zxyxServerUrl,layerName,"zxyx_cache.tmp",Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapyxonline",minLevel,maxLevel);
                returnWMTSLayerArray[i++] = lry;
            }
            for(int k=0; k<zxyxPOIAllLayerNames.length; k++){
                String layerName = zxyxPOIAllLayerNames[k];
                int minLevel = Integer.parseInt(zxyxPOIAllLayerLevel[k].split("-")[0]);
                int maxLevel = Integer.parseInt(zxyxPOIAllLayerLevel[k].split("-")[1]);
                //注意：在线影像地图缓存存放的目录要求所有厂商在调用时要一致用Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapyxonline"
                WMTSLayer lry = createWMTSLayer(map,zxyxPOIServerUrl,layerName,"zxyxpoi_cache.tmp",Environment.getExternalStorageDirectory().getPath()+"/EzMap/mapyxonline",minLevel,maxLevel);
                returnWMTSLayerArray[i++] = lry;
            }
            return returnWMTSLayerArray;
        }
        Log.e("error", "调用添加在线地图图层方法时参数传递错误!");
        return null;
    }

    /**
     * 加载指定WMTS图层，图层加载到地图上暂时不显示，需要手工调用setVisible(true)方法将WMTS图层显示
     * @param map
     * @param wmtsServerURL
     * @param layerName  注意：在线地图layername名称必须和远程的WMTS服务的LayerName一致；离线地图layername名称可任意写
     * @param cacheName
     * @param cacheFileDirectoryPath   注意：在线地图缓存存放的目录要求所有厂商在调用时要一致
     * @param minLevel
     * @param maxLevel
     * @return
     */
    private WMTSLayer createWMTSLayer(EzMap map,String wmtsServerURL,String layerName,String cacheName,String cacheFileDirectoryPath,int minLevel,int maxLevel){
        WMTSLayerInfo info = new WMTSLayerInfo();
        info.setVersion("1.0.0");
        info.setFormat("tiles");
        info.setStyle("default");
        info.setLayerName(layerName);//在线地图layername名称必须和远程的WMTS服务的LayerName一致；离线地图layername名称可任意写
        info.setTileMatrixSet("Matrix_0");
        WMTSLayer lyr = new WMTSLayer(wmtsServerURL,info,cacheName,cacheFileDirectoryPath);//注意：在线地图缓存存放的目录要求所有厂商在调用时要一致
        lyr.setMinLevel(minLevel);
        lyr.setMaxLevel(maxLevel);
        lyr.setVisible(false);
        map.addLayer(lyr);
        return lyr;
    }

    private void hiddenMapLayer(Layer lr){
        if(lr!=null){
            lr.setVisible(false);
        }
    }
    private void hiddenMapLayer(Layer[] lrs){
        if(lrs==null) return;
        for(Layer lr:lrs){
            if(lr!=null)lr.setVisible(false);
        }
    }
    private void showMapLayer(Layer lr){
        if(lr!=null){
            lr.setVisible(true);
        }
    }
    private void showMapLayer(Layer[] lrs){
        if(lrs==null) return;
        for(Layer lr:lrs){
            if(lr!=null)lr.setVisible(true);
        }
    }
}
