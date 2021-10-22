package com.kitabisa.app.util;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.http.ResponseEntity;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class Util {
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static boolean isNullOrEmpty(String str) {
        if(str.trim () != null && !str.trim().isEmpty())
            return false;
        return true;
    }

    public static boolean isNullOrSmallerThanOne(Integer str) {
        if(str == null || str < 1)
            return true;
        return false;
    }

    public static boolean isNullOrSmallerThanZero(Integer str) {
        if(str == null || str < 0)
            return true;
        return false;
    }

    public static void getSslContext() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager () {
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
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public static void loggingHttp(ResponseEntity<?> resEnt, String url ) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** LOG START ****");
        stringBuilder.append("\r\nTimestamp \t: " + LocalDateTime.now());
        stringBuilder.append("\r\nURL       \t: " + url);
        stringBuilder.append("\r\nResponseHandler  \t: " +(String) resEnt.getBody());
        stringBuilder.append("\n*** LOG END ****");
        System.out.println(stringBuilder.toString());

    }

    /**
     * This method will formated log with readable log message
     *
     * @param throwable
     * @param message
     * @return
     */
    public static String loggerFormat(Throwable throwable, String message) {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        StringWriter sw = new StringWriter();
        StringBuilder stringBuilder = new StringBuilder();
        throwable.printStackTrace(new PrintWriter (sw));
        stringBuilder.append("**Log Start**");
        stringBuilder.append("\r\nTimestamp \t: " + ldt.toString(fmt));
        stringBuilder.append("\r\nMessage \t: " + message);
        stringBuilder.append("\r\nCaused By \t: " + sw.toString ());
        stringBuilder.append("\r\n**Log End**\r\n");
        return stringBuilder.toString();
    }

}
