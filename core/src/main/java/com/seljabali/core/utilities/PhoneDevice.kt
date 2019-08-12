package com.seljabali.core.utilities

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.provider.Settings

object PhoneDevice {

    /**
     *  Bluetooth
     */
    @JvmStatic
    fun hasBlueTooth(context: Context): Boolean = context.packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)

    @JvmStatic
    fun hasBlueToothLowEnergy(context: Context): Boolean = context.packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)

    @JvmStatic
    fun hasBlueToothService(context: Context): Boolean = (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager?)?.adapter != null

    @SuppressLint("MissingPermission")
    @JvmStatic
    fun hasBlueToothEnabled(): Boolean = BluetoothAdapter.getDefaultAdapter()?.isEnabled == true

    /**
     *  Camera
     */
    @JvmStatic
    fun hasCamera(context: Context): Boolean {
        try {
            val count = (context.getSystemService(Context.CAMERA_SERVICE) as CameraManager).cameraIdList.size
            return count > 0
        } catch (e: CameraAccessException) {
        }
        return false
    }

    @JvmStatic
    fun hasCameraFlash(context: Context): Boolean = context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

    /**
     *  GPS
     */
    @JvmStatic
    fun hasGPS(context: Context) = context.packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)

    @JvmStatic
    fun hasGPSEnabled(context: Context): Boolean = (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager).isProviderEnabled(LocationManager.GPS_PROVIDER)

    /**
     *  Wifi
     */
    @JvmStatic
    fun isWifiEnabled(context: Context): Boolean = (context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager).isWifiEnabled

    @JvmStatic
    fun isInFlightMode(context: Context): Boolean = Settings.System.getString(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON).isNotBlank()
}