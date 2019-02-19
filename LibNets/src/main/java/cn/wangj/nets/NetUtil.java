package cn.wangj.nets;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class NetUtil {
    private static OkHttpClient netClient;

    public static void post(String param) {
        if (netClient == null) {
            netClient = new OkHttpClient.Builder()
                    .build();
        }

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), param);
    }
}
