package com.xf.sherlock;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import com.xf.sherlock.biz.BitmapConvert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Administrator on 2015/12/5.
 */
public class RetrofitUtils {
    public static Retrofit retrofit;

    public static Retrofit getIntance(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("https://www.12306.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient(context))
                    .build();
        }
        return retrofit;
    }


    public static OkHttpClient getClient(Context context) {
        OkHttpClient client = getUnsafeOkHttpClient();
        client.interceptors().add(new AddCookiesInterceptor(context));
        client.interceptors().add(new ReceivedCookiesInterceptor(context));
        /*client.networkInterceptors().add(new StethoInterceptor());//一个调试工具，具体搜Stetho
        return new OkClient(client);*/
        return client;
    }

    private static OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;

                }
            });

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}

