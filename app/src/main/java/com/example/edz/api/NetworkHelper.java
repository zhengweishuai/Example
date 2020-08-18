package com.example.edz.api;

import com.constant.AppConfigs;
import com.kthttp.BaseNetwork;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author : zhengweishuai
 * date : 2020/6/6 0006.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：
 */
public class NetworkHelper {
    private static IAPI help;

    public static IAPI newInstance() {
        if (help == null) {
            synchronized (NetworkHelper.class) {
                if (help == null) {
                    BaseNetwork baseNetwork = new BaseNetwork();
                    help = baseNetwork.init(IAPI.class, AppConfigs.base_url);
                }
            }
        }
        return help;
    }
}

/**
 *
 */
class ClientUploadUtils {

    static ResponseBody upload(String url, String filePath, String fileName) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName,
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath)))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response.body();
    }


    static void main(String[] args) throws IOException {
        try {
            String fileName = "com.jdsoft.biz.test.zip";
            String filePath = "D:\\ExtJsTools\\Sencha\\Cmd\\repo\\pkgs\\test.zip";
            String url = "http://localhost:9990/upload_app_package";
            System.out.println(upload(url, filePath, fileName).string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
