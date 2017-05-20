package eu.highq.qonverter.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.List;

/**
 * Handles BLE stuff
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BLEManager {
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
    private ScanFilter mScanFilter;
    private ScanSettings mScanSettings;
    private BLEManagerCallbacks bleManagerCallbacks;

    public interface BLEManagerCallbacks {
        void onRequestEnableBluetooth();
        void onNewScanResult(Reading reading, byte[] rawData);
    }

    public BLEManager(BLEManagerCallbacks bleManagerCallbacks) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        this.bleManagerCallbacks = bleManagerCallbacks;
    }

    public void startListening() {
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            bleManagerCallbacks.onRequestEnableBluetooth();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();

                setScanFilter();
                setScanSettings();
            }

            scanLeDevice(true);
        }
    }

    public void stopListening() {
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            scanLeDevice(false);
        }
    }

    private void scanLeDevice(final boolean enable) {
        if (enable) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //noinspection deprecation
                mBluetoothAdapter.startLeScan(mLeScanCallback);
            } else {
                mBluetoothLeScanner.startScan(Collections.singletonList(mScanFilter), mScanSettings, mScanCallback);
            }
        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //noinspection deprecation
                mBluetoothAdapter.stopLeScan(mLeScanCallback);
            } else {
                mBluetoothLeScanner.stopScan(mScanCallback);
            }
        }
    }

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            ScanRecord mScanRecord = result.getScanRecord();
            if (mScanRecord != null) {
                byte[] manufacturerData = mScanRecord.getManufacturerSpecificData(65535);

                if (manufacturerData != null) {
                    newAdvertisementData(manufacturerData);
                }
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice bluetoothDevice, int rssi, byte[] scanRecord) {
            AdRecordList records = AdRecordList.parseScanRecord(scanRecord);

            final byte[] manufacturerData = records.getManufacturerSpecificData(65535);

            if (manufacturerData != null) {
                newAdvertisementData(manufacturerData);
            }
        }
    };

    private void newAdvertisementData(byte[] manufacturerData) {
        ByteBuffer buffer = ByteBuffer.wrap(manufacturerData).order(ByteOrder.LITTLE_ENDIAN);
        Reading reading = new Reading(buffer);

        bleManagerCallbacks.onNewScanResult(reading, manufacturerData);
    }

    private void setScanFilter() {
        ScanFilter.Builder mBuilder = new ScanFilter.Builder();

        mBuilder.setManufacturerData(65535, null);
        mBuilder.setDeviceName("CurrentSensor");

        mScanFilter = mBuilder.build();
    }

    private void setScanSettings() {
        ScanSettings.Builder mBuilder = new ScanSettings.Builder();

//        mBuilder.setReportDelay(0);
        mBuilder.setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY);

        mScanSettings = mBuilder.build();
    }
}
