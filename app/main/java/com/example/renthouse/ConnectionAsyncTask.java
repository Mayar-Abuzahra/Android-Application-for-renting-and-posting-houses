package com.example.renthouse;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;

public class ConnectionAsyncTask extends AsyncTask<String, String,String> {

    Activity activity;

    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ((MainActivity) activity).showToast("Connecting");
        super.onPreExecute();
        ((MainActivity) activity).setProgress(true);
    }

    @Override
    protected String doInBackground(String... params) {
        String data = HttpManager.getData(params[0]);
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        ((MainActivity) activity).setProgress(false);
        ((MainActivity) activity).Connected(true);
        ((MainActivity) activity).showToast("Connected");
        List<DummyData> dummyData = DummyDatatoJasonParser.getObjectFromJason(s);
        System.out.println("List from json objects="+dummyData);



    }
}