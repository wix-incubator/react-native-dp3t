package com.wix.dp3t;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import org.dpppt.android.sdk.DP3T;

public class Dp3tModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public Dp3tModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Dp3t";
    }




   /* function _startScan() {
        Dp3t.start()
    }

    function _stoptScan() {
        Dp3t.stop()
    }

    function _sync() {
        Dp3t.sync()
    }

    function _getStatus() {
        Dp3t.getStatus()
    }

    function _clearData() {
        Dp3t.clearData()
    }
    */


    @ReactMethod
    public void start() {
        DP3T.start(reactContext);
    }

    @ReactMethod
    public void stop() {
        DP3T.stop(reactContext);
    }

    @ReactMethod
    public void sync() {
        DP3T.sync(reactContext);
    }

    @ReactMethod
    public void _getStatus() {
        DP3T.getStatus(reactContext);
    }

    @ReactMethod
    public void _clearData() {
        DP3T.clearData(reactContext, new Runnable() {
            @Override
            public void run() {

            }
        });
    }

}
