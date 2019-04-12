package com.example.lab3.API;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUrlConnectionReader extends AsyncTask<String, Void, String> {
    private static int timeout;
    private URL url;
    private String method;

    public HttpUrlConnectionReader(String url, String method) throws MalformedURLException {
        // give it 15 seconds to respond
        timeout = 15 * 1000;
        this.url = new URL(Constants.BASE_URL + url);
        this.method = method;
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
            // set request method
            connection.setRequestMethod(this.method);
            // set timeout
            connection.setReadTimeout(timeout);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
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
        return "";
    }
}
