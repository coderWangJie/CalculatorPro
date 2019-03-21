package cn.wangj.nets;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetUtil {
    private static OkHttpClient netClient;

    private static LogInterceptor logInterceptor;

    public static void post(String param) {
        if (netClient == null) {

            logInterceptor = new LogInterceptor();
            netClient = new OkHttpClient.Builder()
                    .addInterceptor(logInterceptor).build();
        }

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), param);
    }

    /**
     * 日志拦截器
     */
    static class LogInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            chain.request().body().contentType().toString();
            return null;
        }
    }
}
