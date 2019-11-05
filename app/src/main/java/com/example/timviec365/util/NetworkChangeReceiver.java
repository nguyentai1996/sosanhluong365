package com.example.timviec365.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if(status.isEmpty()) {
            status="Vui lòng kết nối INTERNET";
        }
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }
}