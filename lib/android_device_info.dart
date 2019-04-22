import 'dart:async';
import 'package:flutter/services.dart';

class AndroidDeviceInfo {
  static const MethodChannel channel =
      const MethodChannel('net.hush2/android_device_info');

  var result = {};

  Future<Map<dynamic, dynamic>> getMemoryInfo({String unit = "bytes"}) async {
    try {
      result = await channel.invokeMethod('getMemoryInfo', {"unit": unit});
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getSystemInfo() async {
    try {
      result = await channel.invokeMethod('getSystemInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getDisplayInfo() async {
    try {
      result = await channel.invokeMethod('getDisplayInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getBatteryInfo() async {
    try {
      result = await channel.invokeMethod('getBatteryInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<List<dynamic>> getSensorInfo() async {
    var result;
    try {
      result = await channel.invokeMethod('getSensorInfo');
    } on PlatformException catch (e) {
      result = [
        {'error': true, 'code': e.code, 'message': e.message}
      ];
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getNetworkInfo() async {
    try {
      result = await channel.invokeMethod('getNetworkInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getNfcInfo() async {
    try {
      result = await channel.invokeMethod('getNfcInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getSimInfo() async {
    try {
      result = await channel.invokeMethod('getSimInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getAbiInfo() async {
    try {
      result = await channel.invokeMethod('getAbiInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getFingeprintInfo() async {
    try {
      result = await channel.invokeMethod('getFingerprintInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getConfigInfo() async {
    try {
      result = await channel.invokeMethod('getConfigInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }

  Future<Map<dynamic, dynamic>> getLocationInfo() async {
    try {
      result = await channel.invokeMethod('getLocationInfo');
    } on PlatformException catch (e) {
      result = {'error': true, 'code': e.code, 'message': e.message};
    }
    return result;
  }
}
