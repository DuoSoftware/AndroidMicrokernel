package com.duoworld.framework.mobilemicrokernel.helpers;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestInvoker extends AsyncTask<RestRequest, Void, RestResponse> {

    RestListener listener;

    public void Get (String url, String[] headers, RestListener listener){
        this.listener = listener;
        this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,new RestRequest(url, headers));
    }

    @Override
    protected RestResponse doInBackground(RestRequest... args) {
        RestRequest request = args[0];
        RestResponse response = new RestResponse();

        try {
            URL url = new URL(request.getUrl());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                response.setResponse(stringBuilder.toString());
                response.setCode(urlConnection.getResponseCode());
            }catch (Exception e){
                response.setCode(0);
                response.setResponse(e.getMessage());
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            response.setCode(0);
            response.setResponse(e.getMessage());
        }

        return response;
    }

    @Override
    protected void onPostExecute(RestResponse response) {
        super.onPostExecute(response);
        if (listener !=null)
            listener.OnResponse(response);
    }
}
