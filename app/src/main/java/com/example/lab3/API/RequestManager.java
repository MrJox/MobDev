package com.example.lab3.API;
import android.os.AsyncTask;
import com.example.lab3.API.Constants;
import com.example.lab3.Models.ResponseModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RequestManager extends AsyncTask<String, Void, ResponseModel> {
    private static int timeout;
    private URL url;
    private String method;
    private Map<String, Object> body;

    public RequestManager(String url, String method, Map<String, Object> body) throws MalformedURLException {
        // give it 15 seconds to respond
        timeout = 15 * 1000;
        this.url = new URL(Constants.BASE_URL + url);
        this.method = method;
        this.body = body;
    }

    @Override
    protected ResponseModel doInBackground(String... params) {
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
            // set request method
            connection.setRequestMethod(this.method);
            // set content type header
            if (this.method.equals(Methods.GET)) {
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            } else {
                connection.setRequestProperty("Content-Type", "application/json");
            }
            connection.setDoOutput(true);
            // set timeout
            connection.setReadTimeout(timeout);

            // set body
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            String requestJson = new Gson().toJson(this.body);
            osw.write(requestJson);
            osw.flush();
            osw.close();
            os.close();

            // send request
            connection.connect();

            int responceCode = connection.getResponseCode();
            // read the output from the server
            if (responceCode == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String line;
            stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            String responseJson = stringBuilder.toString();
            return new Gson().fromJson(responseJson, ResponseModel.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
        return new ResponseModel();
    }
}
