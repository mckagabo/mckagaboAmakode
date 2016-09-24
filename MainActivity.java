package com.example.mckagabo.mckagaboapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
      RecyclerView recyclerView;
      List<TheObject> objectList=new ArrayList<>();
    String theUrl="https://datatank.stad.gent/4/toerisme/visitgentevents.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.myRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        JsonArrayRequest jreq = new JsonArrayRequest(theUrl, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            TheObject object=new TheObject();
                            try {
                                JSONObject jo = response.getJSONObject(i);
                                object.setTitle(jo.getString("title"));
                                Pattern p = Pattern.compile("\"([^\"]*)\"");
                                Matcher m = p.matcher(jo.getString("images"));
                                while(m.find())
                                {
                                    try {
                                        URL url=new URL(m.group(1));
                                       // String newUrl=url.getPath();;
                                        object.setImagUrl(url.toString());
                                    } catch (MalformedURLException e) {
                                        Toast.makeText(MainActivity.this,"le probleme est:"+e.getMessage(), Toast.LENGTH_LONG).show();
                                    }


                                }

                                objectList.add(object);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        RecyclerViewAdapter myAdapter=new RecyclerViewAdapter(objectList);
                        recyclerView.setAdapter(myAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Ikibazo giteye gitya:"+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(jreq);
    }

}
