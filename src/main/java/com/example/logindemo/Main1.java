package com.example.logindemo;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author gaohangbo
 * date: 2018/9/12 0012.
 */
public class Main1 implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        try {
            XposedBridge.log("gaohangboapp: " + loadPackageParam.packageName);
            if(loadPackageParam.packageName.equals("com.example.logindemo")){
                XposedHelpers.findAndHookMethod("com.example.logindemo.MainActivity", loadPackageParam.classLoader, "isOK", String.class, String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("账号："+(String)param.args[0]+"   密码："+(String)param.args[1]);
                        Log.i("zz","账号："+(String)param.args[0]+"   密码："+(String)param.args[1]);
                        //修改参数
                        param.args[0]="2";
                        param.args[1]="2";
                        super.beforeHookedMethod(param);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.i("zz", param.getResult().toString());
                        super.afterHookedMethod(param);
                    }
                });
            }else {
                Log.i("zz", "error");
            }
        }catch (Exception e){
            Log.i("zz", "error-----1111");
            e.printStackTrace();
        }


    }


//    private void hook_method(String className, ClassLoader classLoader, String methodName,
//                             Object... parameterTypesAndCallback){
//        try {
//            XposedHelpers.findAndHookMethod(className, classLoader, methodName, parameterTypesAndCallback);
//        } catch (Exception e) {
//            XposedBridge.log(e);
//        }
//    }
//
//    private void hook_methods(String className, String methodName, XC_MethodHook xmh){
//        try {
//            Class<?> clazz = Class.forName(className);
//            for (Method method : clazz.getDeclaredMethods())
//                if (method.getName().equals(methodName)
//                        && !Modifier.isAbstract(method.getModifiers())
//                        && Modifier.isPublic(method.getModifiers())) {
//                    XposedBridge.hookMethod(method, xmh);
//                }
//        } catch (Exception e) {
//            XposedBridge.log(e);
//        }
//    }
//    @Override
//    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
//        Log.i("jw", "pkg:"+loadPackageParam.packageName);
//        XposedBridge.log("Loaded app: " + loadPackageParam.packageName);
//        hook_method("android.telephony.TelephonyManager", loadPackageParam.classLoader, "getDeviceId", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                Log.i("jw", "hook getDeviceId...");
//                Object obj = param.getResult();
//                Log.i("jw", "imei args:"+obj);
//                param.setResult("jiangwei");
//            }
//        });
//    }
}
