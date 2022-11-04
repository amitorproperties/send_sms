import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'send_sms_method_channel.dart';

abstract class SendSmsPlatform extends PlatformInterface {
  /// Constructs a SendSmsPlatform.
  SendSmsPlatform() : super(token: _token);

  static final Object _token = Object();

  static SendSmsPlatform _instance = MethodChannelSendSms();

  /// The default instance of [SendSmsPlatform] to use.
  ///
  /// Defaults to [MethodChannelSendSms].
  static SendSmsPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [SendSmsPlatform] when
  /// they register themselves.
  static set instance(SendSmsPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion(String number, String msg) {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
  Future<String?> getAskForPermission() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
