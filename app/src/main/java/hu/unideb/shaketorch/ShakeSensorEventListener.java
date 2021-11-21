package hu.unideb.shaketorch;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

public class ShakeSensorEventListener implements SensorEventListener {
    private TextView tv;

    public void setTv(TextView tv) {
        this.tv = tv;
    }
    public  boolean shake =false;
    // variables for shake detection
    private static final float SHAKE_THRESHOLD = 3.25f; // m/S**2
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;



    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                shake=false;
                tv.setText("Acceleration is " + acceleration + "m/s^2");//No Shake
                if (acceleration > SHAKE_THRESHOLD) {
                    mLastShakeTime = curTime;
                    shake=true;
                    tv.setText( "Shake, Rattle, and Roll");//Shake
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //TODO
    }
}
