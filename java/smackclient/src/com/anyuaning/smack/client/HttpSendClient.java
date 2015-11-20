package com.anyuaning.smack.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpSendClient {

	static {
		TrustManager[] trustAllCertificates = new TrustManager[] {
			new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] certs,
						String authType) throws CertificateException {
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] certs,
						String authType) throws CertificateException {
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}
				
			}
		};
		
		HostnameVerifier trustAllHostnames = new HostnameVerifier() {

			@Override
			public boolean verify(String hostname, SSLSession arg1) {
				return true;
			}
			
		};
		
		try {
			System.setProperty("jsse.enableSNIExtension", "false");
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCertificates, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);
			
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
//		String url = "http://localhost:5285/rest/";
		String url = "http://localhost:5286/inter/";
		String charset = "UTF-8";

		String query = "<message to=\"user2@192.168.1.130\" from=\"localhost/rest\"><body>hello rest msg 2</body></message>";

		try {
			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
			System.setProperty("http.keepAlive", "false");
			
			HttpURLConnection conn = (HttpURLConnection) new URL(url)
					.openConnection(); // httpurlconnection in order response

			conn.setDoOutput(true);
			conn.setRequestProperty("Accept-Charset", charset);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=" + charset);

			try (OutputStream output = conn.getOutputStream()) {
				output.write(query.getBytes(charset));
				output.flush();
				output.close();
			}

			int status = conn.getResponseCode();
			System.out.println("connection status: " + status);

			for (Entry<String, List<String>> header : conn.getHeaderFields()
					.entrySet()) {
				System.out.println(header.getKey() + " = " + header.getValue());
			}

			if (200 == status) {
				InputStream response = conn.getInputStream();
	
				String contentType = conn.getHeaderField("Content-Type");
				charset = null;
				for (String param : contentType.replace(" ", "").split(";")) {
					if (param.startsWith("charset=")) {
						charset = param.split("=", 2)[1];
						break;
					}
				}
	
				if (charset != null) {
					try (BufferedReader reader = new BufferedReader(
							new InputStreamReader(response, charset))) {
						for (String line; (line = reader.readLine()) != null;) {
							System.out.println("response " + line);
						}
					}
				} else {
	
				}
			} else {
				InputStream error = conn.getErrorStream();
			}
			
			List<String> cookies = conn.getHeaderFields().get("Set-Cookie");
			
			if (null != cookies) {
				for (String cookie : cookies) {
					conn.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
				}
			}


		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
