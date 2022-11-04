import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'send_sms_platform_interface.dart';

/// An implementation of [SendSmsPlatform] that uses method channels.Future<null
class MethodChannelSendSms extends SendSmsPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('send_sms');

  @override
  Future<String?> getPlatformVersion(String number, String msg ) async {

    Map<String,dynamic > args = Map<String,dynamic>();
    args.putIfAbsent("msg", () => msg);
    args.putIfAbsent("number", () => number);

    final version = await methodChannel.invokeMethod<String>('sendMessage', args);
    return version;
  }
@override
  Future<String?> getAskForPermission() async  {
  await methodChannel.invokeMethod<String>('askPermission');

    return super.getAskForPermission();
  }
  //   Future<Null> getShowToast(String msg) async {
  //    await methodChannel .invokeMapMethod('showToast');
  //   return null;
  // }



}
