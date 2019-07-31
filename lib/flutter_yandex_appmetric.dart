import 'dart:async';

import 'package:flutter/services.dart';


class FlutterYandexAppmetric {
  static const MethodChannel _channel = const MethodChannel('com.kf.flutter_yandex_appmetric');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<bool> init({String apiKey}) async {
       final bool init = await _channel.invokeMethod('initialize', {'API_KEY': apiKey});
      return init;
  }
  static Future<bool> reportCustomEvent({String eventName}) async {
       final bool init = await _channel.invokeMethod('report_custom_event', {'EVENT_NAME': eventName});
      return init;
  }
}
