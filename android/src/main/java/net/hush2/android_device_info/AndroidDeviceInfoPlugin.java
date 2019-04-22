// (c) 2019 hush2. https://github.com/hush2

package net.hush2.android_device_info;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import github.nisrulz.easydeviceinfo.base.BatteryHealth;
import github.nisrulz.easydeviceinfo.base.ChargingVia;
import github.nisrulz.easydeviceinfo.base.DeviceType;
import github.nisrulz.easydeviceinfo.base.EasyBatteryMod;
import github.nisrulz.easydeviceinfo.base.EasyConfigMod;
import github.nisrulz.easydeviceinfo.base.EasyCpuMod;
import github.nisrulz.easydeviceinfo.base.EasyDeviceMod;
import github.nisrulz.easydeviceinfo.base.EasyDisplayMod;
import github.nisrulz.easydeviceinfo.base.EasyFingerprintMod;
import github.nisrulz.easydeviceinfo.base.EasyLocationMod;
import github.nisrulz.easydeviceinfo.base.EasyMemoryMod;
import github.nisrulz.easydeviceinfo.base.EasyNetworkMod;
import github.nisrulz.easydeviceinfo.base.EasyNfcMod;
import github.nisrulz.easydeviceinfo.base.EasySensorMod;
import github.nisrulz.easydeviceinfo.base.EasySimMod;
import github.nisrulz.easydeviceinfo.base.NetworkType;
import github.nisrulz.easydeviceinfo.base.OrientationType;
import github.nisrulz.easydeviceinfo.base.PhoneType;
import github.nisrulz.easydeviceinfo.base.RingerMode;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class AndroidDeviceInfoPlugin implements MethodCallHandler {
    private final Context context;
    private final Activity activity;

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "net.hush2/android_device_info");

        channel.setMethodCallHandler(new AndroidDeviceInfoPlugin(registrar.context(), registrar.activity()));
    }

    private AndroidDeviceInfoPlugin(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        switch (call.method) {
            case "getBatteryInfo":
                HashMap<String, Object> getBatteryInfo = getBatteryInfo();
                result.success(getBatteryInfo);
                break;
            case "getSensorInfo":
                List<HashMap<String, Object>> getSensorInfo = getSensorInfo();
                result.success(getSensorInfo);
                break;
            case "getFingerprintInfo":
                HashMap<String, Object> getFingerprintInfo = getFingerprintInfo();
                result.success(getFingerprintInfo);
                break;
            case "getMemoryInfo":
                String unit = call.argument("unit");
                assert unit !=null;
                HashMap<String, Object> getMemoryInfo = getMemoryInfo(unit);
                result.success(getMemoryInfo);
                break;
            case "getSystemInfo":
                HashMap<String, Object> getSystemInfo = getSystemInfo();
                result.success(getSystemInfo);
                break;
            case "getDisplayInfo":
                HashMap<String, Object> getDisplayInfo = getDisplayInfo();
                result.success(getDisplayInfo);
                break;
            case "getConfigInfo":
                HashMap<String, Object> getConfigInfo = getConfigInfo();
                result.success(getConfigInfo);
                break;
            case "getSimInfo":
                HashMap<String, Object> getSimInfo = getSimInfo();
                result.success(getSimInfo);
                break;
            case "getNfcInfo":
                HashMap<String, Object> getNfcInfo = getNfcInfo();
                result.success(getNfcInfo);
                break;
            case "getNetworkInfo":
                HashMap<String, Object> getNetworkInfo = getNetworkInfo();
                result.success(getNetworkInfo);
                break;
            case "getLocationInfo":
                HashMap<String, Object> getLocationInfo = getLocationInfo();
                result.success(getLocationInfo);
                break;
            case "getAbiInfo":
                HashMap<String, Object> getAbiInfo = getAbiInfo();
                result.success(getAbiInfo);
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    private HashMap<String, Object> getBatteryInfo() {

        EasyBatteryMod easyBatteryMod = new EasyBatteryMod(this.context);

        HashMap<String, Object> result = new HashMap<>();

        result.put("batteryPercentage", easyBatteryMod.getBatteryPercentage());
        result.put("isDeviceCharging", easyBatteryMod.isDeviceCharging());
        result.put("batteryTechnology", String.valueOf(easyBatteryMod.getBatteryTechnology()));
        result.put("batteryTemperature", easyBatteryMod.getBatteryTemperature());
        result.put("batteryVoltage", easyBatteryMod.getBatteryVoltage());
        result.put("isBatteryPresent", easyBatteryMod.isBatteryPresent());

        @BatteryHealth
        int batteryHealth = easyBatteryMod.getBatteryHealth();
        switch (batteryHealth) {
            case BatteryHealth.GOOD:
                result.put("batteryHealth", "Good");
                break;
            case BatteryHealth.HAVING_ISSUES:
                result.put("batteryHealth", "Having issues");
                break;
            default:
                result.put("batteryHealth", "Having issues");
                break;
        }

        @SuppressLint("WrongConstant") @ChargingVia
        int isChargingVia = easyBatteryMod.getChargingSource();
        switch (isChargingVia) {
            case ChargingVia.AC:
                result.put("chargingSource", "AC");
                break;
            case ChargingVia.USB:
                result.put("chargingSource", "USB");
                break;
            case ChargingVia.WIRELESS:
                result.put("chargingSource", "Wireless");
                break;
            case ChargingVia.UNKNOWN_SOURCE:
            default:
                result.put("chargingSource", "-");
                break;
        }

        return result;
    }

    private List<HashMap<String, Object>> getSensorInfo() {

        EasySensorMod easySensorMod = new EasySensorMod(this.context);

        List<HashMap<String, Object>> result = new ArrayList<>();

        List<Sensor> sensors = easySensorMod.getAllSensors();

        for (Sensor sensor : sensors) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("name", sensor.getName());
            data.put("vendor", sensor.getVendor());
            data.put("version", sensor.getVersion());
            data.put("power", sensor.getPower());
            data.put("resolution", sensor.getResolution());
            data.put("maximumRange", sensor.getMaximumRange());
            result.add(data);
        }

        return result;
    }

    private HashMap<String, Object> getFingerprintInfo() {

        EasyFingerprintMod easyFingerprintMod = new EasyFingerprintMod(this.context);

        HashMap<String, Object> result = new HashMap<>();

        result.put("isFingerprintSensorPresent", easyFingerprintMod.isFingerprintSensorPresent());
        result.put("areFingerprintsEnrolled", easyFingerprintMod.areFingerprintsEnrolled());

        return result;
    }

    private HashMap<String, Object> getMemoryInfo(String unit) {

        EasyMemoryMod easyMemoryMod = new EasyMemoryMod(this.context);

        float totalRAM;
        float availableInternalMemorySize;
        float availableExternalMemorySize;
        float totalInternalMemorySize;
        float totalExternalMemorySize;

        switch (unit.toLowerCase()) {

            case "kb":
                totalRAM = easyMemoryMod.convertToKb(easyMemoryMod.getTotalRAM());
                availableInternalMemorySize = easyMemoryMod.convertToKb(easyMemoryMod.getAvailableInternalMemorySize());
                availableExternalMemorySize = easyMemoryMod.convertToKb(easyMemoryMod.getAvailableExternalMemorySize());
                totalInternalMemorySize = easyMemoryMod.convertToKb(easyMemoryMod.getTotalInternalMemorySize());
                totalExternalMemorySize = easyMemoryMod.convertToKb(easyMemoryMod.getTotalExternalMemorySize());
                break;

            case "mb":
                totalRAM = easyMemoryMod.convertToMb(easyMemoryMod.getTotalRAM());
                availableInternalMemorySize = easyMemoryMod.convertToMb(easyMemoryMod.getAvailableInternalMemorySize());
                availableExternalMemorySize = easyMemoryMod.convertToMb(easyMemoryMod.getAvailableExternalMemorySize());
                totalInternalMemorySize = easyMemoryMod.convertToMb(easyMemoryMod.getTotalInternalMemorySize());
                totalExternalMemorySize = easyMemoryMod.convertToMb(easyMemoryMod.getTotalExternalMemorySize());
                break;

            case "gb":
                totalRAM = easyMemoryMod.convertToGb(easyMemoryMod.getTotalRAM());
                availableInternalMemorySize = easyMemoryMod.convertToGb(easyMemoryMod.getAvailableInternalMemorySize());
                availableExternalMemorySize = easyMemoryMod.convertToGb(easyMemoryMod.getAvailableExternalMemorySize());
                totalInternalMemorySize = easyMemoryMod.convertToGb(easyMemoryMod.getTotalInternalMemorySize());
                totalExternalMemorySize = easyMemoryMod.convertToGb(easyMemoryMod.getTotalExternalMemorySize());
                break;

            default: // bytes
                totalRAM = easyMemoryMod.getTotalRAM();
                availableInternalMemorySize = easyMemoryMod.getAvailableInternalMemorySize();
                availableExternalMemorySize = easyMemoryMod.getAvailableExternalMemorySize();
                totalInternalMemorySize = easyMemoryMod.getTotalInternalMemorySize();
                totalExternalMemorySize = easyMemoryMod.getTotalExternalMemorySize();
                break;
        }

        HashMap<String, Object> result = new HashMap<>();

        result.put("totalRAM", totalRAM);
        result.put("availableInternalMemorySize", availableInternalMemorySize);
        result.put("availableExternalMemorySize", availableExternalMemorySize);
        result.put("totalInternalMemorySize", totalInternalMemorySize);
        result.put("totalExternalMemorySize", totalExternalMemorySize);

        return result;
    }

    private HashMap<String, Object> getSystemInfo() {

        EasyDeviceMod easyDeviceMod = new EasyDeviceMod(this.context);

        HashMap<String, Object> result = new HashMap<>();

        result.put("screenDisplayID", easyDeviceMod.getScreenDisplayID());
        result.put("buildVersionCodename", easyDeviceMod.getBuildVersionCodename());
        result.put("buildVersionIncremental", easyDeviceMod.getBuildVersionIncremental());
        result.put("buildVersionSDK", easyDeviceMod.getBuildVersionSDK());
        result.put("buildID", easyDeviceMod.getBuildID());
        result.put("manufacturer", easyDeviceMod.getManufacturer());
        result.put("model", easyDeviceMod.getModel());
        result.put("osCodename", easyDeviceMod.getOSCodename());
        result.put("osVersion", easyDeviceMod.getOSVersion());
        result.put("phoneNo", easyDeviceMod.getPhoneNo()); // READ_PHONE_STATE
        result.put("radioVer", easyDeviceMod.getRadioVer());
        result.put("product", easyDeviceMod.getProduct());
        result.put("device", easyDeviceMod.getDevice());
        result.put("board", easyDeviceMod.getBoard());
        result.put("hardware", easyDeviceMod.getHardware());
        result.put("bootloader", easyDeviceMod.getBootloader());
        result.put("fingerprint", easyDeviceMod.getFingerprint());
        result.put("isDeviceRooted", easyDeviceMod.isDeviceRooted());
        result.put("buildBrand", easyDeviceMod.getBuildBrand());
        result.put("buildHost", easyDeviceMod.getBuildHost());
        result.put("buildTags", easyDeviceMod.getBuildTags());
        result.put("buildTime", easyDeviceMod.getBuildTime());
        result.put("buildUser", easyDeviceMod.getBuildUser());
        result.put("buildVersionRelease", easyDeviceMod.getBuildVersionRelease());

        @DeviceType
        int deviceType = easyDeviceMod.getDeviceType(this.activity);
        switch (deviceType) {
            case DeviceType.WATCH:
                result.put("deviceType", "watch");
                break;
            case DeviceType.PHONE:
                result.put("deviceType", "phone");
                break;
            case DeviceType.PHABLET:
                result.put("deviceType", "phablet");
                break;
            case DeviceType.TABLET:
                result.put("deviceType", "tablet");
                break;
            case DeviceType.TV:
                result.put("deviceType", "tv");
                break;
        }

        @PhoneType
        int phoneType = easyDeviceMod.getPhoneType();
        switch (phoneType) {
            case PhoneType.CDMA:
                result.put("phoneType", "CDMA");
                break;
            case PhoneType.GSM:
                result.put("phoneType", "GSM");
                break;
            case PhoneType.NONE:
                result.put("phoneType", "NONE");
                break;
            default:
                result.put("phoneType", "Uknown");
                break;
        }

        @OrientationType
        int orientationType = easyDeviceMod.getOrientation(this.activity);
        switch (orientationType) {
            case OrientationType.LANDSCAPE:
                result.put("orientation", "landscape");
                break;
            case OrientationType.PORTRAIT:
                result.put("orientation", "portrait");
                break;
            case OrientationType.UNKNOWN:
            default:
                result.put("orientation", "uknown");
                break;
        }

        return result;
    }

    private HashMap<String, Object> getDisplayInfo() {

        EasyDisplayMod easyDisplayMod = new EasyDisplayMod(this.context);

        HashMap<String, Object> result = new HashMap<>();

        result.put("resolution", easyDisplayMod.getResolution());
        result.put("density", easyDisplayMod.getDensity());
        //result.put("displayXYCoordinates", easyDisplayMod.getDisplayXYCoordinates());
        result.put("refreshRate", easyDisplayMod.getRefreshRate());
        result.put("physicalSize", easyDisplayMod.getPhysicalSize());

        return result;
    }

    private HashMap<String, Object> getConfigInfo() {

        EasyConfigMod easyConfigMod = new EasyConfigMod(this.context);

        HashMap<String, Object> result = new HashMap<>();

        result.put("isRunningOnEmulator", easyConfigMod.isRunningOnEmulator());
        result.put("time", easyConfigMod.getTime());
        result.put("uptime", easyConfigMod.getUpTime());
        result.put("formattedTime", easyConfigMod.getFormattedTime());
        result.put("currentDate", easyConfigMod.getCurrentDate().getTime());
        result.put("formattedDate", easyConfigMod.getFormattedDate());
        result.put("hasSdCard", easyConfigMod.hasSdCard());

        @RingerMode
        int ringermode = easyConfigMod.getDeviceRingerMode();
        switch (ringermode) {
            case RingerMode.NORMAL:
                result.put("ringerMode", "Normal");
                break;
            case RingerMode.VIBRATE:
                result.put("ringerMode", "Vibrate");
                break;
            case RingerMode.SILENT:
                result.put("ringerMode", "Silent");
                break;
            default:
                result.put("ringerMode", "-");
                break;
        }

        return result;
    }

    private HashMap<String, Object> getSimInfo() {

        EasySimMod easySimMod = new EasySimMod(this.context);

        HashMap<String, Object> result = new HashMap<>();

        result.put("imsi", easySimMod.getIMSI());
        result.put("serial", easySimMod.getSIMSerial());
        result.put("country", easySimMod.getCountry());
        result.put("carrier", easySimMod.getCarrier());
        result.put("isSimNetworkLocked", easySimMod.isSimNetworkLocked());
//        result.put("activeMultiSimInfo", easySimMod.getActiveMultiSimInfo());
        result.put("isMultiSim", easySimMod.isMultiSim());
        result.put("numberOfActiveSim", easySimMod.getNumberOfActiveSim());

        return result;
    }

    private HashMap<String, Object> getNfcInfo() {

        EasyNfcMod easyNfcMod = new EasyNfcMod(this.context);

        HashMap<String, Object> result = new HashMap<>();

        result.put("isNfcPresent", easyNfcMod.isNfcPresent());
        result.put("isNfcEnabled", easyNfcMod.isNfcEnabled());

        return result;
    }

    private HashMap<String, Object> getNetworkInfo() {

        EasyNetworkMod easyNetworkMod = new EasyNetworkMod(this.context);

        HashMap<String, Object> result = new HashMap<>();

        result.put("isNetworkAvailable", easyNetworkMod.isNetworkAvailable());
        result.put("isWifiEnabled", easyNetworkMod.isWifiEnabled());
        result.put("iPv4Address", easyNetworkMod.getIPv4Address());
        result.put("iPv6Address", easyNetworkMod.getIPv6Address());
        result.put("wifiSSID", easyNetworkMod.getWifiSSID());
        result.put("wifiBSSID", easyNetworkMod.getWifiBSSID());
        result.put("wifiLinkSpeed", easyNetworkMod.getWifiLinkSpeed());
        result.put("wifiMAC", easyNetworkMod.getWifiMAC());

        @NetworkType
        int networkType = easyNetworkMod.getNetworkType();
        switch (networkType) {
            case NetworkType.CELLULAR_UNKNOWN:
                result.put("networkType", "Unknown");
                break;
            case NetworkType.CELLULAR_UNIDENTIFIED_GEN:
                result.put("networkType", "Cellular Unidentified Generation");
                break;
            case NetworkType.CELLULAR_2G:
                result.put("networkType", "Cellular 2G");
                break;
            case NetworkType.CELLULAR_3G:
                result.put("networkType", "Cellular 3G");
                break;
            case NetworkType.CELLULAR_4G:
                result.put("networkType", "Cellular 4G");
                break;
            case NetworkType.WIFI_WIFIMAX:
                result.put("networkType", "WIFI/WIFIMAX");
                break;
            case NetworkType.UNKNOWN:
            default:
                result.put("networkType", "Unknown");
                break;
        }
        return result;
    }

    private HashMap<String, Object> getLocationInfo() {

        EasyLocationMod easyLocationMod = new EasyLocationMod(this.context);

        HashMap<String, Object> result = new HashMap<>();

        double[] ll = easyLocationMod.getLatLong();

        result.put("latt", String.valueOf(ll[0]));
        result.put("long", String.valueOf(ll[1]));

        return result;
    }

    private HashMap<String, Object> getAbiInfo() {

        EasyCpuMod easyCpuMod = new EasyCpuMod();

        HashMap<String, Object> result = new HashMap<>();

        StringBuilder supportABI = new StringBuilder();
        for (String abis : easyCpuMod.getSupportedABIS()) {
            supportABI.append(abis).append("\n");
        }
        String supportedABI = supportABI.toString();

        result.put("supportedABI", supportedABI);

        StringBuilder support32ABI = new StringBuilder();
        for (String abis : easyCpuMod.getSupported32bitABIS()) {
            support32ABI.append(abis).append("\n");
        }
        String supported32ABI = support32ABI.toString();

        result.put("supported32ABI", supported32ABI);

        StringBuilder support64ABI = new StringBuilder();
        for (String abis : easyCpuMod.getSupported64bitABIS()) {
            support64ABI.append(abis).append("\n");
        }
        String supported64ABI = support64ABI.toString();

        result.put("supported64ABI", supported64ABI);

        return result;
    }
}
