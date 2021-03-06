package com.kiarra.volleyparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final static String URL = "https://jsonplaceholder.typicode.com/users";
    private final static String URL_String = "https://age-of-empires-2-api.herokuapp.com/api/v1/units";
    private final static String URL_EQ = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";

    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        //getStringObject(URL_String);
        getJsonObject(URL_EQ);

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
//                URL,null, new Response.Listener<JSONObject>()
//        {
//            @Override
//            public void onResponse(JSONObject response)
//               try {
//                    Log.d("Object: ", response.getString("username").toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener()
//        {
//            @Override
//            public void onErrorResponse(VolleyError error)
//            {
//                VolleyLog.d("Error", error.getMessage());
//            }
//        });
//
//        queue.add(jsonObjectRequest);
    }

    private void getJsonObject(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    Log.d("Object: ", response.getString("type").toString());
//
                    JSONObject metadata = response.getJSONObject("metadata");

                    Log.d("Metadata: ", metadata.toString());
                    Log.d("MetaInfo: ", metadata.getString("generated"));
                    Log.d("Metadata: ", metadata.getString("url"));
                    Log.d("Metadata: ", metadata.getString("title"));

                    //jsonARray
                    JSONArray features = response.getJSONArray("features");
                    for(int i = 0; i <features.length(); i++){
                        //Get objects
                        JSONObject propertiesObj = features.getJSONObject(i).getJSONObject("properties");
                        Log.d("Place: ", propertiesObj.getString("place"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    public void getStringObject(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            Log.d("My string: ", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
