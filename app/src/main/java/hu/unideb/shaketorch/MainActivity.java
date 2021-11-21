package hu.unideb.shaketorch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    //Shake Sensor
    private  ShakeSensorEventListener shakeEL=new ShakeSensorEventListener();
    private  Sensor mShake;

    //flashlight
    private ImageButton powerButton;
    private boolean flashLightState;

    //Light sensor
    private LightSensorEventListener lightEL = new LightSensorEventListener();
    private TextView tv;
    private Sensor mLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)  getSystemService(Context.SENSOR_SERVICE);
        tv = findViewById(R.id.infoTextView);


        //all sensors
        //List<Sensor> deviceSensors=sensorManager.getSensorList(Sensor.TYPE_ALL);
        //tv.setMovementMethod(new ScrollingMovementMethod());
        //tv.setText(sensorManager.getSensorList(Sensor.TYPE_ALL).toString());

        //Light Sensor
        //mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //sensorManager.registerListener(lightEL, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        //lightEL.setTv(tv);
        /////


        // Listen for shakes
        mShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (mShake != null) {
            sensorManager.registerListener(shakeEL,mShake,SensorManager.SENSOR_DELAY_NORMAL);
            shakeEL.setTv(tv);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        //sensorManager.registerListener(lightEL, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //sensorManager.unregisterListener(lightEL);
    }

}