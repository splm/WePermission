# WePermission
一个让权限变得更加简便的框架。

## How to use

- 1.定义一个权限清单（PermissionLegends）,如下：

```java
public class PermissionLegends {
    public static final String[] Permissions={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS
    };
}
```

- 2.在需要申请权限的地方调用如下代码：

```java
WePermissions.requestPermissions(MainActivity.this, 1,PermissionLegends.Permissions);
```

- 3.重写`onRequestPermissionsResult`方法，并调用

```java
@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        WePermissions.onRequestPermissionResult(this, requestCode, grantResults, permissions);
    }
```

- 4.实现`WePermissionCallback`接口，并实现以下两个方法：

```java
//当用户允许授权时调用
void onRequestPermissionGranted(int requestCode, @NonNull String[] permissions, boolean isAllGranted);
//当用户拒绝授权时调用
void onRequestPermissionDenied(int requestCode, @NonNull String[] permissions,boolean isAllDenied);
```

以下是两个方法完整使用样例：

```java
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
```

## How to design

