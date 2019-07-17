package me.splm.app.wepermission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class WePermissions {

    public static boolean hasPermissions(@NonNull Context context, @Size(min = 1) @NonNull String... perms) {
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    //PackageManager.PERMISSION_GRANTED
    private static String[] checkNoHaven(@NonNull Context context, @Size(min = 1) @NonNull String... perms) {
        String[] permissions = new String[perms.length];
        for (int i = 0; i < perms.length; i++) {
            if (ContextCompat.checkSelfPermission(context, perms[i]) != PackageManager.PERMISSION_GRANTED) {
                permissions[i] = perms[i];
            }
        }
        return permissions;
    }

    public static void requestPermissions(Activity activity, int requestCode, @Size(min = 1) @NonNull String... perms) {
        if (hasPermissions(activity, perms)) {
            notifyAlreadyHasPermissions(activity, requestCode, perms);
        } else {
            String[] permissions = checkNoHaven(activity, perms);
            requestSelfPermissions(activity, requestCode, permissions);
        }
    }

    private static void notifyAlreadyHasPermissions(Activity reciever, int requestCode, String... perms) {
        int[] grantedResults = new int[perms.length];
        for (int i = 0; i < grantedResults.length; i++) {
            grantedResults[i] = PackageManager.PERMISSION_GRANTED;
        }
        onRequestPermissionResult(reciever, requestCode, grantedResults, perms);
    }

    public static void onRequestPermissionResult(Activity receiver, int requestCode, int[] grantedResults, String... perms) {
        List<String> granteds = new ArrayList<>();
        List<String> denieds = new ArrayList<>();
        for (int i = 0; i < grantedResults.length; i++) {
            if (grantedResults[i] == PackageManager.PERMISSION_GRANTED) {
                if(perms[i] != null) granteds.add(perms[i]);
            } else {
                if(perms[i] != null) denieds.add(perms[i]);
            }
        }

        if (receiver instanceof WePermissionCallback) {
            WePermissionCallback callback = (WePermissionCallback) receiver;
            //部分权限被允许
            if (!granteds.isEmpty() && granteds.size() < perms.length) {
                callback.onRequestPermissionGranted(requestCode, granteds.toArray(new String[]{}), false);
            }
            //部分权限被拒绝
            if(!denieds.isEmpty() && denieds.size()<perms.length){
                callback.onRequestPermissionDenied(requestCode, denieds.toArray(new String[]{}),false);
            }
            //全部权限被拒绝
            if (!denieds.isEmpty() && granteds.isEmpty()) {
                callback.onRequestPermissionDenied(requestCode, denieds.toArray(new String[]{}),true);
            }
            //全部权限被允许
            if (!granteds.isEmpty() && denieds.isEmpty()) {
                callback.onRequestPermissionGranted(requestCode, granteds.toArray(new String[]{}), true);
            }
        }
    }

    private static void requestSelfPermissions(Activity activity, int requestCode, String... perms) {
        ActivityCompat.requestPermissions(activity, perms, requestCode);
    }
}
