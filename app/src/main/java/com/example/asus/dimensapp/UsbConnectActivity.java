package com.example.asus.dimensapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

/**
 * Created by ASUS on 2017/12/14.
 */
public class UsbConnectActivity extends AppCompatActivity {

    private static final String ACTION_DEVICE_PERMISSION = "USB_PERMISSION_CONNECT";
    private UsbManager usbManager;
    private UsbPermissionReceiver usbPermissionReceiver;
    private Map<String, UsbDevice> usbList;
    private TextView tv_usb_device_list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_usb_device_list = (TextView) findViewById(R.id.tv_usb_device_list);
    }

    public void getUsb(View view){

        usbManager = (UsbManager) getApplicationContext().getSystemService(Context.USB_SERVICE);
        //获取usb设备列表
//        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber.findAllDrivers(manager)
//        usbList = usbManager.getDeviceList();
//        tv_usb_device_list.setText(usbList.size()+"");
    }

    //获取读取usb权限
    private void getPermission(UsbDevice usbDevice){
        if (!usbManager.hasPermission(usbDevice)) {
            usbPermissionReceiver = new UsbPermissionReceiver();
            //申请权限
            Intent intent = new Intent(ACTION_DEVICE_PERMISSION);
            PendingIntent mPermissionIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
            IntentFilter permissionFilter = new IntentFilter(ACTION_DEVICE_PERMISSION);
            getApplicationContext().registerReceiver(usbPermissionReceiver, permissionFilter);
            usbManager.requestPermission(usbDevice, mPermissionIntent);
        }
    }

    //监听获取usb读取权限广播
    private class UsbPermissionReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_DEVICE_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (device.getDeviceName().equals(usbList.get("").getDeviceName())) {
                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                            //授权成功,在这里进行打开设备操作
                            transfer();
                        } else {
                            //授权失败
                            Toast.makeText(getApplicationContext(),"授权失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    //usb输入接口
    UsbEndpoint usbEndpointIn;
    //usb输出接口
    UsbEndpoint usbEndpointOut;
    //开始数据传输
    private void transfer() {
        //获取usb数据传输接口
        UsbInterface usbInterface=usbList.get("").getInterface(0);
        for (int index = 0; index < usbInterface.getEndpointCount(); index++) {
            UsbEndpoint point = usbInterface.getEndpoint(index);
            if (point.getType() == UsbConstants.USB_ENDPOINT_XFER_BULK) {
                if (point.getDirection() == UsbConstants.USB_DIR_IN) {
                    usbEndpointIn = point;
                } else if (point.getDirection() == UsbConstants.USB_DIR_OUT) {
                    usbEndpointOut = point;
                }
            }
        }
        if (usbEndpointIn!=null){
            //获取usb数据获取连接器
//            UsbDeviceConnection usbDeviveConn=usbManager.openDevice(usbList.get(""));
//            UsbConfiguration configuration=new UsbConfiguration()
//            usbDeviveConn.setConfiguration()
        }
    }

}
