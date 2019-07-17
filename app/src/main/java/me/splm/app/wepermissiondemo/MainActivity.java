package me.splm.app.wepermissiondemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.splm.app.wepermission.WePermissionCallback;
import me.splm.app.wepermission.WePermissions;

public class MainActivity extends AppCompatActivity implements WePermissionCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        Log.e(TAG, "准备申请权限");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WePermissions.requestPermissions(MainActivity.this, 1, PermissionLegends.Permissions);
            }
        });

        Button simpleBtn=findViewById(R.id.btn_2);
        simpleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WePermissions.requestPermissions(MainActivity.this, 2, PermissionLegends.Permissions[5]);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        WePermissions.onRequestPermissionResult(this, requestCode, grantResults, permissions);
    }

    @Override
    public void onRequestPermissionGranted(int requestCode, @NonNull String[] permissions, boolean isAllGranted) {
        if (requestCode == 1) {
            Log.e(TAG, "已经允许请求授权操作1");
            for (String s : permissions) {
                Log.e(TAG, "onRequestPermissionGranted: 允许使用的权限  " + s);
            }
        } else if (requestCode == 2) {
            if(isAllGranted){
                Log.e(TAG, "已经允许请求授权操作2");
            }
        }
    }

    @Override
    public void onRequestPermissionDenied(int requestCode, @NonNull String[] permissions,boolean isAllDenied) {
        Log.e(TAG, "onRequestPermissionDennied: requestCode=" + requestCode);
        for (String s : permissions) {
            Log.e(TAG, "onRequestPermissionDennied: " + s);
        }
        if(requestCode == 2){
            if(isAllDenied){
                Log.e(TAG, "onRequestPermissionDennied: 拒绝了2的申请权限操作");
            }
        }
    }
}
