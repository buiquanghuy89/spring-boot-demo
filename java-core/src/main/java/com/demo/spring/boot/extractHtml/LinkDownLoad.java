package com.demo.spring.boot.extractHtml;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

/**
 * Created by bqhuy on 7/23/2018.
 */
public class LinkDownLoad {

    public static void main(String[] args) {
        BufferedReader br = null;
        FileReader fr = null;
        String content = "";
        try {
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader("D:\\logs\\Ma thổi đèn - Tập 1 - Thành cổ tinh tuyệt - Thiên Hạ Bá ....html");
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                content += sCurrentLine;
            }

            HTMLLinkExtractor extractor = new HTMLLinkExtractor();
            Vector<HtmlLink> vLink = extractor.grabHTMLLinks(content);
            for (HtmlLink link : vLink) {
                System.out.println(link);
            }

            // Create a new trust manager that trust all certificates
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Activate the new trust manager
            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            } catch (Exception e) {
            }

            // And as before now you can use URL and URLConnection
            URL url = new URL("https://drive.google.com/uc?id=0B9RchjV0aIxzY044QkVsZU5NdWs&amp;export=download");
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            // .. then download the file
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
