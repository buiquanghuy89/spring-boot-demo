package com.demo.spring.boot.extractHtml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by bqhuy on 7/23/2018.
 */
public class LinkDownLoad {
    private static Logger logger = LoggerFactory.getLogger(LinkDownLoad.class);

    public static void main(String[] args) {
        List<Book> data = new ArrayList<>();
//        data.add(new Book("Ma thổi đèn - Tập 1 - Thành cổ tinh tuyệt"
//                , "D:\\Ma thổi đèn - Tập 1 - Thành cổ tinh tuyệt - Thiên Hạ Bá.htm"
//                , "Ma thổi đèn 1 - Thành cổ tinh tuyệt", ".mp3"));
//        data.add(new Book("Ma thổi đèn - Tập 2 - Mê động long lĩnh"
//                , "D:\\Ma thổi đèn - Tập 2 - Mê động long lĩnh - Thiên Hạ Bá.htm"
//                , "Ma thổi đèn 2 - Mê động long lĩnh", ".mp3"));
//        data.add(new Book("Ma thổi đèn - Tập 3 - Trùng Cốc Vân Nam"
//                , "D:\\Ma thổi đèn - Tập 3 - Trùng Cốc Vân Nam - Thiên Hạ Bá.htm"
//                , "Ma thổi đèn 3 - Trùng Cốc Vân Nam", ".mp3"));
//        data.add(new Book("Ma thổi đèn - Tập 4 - Thần Cung Côn Luân"
//                , "D:\\Ma thổi đèn - Tập 4 - Thần Cung Côn Luân - Thiên Hạ Bá.htm"
//                , "Ma thổi đèn 4 - Thần Cung Côn Luân", ".mp3"));
//        data.add(new Book("Ma thổi đèn - Tập 5 - Mộ Hoàng Bì Tử"
//                , "D:\\Ma thổi đèn - Tập 5 - Mộ Hoàng Bì Tử - Thiên Hạ Bá Xướng.htm"
//                , "Ma thổi đèn 5 - Mộ Hoàng Bì Tử", ".mp3"));
//        data.add(new Book("Ma thổi đèn - Tập 6 - Nam Hải Quy Khư"
//                , "D:\\Ma thổi đèn - Tập 6 - Nam Hải Quy Khư - Thiên Hạ Bá.htm"
//                , "Ma thổi đèn 6 - Nam Hải Quy Khư", ".mp3"));
//        data.add(new Book("Ma thổi đèn - Tập 7 - Thi Vương Tương Tây"
//                , "D:\\Ma thổi đèn - Tập 7 - Thi Vương Tương Tây - Thiên Hạ Bá.htm"
//                , "Ma thổi đèn 7 - Thi Vương Tương Tây", ".mp3"));
        data.add(new Book("Ma thổi đèn - Tập 8 - Vu Hiệp Quan Sơn"
                , "D:\\Ma thổi đèn - Tập 8 - Vu Hiệp Quan Sơn - Thiên Hạ Bá.htm"
                , "Ma thổi đèn 8 - Vu Hiệp Quan Sơn", ".mp3"));

        for (Book book : data) {
            downLoadBook(book, "E:\\iTunes\\Audio");
        }
    }

    private static void downLoadBook(Book book, String path) {
        logger.info("Start download {}", book.getTitle());
        BufferedReader br = null;
        FileReader fr = null;
        String content = "";
        try {
            fr = new FileReader(book.getLinkDownload());
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                content += sCurrentLine;
            }

            HTMLLinkExtractor extractor = new HTMLLinkExtractor();
            Vector<HtmlLink> vLink = extractor.grabHTMLLinks(content);
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
            } catch (Exception ex) {
                logger.error("", "", ex);
            }

            path = path + "\\" + book.getTitle();
            createFolder(path);
            for (HtmlLink link : vLink) {
                logger.info("Execute: {}", link);
                String[] tmp = link.getTitle().split("-");
                if (tmp.length == 3) {
                    saveFile(link.getLink(), path + "\\" + tmp[2].trim() + " - " + book.getPrefixFileName() + book.getExtension());
                }
            }
        } catch (Exception ex) {
            logger.error("", ex);
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                logger.error("", "", ex);
            }
            logger.info("End download {}", book.getTitle());
        }
    }

    private static void createFolder(String path) {
        File theDir = new File(path);
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            logger.info("creating directory: {}", theDir.getName());
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                logger.error("", "", se);
            }
            if (result) {
                logger.info("DIR created");
            }
        }
    }

    private static void saveFile(String url, String fileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            URL _url = new URL(url);
            URLConnection connection = _url.openConnection();
            is = connection.getInputStream();
            ReadableByteChannel rbc = Channels.newChannel(is);
            fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            logger.info("======> Save to {}", fileName);
        } catch (Exception ex) {
            logger.error("", "", ex);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception ex) {
                logger.error("", "", ex);
            }
        }
    }

    private static String doHttpUrlConnectionAction(String desiredUrl)
            throws Exception {
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            url = new URL(desiredUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");

            // uncomment this if you want to write output to this url
            //connection.setDoOutput(true);
            connection.addRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
