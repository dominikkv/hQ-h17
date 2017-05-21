package eu.highq.qonverter;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import eu.highq.qonverter.ble.BLEManager;
import eu.highq.qonverter.ble.Reading;
import eu.highq.qonverter.ble.Utils;

public class ActMeasure extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 5682;

    private BLEManager bleManager;
    private long lastTime = 0;
    private double energy = 0.0;

    private TextView lblWatt;
    private TextView lblEnergy;
    private TextView lblStrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "BLE not supported", Toast.LENGTH_SHORT).show();
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_measure);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lblWatt = (TextView) findViewById(R.id.lblWatt);
        lblEnergy = (TextView) findViewById(R.id.lblEnergy);
        lblStrom = (TextView) findViewById(R.id.lblStrom);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("energy", energy);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bleManager = new BLEManager(new BLEManager.BLEManagerCallbacks() {
            @Override
            public void onRequestEnableBluetooth() {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }

            @Override
            public void onNewScanResult(final Reading reading, final byte[] rawData) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String hex = Utils.bytesToHex(rawData);

                        double watt = reading.getCurrentAC() * 230;

                        if ((lastTime != 0) && (lastTime < reading.getTimestamp())) {
                            long timeDiff = reading.getTimestamp() - lastTime;
                            energy += watt * timeDiff;
                        }

                        lastTime = reading.getTimestamp();

                        lblWatt.setText(String.format("%.2f", watt) + " Watt");
                        lblEnergy.setText(String.format("%.2f", energy / 1000.0) + " WattSek");
                        lblStrom.setText(String.format("%.2f", reading.getCurrentAC()) + "A");
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        bleManager.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();

        bleManager.stopListening();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Bluetooth not enabled.
                finish();
                return;
            }

            bleManager.startListening();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
