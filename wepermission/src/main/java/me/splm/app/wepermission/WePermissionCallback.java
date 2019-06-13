package me.splm.app.wepermission;

import android.support.annotation.NonNull;

public interface WePermissionCallback {
    void onRequestPermissionGranted(int requestCode, @NonNull String[] permissions, boolean isAllGranted);
    void onRequestPermissionDennied(int requestCode, @NonNull String[] permissions);
}
