package com.wix.dp3t;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.dpppt.android.sdk.DP3T;
import org.dpppt.android.sdk.TracingStatus;
import org.dpppt.android.sdk.internal.AppConfigManager;

import java.io.Console;

public class Dp3tModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private String TAG = "Dp3tModule";

    public Dp3tModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        registerToStatusEvents();
    }

    @Override
    public String getName() {
        return "Dp3t";
    }


    public void registerToStatusEvents() {
        reactContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TracingStatus status = getStatus();
                Log.d(TAG,"status");
            }
        }, DP3T.getUpdateIntentFilter());
    }

    @ReactMethod
    public void startScan() {
        DP3T.start(reactContext, false,true);
    }

    @ReactMethod
    public void startAdvertising() {
        DP3T.start(reactContext, true,false);
    }

    @ReactMethod
    public void startScanningAndAdvertising() {
        DP3T.start(reactContext, true,true);
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
    public TracingStatus getStatus() {
        TracingStatus status = DP3T.getStatus(reactContext);
        return status;
    }


    @ReactMethod
    public void _clearData() {
        DP3T.clearData(reactContext, new Runnable() {
            @Override
            public void run() {

            }
        });
    }


    @ReactMethod
    public void getScanInterval() {
        AppConfigManager.getInstance(reactContext).getScanInterval();
    }

}
