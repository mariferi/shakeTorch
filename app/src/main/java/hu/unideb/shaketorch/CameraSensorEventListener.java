package hu.unideb.shaketorch;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class CameraSensorEventListener implements SensorEventListener {
    private TextView tv;

    public void setTv(TextView tv) {
        this.tv = tv;
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //TODO
    }
}
