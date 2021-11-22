package hu.unideb.shaketorch;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageButton;
import android.widget.TextView;


public class ShakeSensorEventListener implements SensorEventListener {
    private TextView tv;
    private ImageButton btn;
    public Boolean shake=false;

    public  void setbtn(ImageButton btn){
        this.btn=btn;
    }
    public void setTv(TextView tv) {
        this.tv = tv;
    }

    // variables for shake detection
    private static final float SHAKE_THRESHOLD = 4.25f; // m/S**2;3.25f
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
                tv.setText(shake.toString());//No Shake
                if (acceleration > SHAKE_THRESHOLD) {
                    mLastShakeTime = curTime;
                    shake=true;
                    btn.callOnClick();
                   tv.setText(shake.toString());//Shake
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //TODO
    }


}
