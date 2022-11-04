package com.amitor.send_sms;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** SendSmsPlugin */
public class SendSmsPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
Activity activity;

private static final int MY_PERMISSION_REQUEST_SEND_SMS = 0;

private String number = "";
private String msg = "";

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "send_sms");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("sendMessage")) {
    number = call.argument("number").toString();
        msg = call.argument("msg").toString();
     sendSms(number , msg);

      Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
//      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else if(call.method.equals("askPermission")){

      ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE},
              PackageManager.PERMISSION_GRANTED)  ;

    }

    else {
      result.notImplemented();
    }
  }

  private void sendSms(String number, String message) {


    SmsManager smsManager = SmsManager.getDefault();
    smsManager.sendTextMessage(number, null,  message, null, null);


  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
this.activity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
this.activity = null;
  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

  }

  @Override
  public void onDetachedFromActivity() {

  }
}
