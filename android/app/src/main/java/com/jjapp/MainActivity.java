package com.jjapp;

import com.facebook.react.ReactActivity;
import com.jjapp.ezmap.RNEzMapEntity;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        RNEzMapEntity.setActivity(this);
        return "JJApp";
    }
}
