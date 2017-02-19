package com.example.sangh.soop;

import java.io.IOException;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by koohanmo on 2017-02-19.
 */
public class NetworkRequests {


    private static OkHttpClient client = new OkHttpClient();
    private static NetworkRequests ourInstance = new NetworkRequests();

    public static NetworkRequests getInstance() {
        return ourInstance;
    }

    private NetworkRequests() {

    }

    public void getAriticle(String url,String date, Callback callback) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse(url)
                .newBuilder()
                .addQueryParameter("time", "\""+date+"\"");
        url = urlBuilder.build().toString();
        AppLog.i("REQUEST",url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
