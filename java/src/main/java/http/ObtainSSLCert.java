package http;

import javax.net.ssl.*;
import javax.security.auth.Subject;
import javax.swing.*;
import java.io.*;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ObtainSSLCert {

//    private static String HOST = "";  // UnknownHostException
    private static String HOST = "";

    private static int PORT = 443;

    private static char[] PASSPHRASE = "passphare".toCharArray();

    public static void main(String[] args) throws Exception {

        File file = new File("jssecacerts");
        if (file.isFile() == false) {
            char SEP = File.separatorChar;
            File dir = new File(System.getProperty("java.home")
                    + SEP + "lib" + SEP + "security");

            file = new File(dir, "jssecacerts");
            if (file.isFile() == false) {
                file = new File(dir, "cacerts");
            }
        }

        FileInputStream in = new FileInputStream(file);

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(in, PASSPHRASE);
        in.close();

        SSLContext sslContext = SSLContext.getInstance("TLS");

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        X509TrustManager x509TrustManager = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];


        SavingTrustManager savingTrustManager = new SavingTrustManager(x509TrustManager);

        sslContext.init(null, new TrustManager[] { savingTrustManager }, null);

        SSLSocketFactory factory = sslContext.getSocketFactory();

        System.out.println("Opening connection to " + HOST + ":" + PORT + "...");

        SSLSocket sslSocket = (SSLSocket) factory.createSocket(HOST, PORT);

        sslSocket.setSoTimeout(10000);

        try {

            System.out.println("Starting SSL handshake ...");

            sslSocket.startHandshake();
            sslSocket.close();

            System.out.println();
            System.out.println("No errors, certificate is already trusted");

        } catch (SSLException e) {
            System.out.println();
            e.printStackTrace();
        }

        X509Certificate[] certificates = savingTrustManager.certificates;

        if (null == certificates) {
            System.out.println("Could not obtain server certificate chain");
            return ;
        }

        System.out.println();
        System.out.println("Server sent " + certificates.length + " certificate(s) :");
        System.out.println();

        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        MessageDigest md5 = MessageDigest.getInstance("MD5");


        for (X509Certificate cert : certificates) {
            System.out.println(" Subject " + cert.getSubjectDN());
            System.out.println(" Issuer " + cert.getIssuerDN());
            sha1.update(cert.getEncoded());
            System.out.println(" sha1 " + toHexString(sha1.digest()));
            md5.update(cert.getEncoded());
            System.out.println(" md5 " + toHexString(md5.digest()));
            System.out.println();
        }

        System.out.println("Enter certificate to add to trusted keystore ro 'q' to quit: [1]");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine().trim();

        int k = 0;
        try {
            k = (line.length() == 0) ? 0 : Integer.parseInt(line) - 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Keystore not changed");
            return ;
        }

        X509Certificate certificate = certificates[k];
        String alias = HOST + "-" +(k + 1);

        keyStore.setCertificateEntry(alias, certificate);

        OutputStream outputStream = new FileOutputStream("ca.cer");
        keyStore.store(outputStream, PASSPHRASE);
        outputStream.close();

        System.out.println();
        System.out.println(certificate);
        System.out.println();
        System.out.println("Added certificate to keystore 'ca.cer' using alias '" + alias + "'");

    }

    private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

    private static String toHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 3);

        for (int b : bytes) {
            b &= 0xff;
            builder.append(HEXDIGITS[b >> 4]);
            builder.append(HEXDIGITS[b & 15]);
            builder.append(' ');
        }

        return builder.toString();
    }

    private static class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager trustManager;

        private X509Certificate[] certificates;


        public SavingTrustManager(X509TrustManager manager) {
            trustManager = manager;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {
            throw new UnsupportedOperationException();
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {
            this.certificates = x509Certificates;
            trustManager.checkServerTrusted(certificates, authType);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
//            throw new UnsupportedOperationException();
        }
    }
}
