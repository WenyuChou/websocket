package com.polycis.websocket.ws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.DefaultSSLWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.net.ssl.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

/**
 * @author wenyu zhou
 * @version 2020-12-22
 */
@Slf4j
public class MyWebSocketClient extends WebSocketClient {
    public static final String WEBSOCKET_SERVER_URL = "wss://api.hadax.com/ws";

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        log.info("握手...");
        for (Iterator<String> it = shake.iterateHttpFields(); it.hasNext(); ) {
            String key = it.next();
            System.out.println(key + ":" + shake.getFieldValue(key));
        }
    }

    @Override
    public void onMessage(String paramString) {
        log.info("接收到消息：{}", paramString);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        String str = new String(uncompress(bytes.array()));
        //log.info("接收到消息：{}", str);
        JSONObject message = JSON.parseObject(str);
        if(message.containsKey("ping")){
            this.send("{\"pong\":"+message.getLongValue("ping")+"}");
        }else if(message.containsKey("tick")){
            int intValue = message.getJSONObject("tick").getIntValue("close");
            log.info("最新price：{}",intValue);
        }

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("异常" + e);

    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts(MyWebSocketClient appClient) {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }


            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }


            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        }};


        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            appClient.setWebSocketFactory(new DefaultSSLWebSocketClientFactory(sc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyWebSocketClient getChatClient() throws Exception {
        MyWebSocketClient chatclient = new MyWebSocketClient(new URI(WEBSOCKET_SERVER_URL));
        trustAllHosts(chatclient);
        chatclient.connectBlocking();
        return chatclient;
    }

    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        MyWebSocketClient chatClient = new MyWebSocketClient(new URI(WEBSOCKET_SERVER_URL));
        trustAllHosts(chatClient);
        chatClient.connectBlocking();
        chatClient.send("{\"sub\": \"market.btcusdt.kline.1min\",\"id\": \"id1\"}");
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            if (line.equals("close")) {
                chatclient.close();
            } else {
                chatclient.send("{\"type\":\"friend_list\"}");
            }*/
    }
}
