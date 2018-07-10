import 'dart:async';

import 'package:flutter/services.dart';

class MiPush {
  static const MethodChannel _channel =
      const MethodChannel('push.lmy.com/mi_push');

  static Future<bool> init({String id, String key}) async {
    final bool result =
        await _channel.invokeMethod('init', {'id': id, 'key': key});
    return result;
  }

  static Future<bool> setUserAccount({String account}) async {
    final bool result =
        await _channel.invokeMethod('setUserAccount', {'account': account});
    return result;
  }
}
