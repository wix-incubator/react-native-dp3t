package com.wix.dp3t;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import org.dpppt.android.sdk.DP3T;
import org.dpppt.android.sdk.TracingStatus;
import org.dpppt.android.sdk.internal.AppConfigManager;
import org.dpppt.android.sdk.internal.database.Database;
import org.dpppt.android.sdk.internal.database.models.Handshake;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
                //TracingStatus status = getStatus();
            }
        }, DP3T.getUpdateIntentFilter());
    }

    @ReactMethod
    public void startScan() {
        TracingStatus status = DP3T.getStatus(reactContext);
        DP3T.start(reactContext, status.isAdvertising(),true);
    }

    @ReactMethod
    public void startAdvertising() {
        TracingStatus status = DP3T.getStatus(reactContext);
        DP3T.start(reactContext, true,status.isReceiving());
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
    public void getStatus(Callback callback) {
        TracingStatus status = DP3T.getStatus(reactContext);
        WritableMap statusMap = Arguments.createMap();
        statusMap.putInt("numberOfHandshakes", status.getNumberOfHandshakes());
        statusMap.putBoolean("advertising", status.isAdvertising());
        statusMap.putBoolean("scanning", status.isReceiving());
        statusMap.putBoolean("reportedAsExposed", status.isReportedAsExposed());
        statusMap.putBoolean("wasContactExposed", status.wasContactExposed());
        statusMap.putInt("wasContactExposed", (int) status.getLastSyncDate());
        WritableArray errArr = Arguments.createArray();
        for(TracingStatus.ErrorState err : status.getErrors()){
            errArr.pushString(errArr.toString());
        }
        statusMap.putArray("errors",errArr);
        callback.invoke(statusMap);
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
    public void getHandshakes(Callback callback) {
        Database db = new Database(reactContext);
        List<Handshake> handshakes = db.getHandshakes();
        WritableArray handshakesArr = Arguments.createArray();
        for(Handshake hs : handshakes){
            WritableMap hsMap = Arguments.createMap();
            hsMap.putInt("rssi",hs.getRssi());
            hsMap.putString("public_key",new String(Base64.encode(hs.getEphId(), Base64.NO_WRAP)).substring(0, 10));
            hsMap.putDouble("hs_timestamp", hs.getTimestamp());
            handshakesArr.pushMap(hsMap);
        }

        callback.invoke(handshakesArr);
    }


    @ReactMethod
    public void getScanInterval() {
        AppConfigManager.getInstance(reactContext).getScanInterval();
    }

}
