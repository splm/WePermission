package me.splm.app.wepermissiondemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import me.splm.app.wepermission.WePermissionCallback;
import me.splm.app.wepermission.WePermissions;

public class MainActivity extends AppCompatActivity implements WePermissionCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WePermissions.requestPermissions(this, 1, PermissionLegends.Permissions);
    }

    @Override
    public void onRequestPermissionGranted(int requestCode, @NonNull String[] permissions, boolean isAllGranted) {
        if(isAllGranted && requestCode==1){
            Log.e(TAG, "已经允许请求授权操作1");
        }else if(isAllGranted && requestCode==2){
            Log.e(TAG, "已经允许请求授权操作2");
        }
    }

    @Override
    public void onRequestPermissionDennied(int requestCode, @NonNull String[] permissions) {

    }
}
