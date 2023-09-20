package com.example.myapplication1

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class BluetoothActivity : AppCompatActivity() {

    // Check BLE support
    private val TAG = "BLE_Sample"
    private val REQUEST_ENABLE_BT = 1
    private val SCAN_PERIOD: Long = 10000 // Scan for 10 seconds

    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothLeScanner: BluetoothLeScanner? = null

    private val scanCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            val device: BluetoothDevice? = result?.device
            device!!.let {
                Log.d(TAG, "Found BLE device: ${device.name}, ${device.address}")
                // You can connect to the device here if it meets your criteria
                connectToDevice(device)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if Bluetooth is supported on the device
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            Log.e(TAG, "Bluetooth is not supported on this device.")
            return
        }

        // Request Bluetooth permissions if not already granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN
                ),
                REQUEST_ENABLE_BT
            )
        }

        // Check if Bluetooth is enabled, if not, request to enable it
        if (!bluetoothAdapter!!.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        } else {
            startBleScan()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startBleScan() {
        bluetoothLeScanner = bluetoothAdapter?.bluetoothLeScanner

        val scanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()

        val handler = Handler()
        handler.postDelayed({
            stopBleScan()
        }, SCAN_PERIOD)

        bluetoothLeScanner?.startScan(null, scanSettings, scanCallback)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun stopBleScan() {
        bluetoothLeScanner?.stopScan(scanCallback)
    }

    private fun connectToDevice(device: BluetoothDevice) {
        // Implement your BLE device connection logic here
        // You would typically create a BluetoothGattCallback and use it to manage the connection and data transfer
        // For simplicity, we won't cover the entire connection process in this example
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                startBleScan()
            } else {
                Log.e(TAG, "Bluetooth activation request was denied.")
            }
        }

    }

}