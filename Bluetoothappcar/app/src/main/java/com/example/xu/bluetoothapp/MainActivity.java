package com.example.xu.bluetoothapp;


import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView myLabel;

    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    OutputStream mmOutputStream;
    BroadcastReceiver mReceiver;
    Button mainled,greenled,blueled,redled,yellowled,fan;
    boolean b_main=false,b_green=false,b_blue=false,b_red=false,b_yellow=false,b_fan=false;
    SeekBar seekBar;
    boolean blv=true;
    private static final int LOCATION_REQUEST = 1;
    private static final int PhoneState_REQUEST = 0;
    private ArrayAdapter mArrayAdapter;
     List lstDevices=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refactor);
        getNetworkFix();
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter==null){
            myLabel.setText("no bluetooth");
        }
        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBluetooth=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth,0);
        }
        mainled=(Button)findViewById(R.id.mainled);
        greenled=(Button)findViewById(R.id.greenled);
        blueled=(Button)findViewById(R.id.blueled);
        redled=(Button)findViewById(R.id.redled);
        yellowled=(Button)findViewById(R.id.yellowled);
        fan = findViewById(R.id.fan);
        seekBar = findViewById(R.id.seekBar);

        listView=(ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBluetoothAdapter.cancelDiscovery();
                if(mmDevice!=null && mmOutputStream!=null && mmSocket.isConnected()){
                    try {
                        mmOutputStream.close();
                        mmSocket.close();
                        mmDevice=null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                String info=((TextView)view).getText().toString();
                String address=info.substring(info.length()-17);

                myLabel.setText(address);
                mmDevice=mBluetoothAdapter.getRemoteDevice(address);

                try {
                    openBT();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        myLabel=(TextView)findViewById(R.id.label);
        mArrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,lstDevices);




        mainled.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mBluetoothAdapter.isEnabled()&&mmDevice!=null){
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        try {
                            if(!b_main){
                                mainled.setText("CLOSE_MAIN_LED");
                                b_main = true;
                            }else{
                                mainled.setText("OPEN_MAIN_LED");
                                b_main = false;
                            }
                            mmOutputStream.write('M');

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                return true;
            }
        });

        greenled.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mBluetoothAdapter.isEnabled()&&mmDevice!=null){
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        try {
                            if(!b_green){
                                greenled.setText("CLOSE_GREEN_LED");
                                b_green = true;
                            }else{
                                greenled.setText("OPEN_GREEN_LED");
                                b_green = false;
                            }
                            mmOutputStream.write('G');

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                return true;
            }
        });

        blueled.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mBluetoothAdapter.isEnabled()&&mmDevice!=null){
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        try {
                            if(!b_blue){
                                blueled.setText("CLOSE_BLUE_LED");
                                b_blue = true;
                            }else{
                                blueled.setText("OPEN_BLUE_LED");
                                b_blue = false;
                            }
                            mmOutputStream.write('B');

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                return true;
            }
        });

        redled.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mBluetoothAdapter.isEnabled()&&mmDevice!=null){
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        try {
                            if(!b_red){
                                redled.setText("CLOSE_RED_LED");
                                b_red = true;
                            }else{
                                redled.setText("OPEN_RED_LED");
                                b_red = false;
                            }
                            mmOutputStream.write('R');

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                return true;
            }
        });

        yellowled.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mBluetoothAdapter.isEnabled()&&mmDevice!=null){
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        try {
                            if(!b_yellow){
                                yellowled.setText("CLOSE_YELLOW_LED");
                                b_yellow = true;
                            }else{
                                yellowled.setText("OPEN_YELLOW_LED");
                                b_yellow = false;
                            }
                            mmOutputStream.write('Y');

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                return true;
            }
        });

        fan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mBluetoothAdapter.isEnabled()&&mmDevice!=null){
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        try {
                            if(!b_fan){
                                fan.setText("CLOSE_FAN");
                                b_fan = true;
                            }else{
                                fan.setText("OPEN_FAN");
                                b_fan = false;
                            }
                            mmOutputStream.write('F');

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                return true;
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mBluetoothAdapter.isEnabled()&&mmDevice!=null){
                    try {
                        if(progress!='M' && progress!='G' && progress!='B' && progress!='R' && progress!='F' && progress!='Y')
                            mmOutputStream.write(progress);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findBT();


    }
    @TargetApi(Build.VERSION_CODES.M)
    public void getNetworkFix() {

        if ( !checkLocationPermission() )
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST);

    }
    @TargetApi(Build.VERSION_CODES.M)
    private boolean checkLocationPermission() {

        int api_version = Build.VERSION.SDK_INT;
        String android_version = Build.VERSION.RELEASE;
        if(api_version < Build.VERSION_CODES.M && !android_version.matches("(6)\\..+")) return true;

        return (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }
    @TargetApi(Build.VERSION_CODES.M)
    private boolean checkPhoneStatePermission() {

        int api_version = Build.VERSION.SDK_INT;
        String android_version = Build.VERSION.RELEASE;
        if(api_version < Build.VERSION_CODES.M && !android_version.matches("(6)\\..+")) return true;

        return (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == LOCATION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                getNetworkFix();
            else
                Toast.makeText(this, "需要您的授權使用定位功能", Toast.LENGTH_SHORT).show();
        }

        else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    void findBT(){
        blv=false;

        mBluetoothAdapter.startDiscovery();
         mReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if(device.getBondState()==BluetoothDevice.BOND_BONDED){
                        String str="paired devices : "+device.getName() + "\n" + device.getAddress();
                        if(lstDevices.indexOf(str)==-1)
                            lstDevices.add(str);
                    }
                    if(device.getBondState()==BluetoothDevice.BOND_NONE){
                        String str=device.getName() + "\n" + device.getAddress();
                        if(lstDevices.indexOf(str)==-1)
                            lstDevices.add(str);
                    }

                    listView.setAdapter(mArrayAdapter);
                }

            }
        };
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

    }


    protected void onDestroy() {
        this.unregisterReceiver(mReceiver);
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }




    void openBT() throws IOException{
        UUID uuid=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        if(mmDevice!=null){
            mmSocket=mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream=mmSocket.getOutputStream();


            myLabel.setText("Bluetooth Opened :"+mmDevice.getName()+""+mmDevice.getAddress());
        }
    }






}
