package org.mislab.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    public static final MediaType JSON;
    public static final Logger LOGGER;
    
    static {
        JSON = MediaType.parse("application/json; charset=utf-8");
        LOGGER = Logger.getLogger(Utils.class.getName());
    }
    
    private static Response sendRequest(OkHttpClient client, Request request) {
        Response response;
        
        try {
            com.squareup.okhttp.Response r = client.newCall(request).execute();
            
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(r.body().string(), JsonObject.class);
            response = new Response(json);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            
            response = new Response(ErrorCode.NetworkError);
        }
        
        return response;
    }
    
    public static Response post(OkHttpClient client, String url, JsonObject json) {
        RequestBody body = RequestBody.create(JSON, json.toString());
        
        Request request = new Request.Builder()
                .url(Config.HOST + url)
                .post(body)
                .build();
        
        return sendRequest(client, request);
    }
    
    public static Response get(OkHttpClient client, String url) {
        Request request = new Request.Builder()
                .url(Config.HOST + url)
                .build();
        
        return sendRequest(client, request);
    }
    
    public static String getIPAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            
            return "localhost";
        }
    }
    
    public static int doubleStr2int(String doubleStr) {
        return Double.valueOf(doubleStr).intValue();
    }
}
