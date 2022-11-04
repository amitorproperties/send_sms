
import 'send_sms_platform_interface.dart';

class SendSms {
  Future<String?> getPlatformVersion(String number, String msg) {
    return SendSmsPlatform.instance.getPlatformVersion(number,msg);
  }
  Future<String?> getAskForPermission() {
    return SendSmsPlatform.instance.getAskForPermission();
  }

}
