package com.example.fragrancestore;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

import android.os.StrictMode;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebServices {
    static final String authorization = "Authorization";
    static final String authorizationValue = "Basic TEFQVE9QLUU5VjRKN0JIXEJPR0RJOkV1c3VudHlha3VidTEu";
    static final String contentType = "Content_type";
    static final String contentTypeValue = "application/json; charset=UTF-8";
    static final String accept = "Accept";
    static final String acceptValue = "*/*";
    static final String applicationJson = "application/json";
    static final String ifMatch = "If-Match";
    static final String detailsUpdated = "The details were updated successfully.";
    static final String detailsNotUpdated = "The details couldn't be updated";
    static final String successfullyRegistered = "Successfully registered";
    static final String successfullyLogged = "Successfully logged in";
    static final String emailExist = "The email address already exist";
    static final String emailPasswordIncorrect = "Email or password incorrect";

    public enum authentication{
        REGISTER,
        LOGIN
    }

    public String sendPostRequest(String url, JSONObject body, authentication auth) {
        String responseBody = "";
        OkHttpClient client = new OkHttpClient();
        int responseCode = 0;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Request request = new Request.Builder()
                .url(url)
                .addHeader(authorization, authorizationValue)
                .addHeader(contentType, contentTypeValue)
                .addHeader(accept, acceptValue)
                .post(RequestBody.create(body.toString(), MediaType.parse(applicationJson))).build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            responseCode = response.code();
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return showMessageToUser(responseCode, responseBody, auth);
    }

    public String sendPutRequest(String url, JSONObject body, String odataEtag) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        int responseCode = 0;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Request request = new Request.Builder()
                .url(url)
                .addHeader(authorization, authorizationValue)
                .addHeader(contentType, contentTypeValue)
                .addHeader(accept, acceptValue)
                .addHeader(ifMatch, odataEtag)
                .put(RequestBody.create(body.toString(), MediaType.parse(applicationJson))).build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            responseCode = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (responseCode == HTTP_OK)
            return detailsUpdated;
        return detailsNotUpdated;
    }

    public Pair<Integer, String> sendGetRequest(String url, String filter) {
        Pair<Integer, String> responseCodeBody = null;
        OkHttpClient client = new OkHttpClient();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Request request = new Request.Builder()
                .url(url + filter)
                .addHeader(authorization, authorizationValue)
                .addHeader(contentType, contentTypeValue)
                .addHeader(accept, acceptValue)
                .get().build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            responseCodeBody = Pair.create(response.code(), response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseCodeBody;
    }

    public Pair<Integer, InputStream> sendGetRequestPicture(String url, String filter) {
        Pair<Integer, InputStream> responseCodeBody = null;
        OkHttpClient client = new OkHttpClient();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Request request = new Request.Builder()
                .url(url + filter)
                .addHeader(authorization, authorizationValue)
                .addHeader(contentType, contentTypeValue)
                .addHeader(accept, acceptValue)
                .get().build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            responseCodeBody = Pair.create(response.code(), response.body().byteStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseCodeBody;
    }

    public Pair<Integer, String> sendDeleteRequest(String url, String odataEtag) {
        Pair<Integer, String> responseCodeBody = null;
        OkHttpClient client = new OkHttpClient();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Request request = new Request.Builder()
                .url(url)
                .addHeader(authorization, authorizationValue)
                .addHeader(contentType, contentTypeValue)
                .addHeader(accept, acceptValue)
                .addHeader(ifMatch, odataEtag)
                .delete().build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            responseCodeBody = Pair.create(response.code(), response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseCodeBody;
    }

    private String showMessageToUser(int responseCode, String responseBody, authentication auth){
        switch (auth) {
            case REGISTER:
                if (responseCode == HTTP_CREATED)
                    return(successfullyRegistered);
                return(emailExist);
            case LOGIN:
                if (responseCode == HTTP_CREATED){
                    try {
                        JSONObject jsonResponse = new JSONObject(responseBody);

                        if (jsonResponse.get("check").toString().equals("true"))
                            return(successfullyLogged);
                        return(emailPasswordIncorrect);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        }

        return "";
    }
}
