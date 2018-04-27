package http.h2;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class OkHttpVisitOne {

    OkHttpClient client = new OkHttpClient();

    public void run() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8443/greeting")
                .build();

        Response response = client.newCall(request)
                .execute();

        if (!response.isSuccessful()) {
            throw new IOException("unexpected code " + response);
        }

        System.out.println("protocol: " + response.protocol().toString());

        Headers responseHeaders = response.headers();

        for (int i=0; i<responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + " : " + responseHeaders.value(i));
        }

        System.out.println("visit one body: " + response.body().string());
    }

    public static void main(String[] args) {

        OkHttpVisitOne visit = new OkHttpVisitOne();

        try {
            visit.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OkHttpClient getClient() throws NoSuchAlgorithmException, KeyManagementException {

        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
                return new java.security.cert.X509Certificate[]{};
            }
        };

        TrustManager[] trustManagers = new TrustManager[] {trustManager};

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustManagers, new java.security.SecureRandom());

        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.sslSocketFactory(sslSocketFactory, trustManager);
        builder.hostnameVerifier((hostname, session) -> true);
        return builder.build();
    }
}
