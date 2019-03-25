package com.example.edz.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.edz.application.R;
import com.example.edz.utils.OrderInfoUtil2_0;
import com.example.edz.utils.PayResult;

import java.util.Map;

public class AliPayActivity extends AppCompatActivity {
    private String RSA2_PRIVATE = "MIIEowIBAAKCAQEA52VEs0yPm08vnpJLM1OJALwj1irBfpi5/LDSO1owA+H904ByqVnZxTWy42tYsm2htvxw9JIHGNnea4og8VRvPqXOesbPQx9J6D7DNZW5uVTzxlQ5PQwPP8dg1Y87t4j7+5tnzE29Mrjv72/F58nP1rNizvAo89ViwIb4n7Xbdv1D+zyXQ/pRGCWtdmzuksCaZLi6MsAdXIcZ2mqqaDKbg+5EuIpWzMMWu/04nz3b8BcoqVoIQyvalYA+fsQCvrhJBVw9NIbigofk0JnwC1Z1w9zxxil2tR0r0tj89FLgOavS73C9cPSmcT9FiZPV2mz3kAevZCzhX7m19MI73a2EdwIDAQABAoIBAQCS/Tph522GDAxE38uTZCowZFMjde+KWrwyr6QsfcLohIN3+i1cNK5oXw8u9f7XAVtr4Ppj8es+Nw/aIlmA09EC69K5HVeF3PdW55+bh2v+Nd/RCal+hQ2exylVH/KgSQ1ArFyIZI2eDeqkn80Iy6MSUMughuCM2+0qPdLrqVsrPsvZoFjdgHwj9AYZt1+faLVQlZj/WKzv0Zunpm9J1+1m6NHpHIjlSHynxb0LlhhwToYg74wE0X3nobhRA/Sy3T9Xuc6tBFAPlwz1waHtNBZsTyvS8adQ9RAWJLBydo/Zo27TqBk+GyEXj1Q2Fohy0dHerHdxR34BOYLyxDLhO9N5AoGBAPUkJME2d4E8RVcbvwPS8VPjHuqSjq5m9iBAqrU4eXdvMY4SzJYUbozsVvKpVoPYMSdCt2uGMR/lUQWmnxe4iawMD92roqvguZ6dNTltSGFBR+VCctnLILOSJYcDEhsKG06ptU+S/Q3b8JM6L/wgNQFDdeKp06z+YHeygQov+zQbAoGBAPGlQIqPJ1y/hWOuZ6KNVaBQf96vhnUi3uhpbBacsvpVrAmrR2qVD9U+GBEIJ2HkjvjmWewOKKp78U/DpaRxqldbsRh8unDkCe5Vyet9YkWgwlfyYoUpa3Bnrr2Xuc9457EjCdhe/R3p2d45wJpKGPDkD4uYszjQzQOqutD9sB7VAoGAChpkPWZORJBEV1O4aqLt+UQaCRbjNILfz/J6Vp+uOeLmSvGxZtoOuFYhOz0JaMOnOvP/9TmBmsvrq+zzD5o2alXQ7ZTeTnEjlUrrX6OGYSO8n9E+RIWfomb6jvbsbxrah0JtS4tGTKTs5FR4l86IALZUDHRtWerd9PDxaocRtlMCgYAKlutVJyDad6oozCPOg9MUy3JPF/IUgAq0tKn/864EOmznn5Hpoc6n0ajMVkcjixiY379hEN3HWtcgX7qKklCcs2/3Wr/w4o1s3GWU5FHCK2FY6U5X95C1xFVsCZIi/XFp2dc8nC0LeowJqQ4/tK+THutveqmf+8aAOqMlNXjaYQKBgEvhadSMmpr/bwwWdbaxiXstzYxwc23eR5YUTLIhezCUySv6mwIPM7oYE/rPpLWlMFNfka2l9ZxvD7pzu3IamnLhKO/apFqCUI+fREqAShgaKxvjrD8mfW+NgzfmTUezFQG+FAlSQVQTolS/uCwYONHgWDcQOUgNcievoxPf8OVe";
    private String APPID = "2018122462642667";
    private Button pay, auth;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pay = findViewById(R.id.pay);
        auth = findViewById(R.id.auth);
        requestPermission();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
                 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
                 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
                 *
                 * orderInfo 的获取必须来自服务端；
                 */
                boolean rsa2 = (RSA2_PRIVATE.length() > 0);
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

                String privateKey = RSA2_PRIVATE;
                String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
                final String orderInfo = orderParam + "&" + sign;

                final Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(AliPayActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
                 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
                 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
                 *
                 * authInfo 的获取必须来自服务端；
                 */
//                boolean rsa2 = (RSA2_PRIVATE.length() > 0);
//                Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
//                String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
//
//                String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
//                String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
//                final String authInfo = info + "&" + sign;
//                Runnable authRunnable = new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // 构造AuthTask 对象
//                        AuthTask authTask = new AuthTask(MainActivity.this);
//                        // 调用授权接口，获取授权结果
//                        Map<String, String> result = authTask.authV2(authInfo, true);
//
//                        Message msg = new Message();
//                        msg.what = SDK_AUTH_FLAG;
//                        msg.obj = result;
//                        mHandler.sendMessage(msg);
//                    }
//                };
//
//                // 必须异步调用
//                Thread authThread = new Thread(authRunnable);
//                authThread.start();
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();

                    break;
                }
                case SDK_AUTH_FLAG: {

                }
                default:
                    break;
            }
        }

        ;
    };


    /**
     * 检查支付宝 SDK 所需的权限，并在必要的时候动态获取。
     * 在 targetSDK = 23 以上，READ_PHONE_STATE 和 WRITE_EXTERNAL_STORAGE 权限需要应用在运行时获取。
     * 如果接入支付宝 SDK 的应用 targetSdk 在 23 以下，可以省略这个步骤。
     */
    private void requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);

        } else {
            Toast.makeText(this, "已有权限", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 权限获取回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

        }
    }

}
