package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView txtX, txtY, txtZ;

    SensorManager sensorManager;
    Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        txtX.setText("X : " + x);
        txtY.setText("Y : " + y);
        txtZ.setText("Z : " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}