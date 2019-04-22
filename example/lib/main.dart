import 'package:flutter/material.dart';
import 'dart:async';
import 'package:android_device_info/android_device_info.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  var _batteryInfo = {};

  @override
  void initState() {
    super.initState();

    _getBatteryInfo();
  }

  Future<void> _getBatteryInfo() async {
    var batteryInfo = await AndroidDeviceInfo().getBatteryInfo();
    print(batteryInfo);

    if (batteryInfo['error'] != null) {
      print(batteryInfo['message']);
    }

    if (!mounted) return;

    setState(() {
      _batteryInfo = batteryInfo;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Plugin example app')),
        body: _batteryInfo.isEmpty
            ? CircularProgressIndicator()
            : _buildBatteryInfo(),
      ),
    );
  }

  _buildBatteryInfo() {
    if (_batteryInfo['error'] != null) {
      return Text('Failed to get battery information.');
    }
    return Column(
      children: <Widget>[
        Text('isBatteryPresent: ${_batteryInfo['isBatteryPresent']}'),
        Text('batteryPercentage: ${_batteryInfo['batteryPercentage']}%'),
        Text('batteryTechnology: ${_batteryInfo['batteryTechnology']}'),
        Text('chargingSource: ${_batteryInfo['chargingSource']}'),
        Text('batteryTemperature: ${_batteryInfo['batteryTemperature']}'),
        Text('batteryHealth: ${_batteryInfo['batteryHealth']}'),
        Text('isDeviceCharging: ${_batteryInfo['isDeviceCharging']}'),
        Text('batteryVoltage: ${_batteryInfo['batteryVoltage']}'),
      ],
    );
  }
}
