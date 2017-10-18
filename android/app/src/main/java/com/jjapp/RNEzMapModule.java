package com.jjapp;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNEzMapModule extends ReactContextBaseJavaModule {
    public RNEzMapModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName(){
        return "RNEzMapModule";
    }

    @ReactMethod
    public void HandleMessage(String aMassage){
        Log.i("RNMessage","received message from RN:"+aMassage);
    }
}