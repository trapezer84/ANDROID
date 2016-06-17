package com.ktds.leina.callapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /**
                 * 사용자의 OS Vesion이 마시멜로 이상인지 체크한다.
                 * M : Marshmallow
                 * /
                 *
                /*
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    *//**
                     * 사용자 단말기의 권한 중 "전화걸기" 권한이 허용되어 있는지 체크한다.
                     *//*

                    //안드로이드는 boolean 타입을 잘 안쓴다. int로 받아와 상수와 비교한다.
                    //이유는, 1. 다양하지 못해서 2. C 기반이기 때문에 JNI 로 돌아간다. 그래서 boolean 코드를 잘 안쓴다.
                    int permissionResult = checkSelfPermission(Manifest.permission.CALL_PHONE);

                    // 패키지는 안드로이드 어플리케이션의 아이디와 같다.
                    // 다른 것을 사용하고 싶을 때도, 패키지를 쓰고 사용해야 한다.
                    // CALL_PHONE의 권한이 없을 때
                    if (permissionResult == PackageManager.PERMISSION_DENIED) {
                        *//**
                         * CALL_PHONE 권한을 한번이라도 "거부"한 적이 있는지 조사한다.
                         * 거부한 이력이 한번이라도 있다면, true를 요청한다.
                         * 거부한 이력이 없다면, false를 리턴한다.
                         *//*
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("권한이 필요합니다. ")
                                    .setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\" 기능이 필요합니다. ")
                                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                // requestPermissions 는 M 이전에는 존재하지 않음.
                                                // 그래서 내가 만드는 App의 최소버전에 젤리빈이기 때문에 젤리빈에서는 돌아가지 않아 경고창을 띄우는 것이다.
                                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                                            }
                                        }
                                    })
                                    .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(MainActivity.this, "기능을 취소했습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .create().show();
                        }
                        // 최초로 권한을 요청할 때
                        else {
                            // CALL_PHONE 권한을 Android OS에 요청한다.
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                        }
                    }
                    // CALL_PHONE의 권한이 있을 때
                    else {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 010-1111-2222"));
                        startActivity(intent);
                    }
                }
                // 사용자의 OS Vesion이 마시멜로 이하 일 때
                else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 010-1111-2222"));
                    startActivity(intent);
                }*/


                int result = new PermissionRequester.Builder(MainActivity.this)
                        .setTitle("권한 요청")
                        .setMessage("권한을 요청합니다.")
                        .setPositiveButtonName("네")
                        .setNegativeButtonName("아니요.")
                        .create()
                        .request(Manifest.permission.CALL_PHONE, 1000 , new PermissionRequester.OnClickDenyButtonListener() {
                            @Override
                            public void onClick(Activity activity) {
                                Log.d("xxx", "취소함.");
                            }
                        });

                if (result == PermissionRequester.ALREADY_GRANTED) {
                    Log.d("RESULT", "권한이 이미 존재함.");
                    if (ActivityCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-1111-1111"));
                        startActivity(intent);
                    }
                }
                else if(result == PermissionRequester.NOT_SUPPORT_VERSION)
                    Log.d("RESULT", "마쉬멜로우 이상 버젼 아님.");
                else if(result == PermissionRequester.REQUEST_PERMISSION)
                    Log.d("RESULT", "요청함. 응답을 기다림.");
            }
        });
    }

    /**
     *
     * @param requestCode
     * @param permissions : 개발자가 요청한 권한들 리스트
     * @param grantResults : 권한에 대한 응답
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            // grantResults[0] 이라고 0번째로 바로 입력한 이유는, 지금 현재 우리는 1개 밖에 사용하지 않기 때문에 그냥 0번째라고 적은 것
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 010-1111-2222"));
                // 권한이 정말 있는지 확인
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this, "권한 요청을 거부했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
