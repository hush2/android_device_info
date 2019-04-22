# android_device_info

A Flutter plugin for getting device information on Android devices.

This package is a binding for [EasyDeviceInfo](https://github.com/nisrulz/easydeviceinfo).

## usage

```dart
import 'package:android_device_info/android_device_info.dart';

var batteryInfo = await AndroidDeviceInfo().getBatteryInfo();
print(batteryInfo);
```

```
{isBatteryPresent: true,
 batteryPercentage: 71,
 batteryTechnology: Li-ion,
 chargingSource: AC,
 batteryTemperature: 0.0,
 batteryHealth: Having issues,
 isDeviceCharging: false,
 batteryVoltage: 0}

```

## permissions

Add required permissions to:

`<your project>/android/app/src/main/AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> <!-- Network Info -->
<uses-permission android:name="android.permission.INTERNET" /> <!-- Network Info -->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" /> <!-- WiFI Info -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" /> <!-- SIM Info / Phone # -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" /> <!-- Location Info -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" /> <!-- Location Info -->
<uses-permission android:name="android.permission.USE_FINGERPRINT" /> <!-- Fingerprint Info -->
```

Permissions are not handled by the library, please use [permission_handler](https://pub.dartlang.org/packages/permission_handler) to handle permissions.

## implemented apis

- **Device** `getDeviceInfo()`
- **Memory** `getMemoryInfo()`
- **Battery** `getBatteryInfo()`
- **Sensors** `getSensorInfo()`
- **Network** `getNetworkInfo()`
- **Display** `getDisplayInfo()`
- **NFC** `getNfcInfo()`
- **SIM** `getSimInfo()`
- **Config** `getConfigInfo()`
- **Location** `getLocationInfo()`
- **ABI** `getAbiInfo()`
- **Fingerprint** `getFingerprintInfo()`

## demo app

check [Flutter Android Device Info](https://github.com/hush2/flutter_android_device_info) for a working demo.

## credits

[Nishant Srivastava](https://github.com/nisrulz/) for [EasyDeviceInfo](https://github.com/nisrulz/easydeviceinfo).

## license

BSD
