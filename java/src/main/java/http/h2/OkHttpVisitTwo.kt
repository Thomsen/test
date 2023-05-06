package http.h2

import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import java.io.IOException
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.*
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class OkHttpVisitTwo {

//    internal var client = getClient()
//
//    @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
//    fun getClient(): OkHttpClient {
//
//        val trustManager = object : X509TrustManager {
//            @Throws(CertificateException::class)
//            override fun checkClientTrusted(x509Certificates: Array<X509Certificate>, s: String) {
//
//            }
//
//            @Throws(CertificateException::class)
//            override fun checkServerTrusted(x509Certificates: Array<X509Certificate>, s: String) {
//
//            }
//
//            override fun getAcceptedIssuers(): Array<X509Certificate> {
//                //                return new X509Certificate[0];
//                return arrayOf()
//            }
//        }
//
//        val trustManagers = arrayOf<TrustManager>(trustManager)
//
//        val sslContext = SSLContext.getInstance("SSL")
//        sslContext.init(null, trustManagers, java.security.SecureRandom())
//
//        val sslSocketFactory = sslContext.socketFactory
//
//        val builder = OkHttpClient.Builder()
//
//        builder.sslSocketFactory(sslSocketFactory, trustManager)
//        builder.hostnameVerifier { hostname, session -> true }
//
//        // jdk 1.8.0_121 need -Xbootclasspath/p:/Users/thom/.m2/repository/org/mortbay/jetty/alpn/alpn-boot/8.1.11.v20170118/alpn-boot-8.1.11.v20170118.jar
//
//        var protocols = ArrayList<Protocol>();
//        protocols.add(Protocol.HTTP_2)
//        protocols.add(Protocol.HTTP_1_1)
//
//        builder.protocols(protocols)
//
//        return builder.build()
//    }
//
////    val URL = "https://http2.akamai.com/";
//    val URL = "https://localhost:8082/greeting";
//
//    @Throws(IOException::class)
//    fun run() {
//        val request = Request.Builder()
//                .url(URL)
//                .build()
//
//        val response = client.newCall(request)
//                .execute()
//
//        if (!response.isSuccessful) {
//            throw IOException("unexpected code " + response)
//        }
//
//        val responseHeaders = response.headers()
//
//        println("protocol: " + response.protocol().toString())
//
//        for (i in 0 until responseHeaders.size()) {
//            println(responseHeaders.name(i) + " : " + responseHeaders.value(i))
//        }
//
//        println("visit two body: " + response.body()!!.string())
//    }
//
//    companion object {
//
//        @JvmStatic
//        fun main(args: Array<String>) {
//
//            val visit = OkHttpVisitTwo()
//
//            try {
//                visit.run()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//
//        }
//    }
}