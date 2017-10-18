package com.jjapp.ezmap;

import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by yc-founder on 2017/10/16.
 */

public class RNEzMapInterface extends ReactContextBaseJavaModule {

    private static final String REACT_CLASS="RNEzMapInterface";
    private ReactApplicationContext aContext;
    public RNEzMapInterface(ReactApplicationContext reactContext) {
        super(reactContext);
        aContext=reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void HandleMessage(){
        Log.i("RNMessage","xxxxxxxx还没执行?");
    }

    @ReactMethod
    public void InitMap(){
        Intent aIntent=new Intent(aContext,EzMapMainActivity.class);
        aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        aContext.startActivity(aIntent);
    }
}
