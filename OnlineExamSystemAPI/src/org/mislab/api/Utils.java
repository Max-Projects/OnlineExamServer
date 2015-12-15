package org.mislab.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    public static final MediaType JSON;
    public static final Logger LOGGER;
    
    static {
        JSON = MediaType.parse("application/json; charset=utf-8");
        LOGGER = Logger.getLogger(Utils.class.getName());
    }
    
    public static Response post(OkHttpClient client, String url, JsonObject json) {
        RequestBody body = RequestBody.create(JSON, json.toString());
        
        Request request = new Request.Builder()
                .url(Config.HOST + url)
                .post(body)
                .build();
        
        Response response;
        
        try {
            com.squareup.okhttp.Response r = client.newCall(request).execute();
            
            Gson gson = new Gson();
            Map map = (Map) gson.fromJson(r.body().string(), Object.class);
            response = new Response(map);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            
            response = new Response(ErrorCode.NetworkError);
        }
        
        return response;
    }
    
    public static Response get(OkHttpClient client, String url) {
        Request request = new Request.Builder()
                .url(Config.HOST + url)
                .build();
        
        Response response;
        
        try {
            com.squareup.okhttp.Response r = client.newCall(request).execute();
            
            Gson gson = new Gson();
            Map map = (Map) gson.fromJson(r.body().string(), Object.class);
            response = new Response(map);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            
            response = new Response(ErrorCode.NetworkError);
        }
        
        return response;
    }
}
