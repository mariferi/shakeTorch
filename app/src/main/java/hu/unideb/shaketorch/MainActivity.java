package hu.unideb.shaketorch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView sTV;
    private TextView lTV;

    //Settings
    public Boolean shake_on;
    public Boolean light_on;

    //Shake Sensor
    private  ShakeSensorEventListener shakeEL=new ShakeSensorEventListener();
    private  Sensor mShake;

    //flashlight
    private CameraManager cameraManager;
    private String cameraID;
    private boolean flashState=false;
    //private ImageButton powerButton;
    private Button powerButton;
    private Switch light_sw;
    private Switch shake_sw;

    //Light sensor
    private LightSensorEventListener lightEL = new LightSensorEventListener();
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sTV = findViewById(R.id.shakeTextView);
        lTV = findViewById(R.id.lightTextView);
        powerButton = findViewById(R.id.powerButton);
        light_sw=findViewById(R.id.light_sw);
        shake_sw=findViewById(R.id.shake_sw);


        //all sensors
        //List<Sensor> deviceSensors=sensorManager.getSensorList(Sensor.TYPE_ALL);
        //tv.setMovementMethod(new ScrollingMovementMethod());
        //tv.setText(sensorManager.getSensorList(Sensor.TYPE_ALL).toString());

        //shake
            mShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(shakeEL, mShake, SensorManager.SENSOR_DELAY_NORMAL);
            shakeEL.setTv(sTV);
            shakeEL.setbtn(powerButton);

        //light
            mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager.registerListener(lightEL, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            lightEL.setTv(lTV);
            lightEL.setbtn(powerButton);


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(lightEL, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(shakeEL,mShake,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightEL);
        sensorManager.unregisterListener(shakeEL);
    }

    public void shakeSwitched(android.view.View v){
        if (!shake_on)
            shake_on=true;
        else
            shake_on=false;
    }

    public void lightSwitched(android.view.View v){
        if (!light_on)
            light_on=true;
        else
            light_on=false;
    }

    public void powerButtonClicked(android.view.View v){
        if (!flashState)
            flashlighON();
        else
            flashlighOFF();
    }

    public  void flashlighON(){
        cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraID=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraID,true);
            flashState=true;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    public void flashlighOFF(){
        cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraID=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraID,false);
            flashState=false;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
