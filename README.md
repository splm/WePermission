# WePermission
> A project to make your permission be easy.

## How to design

![流程图](https://github.com/splm/WePermission/blob/master/WePermission-flow.png)

--------------------------------------

## Installation

[![](https://jitpack.io/v/splm/WePermission.svg)](https://jitpack.io/#splm/WePermission)

> ### Gradle
>
> **Step 1 :  Add the JitPack repository to your build file**
>
> ```groovy
> allprojects {
> 		repositories {
> 			...
> 			maven { url 'https://jitpack.io' }
> 		}
> 	}
> ```
>
> **Step 2 : Add the dependency** 
>
> ```groovy
> dependencies {
> 	        implementation 'com.github.splm:WePermission:0.0.2'
> 	}
> ```
>
>
>
> --------------
>
> ### Maven
>
> **Step 1 :  Add the JitPack repository to your build file **
>
> ```xml
> <repositories>
> 		<repository>
> 		    <id>jitpack.io</id>
> 		    <url>https://jitpack.io</url>
> 		</repository>
> 	</repositories>
> ```
>
> **Step 2 :  Add the dependency **
>
> ```xml
> <dependency>
> 	    <groupId>com.github.splm</groupId>
> 	    <artifactId>WePermission</artifactId>
> 	    <version>Tag</version>
> 	</dependency>
> ```
>
>



## Usage

- 1.Define a permission legend(PermissionLegends)  by yourself：

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

- 2.Add the following code into ：

```java
WePermissions.requestPermissions(MainActivity.this, 1,PermissionLegends.Permissions);
```

- 3.Re-write`onRequestPermissionsResult`method，and call it

```java
@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        WePermissions.onRequestPermissionResult(this, requestCode, grantResults, permissions);
    }
```

- 4.Implement`WePermissionCallback`interface，and call the following two methods：

```java
//当用户允许授权时调用
void onRequestPermissionGranted(int requestCode, @NonNull String[] permissions, boolean isAllGranted);
//当用户拒绝授权时调用
void onRequestPermissionDenied(int requestCode, @NonNull String[] permissions,boolean isAllDenied);
```

The following code is complete sample：

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
