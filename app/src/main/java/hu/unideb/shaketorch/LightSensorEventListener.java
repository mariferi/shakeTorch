package hu.unideb.shaketorch;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LightSensorEventListener implements SensorEventListener {
    private TextView tv;
    private ImageButton btn;
    public float  lux;
    private int darkvalue=30;
    private boolean torchON =false;

    public void setTv(TextView tv) {
        this.tv = tv;
    }
    public  void setbtn(ImageButton btn){
        this.btn=btn;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        lux = sensorEvent.values[0];
        //sötét
        if(lux<=darkvalue){
            tv.setText(""+lux);
            tv.setTextColor(0xFFFFFFFF);
            tv.setBackgroundColor(0xFF000000);
            if(!torchON) {
                btn.callOnClick();
                torchON =true;
            }
        }
        //világos
        else if(lux > darkvalue) {
            tv.setText(""+lux);
            tv.setTextColor(0xFF000000);
            tv.setBackgroundColor(0xFFFFFFFF);
            if(torchON){
                btn.callOnClick();
                torchON=false;
            }
            torchON =false;
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
