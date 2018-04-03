package com.zl.androidzxing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zl.libzxing.activity.CaptureActivity;

public class MainActivity extends AppCompatActivity {

    Button btScaner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btScaner = findViewById(R.id.bt_scaner);
        btScaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                    Toast.makeText(MainActivity.this,"请在‘设置’中允许调用摄像头权限",Toast.LENGTH_SHORT).show();
                } else {
                    Intent openCameraIntent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 0);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //扫描结束
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Log.v("result",scanResult);


        }
    }
}
