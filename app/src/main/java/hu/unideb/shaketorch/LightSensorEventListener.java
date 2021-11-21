package hu.unideb.shaketorch;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class LightSensorEventListener implements SensorEventListener {
    private TextView tv;

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public float getLux(SensorEvent sensorEvent){
        return sensorEvent.values[0];
    }
    public boolean bright(SensorEvent sensorEvent){
        return sensorEvent.values[0]<=20;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float lux = sensorEvent.values[0];
        if(lux<=20){
            tv.setText("Dark "+lux);
            tv.setTextColor(0xFFFFFFFF);
            tv.setBackgroundColor(0xFF000000);
        }
        else if(lux > 20) {
            tv.setText("Light "+lux);
            tv.setTextColor(0xFF000000);
            tv.setBackgroundColor(0xFFFFFFFF);
        }
        //tv.setText(""+lux);
       //tv.setText(lux + "\n" + tv.getText());
        //tv.setBackgroundColor(0xFF000000+(int)lux/3+(int)lux/3*256+(int)lux/3*256*256);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //TODO
    }
}
