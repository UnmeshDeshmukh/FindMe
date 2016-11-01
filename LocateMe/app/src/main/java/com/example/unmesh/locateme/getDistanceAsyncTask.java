package com.example.unmesh.locateme;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.security.AccessController.getContext;

/**
 * Created by Unmesh on 10/25/2016.
 */

public class getDistanceAsyncTask extends AsyncTask<String, Void, String>{

    Context context;
    Double timeTaken;
    ProgressDialog progressDialog;
    JsonDisplayInterface jsonDisplayInterface;

    public getDistanceAsyncTask(Context context){
        this.context = context;
        jsonDisplayInterface = (JsonDisplayInterface) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading the Data");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(result==null){
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,MapScreenActivity.class);
            progressDialog.dismiss();
            context.startActivity(intent);


        }else{
            jsonDisplayInterface.display(result);
            progressDialog.dismiss();
        }

    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            System.out.println(strings[0]);
            URL url = new URL(strings[0]);

            HttpURLConnection connectionObject = (HttpURLConnection) url.openConnection();
            connectionObject.setRequestMethod("GET");
            int responseCode = connectionObject.getResponseCode();
            if(responseCode==HttpURLConnection.HTTP_OK){
                BufferedReader buff = new BufferedReader(new InputStreamReader(connectionObject.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line= buff.readLine();
                while((line !=null)){
                    stringBuilder.append(line);
                    line = buff.readLine();
                }
                String json = stringBuilder.toString();
                JSONObject rootObject = new JSONObject(json);
                JSONArray destAddress = rootObject.getJSONArray("destination_addresses");
                JSONArray originAddress = rootObject.getJSONArray("origin_addresses");
                JSONArray arrayRow = rootObject.getJSONArray("rows");
                JSONObject rowObjects=arrayRow.getJSONObject(0);
                JSONArray array_elements=rowObjects.getJSONArray("elements");
                JSONObject elementDetails=array_elements.getJSONObject(0);
                JSONObject timeDuration=elementDetails.getJSONObject("duration");
                JSONObject totalDistance=elementDetails.getJSONObject("distance");

                return destAddress.getString(0) +"/"+ originAddress.getString(0) + "/" + timeDuration.getString("text")+"/"+totalDistance.getString("text")+"/";

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
