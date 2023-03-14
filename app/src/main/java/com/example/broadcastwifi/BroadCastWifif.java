package com.example.broadcastwifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class BroadCastWifif extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            if (isNetWork(context)){
                Toast.makeText(context, "tc", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "tb", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNetWork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null){
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Network network = connectivityManager.getActiveNetwork();
            if (network == null){
                return false;
            }
            NetworkCapabilities capabilities =connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        }else {
            NetworkInfo netWorkInfor = connectivityManager.getActiveNetworkInfo();
            return  netWorkInfor != null && netWorkInfor.isConnected();
        }
    }
}
