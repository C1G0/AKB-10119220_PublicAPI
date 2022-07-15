package com.wandika.akb_10119220_publicapi;

//NIM: 10119220
//Nama: Moch. Wandika Yusgiar
//Kelas: IF-6

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_search;
    EditText edit_txt_search;
    TextView txt_items, txt_name, txt_desc, txt_subCount,txt_viewCount;
    String name, twitterLink;
    int index, index2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Memasukkan Value
        btn_search = findViewById(R.id.btn_search);
        edit_txt_search = findViewById(R.id.edit_txt_search);
        txt_items = findViewById(R.id.txt_items);
        txt_name = findViewById(R.id.txt_item_name);
        txt_desc = findViewById(R.id.txt_item_desc);
        txt_subCount = findViewById(R.id.txt_item_subCount);
        txt_viewCount = findViewById(R.id.txt_item_viewCount);
        int index = new Random().nextInt(21);


        //btn Listener
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://api.holotools.app/v1/channels/";


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int index2 = Integer.parseInt(edit_txt_search.getText().toString());
                        String name = "", twitterLink = "", desc = "", subCount = "", viewCount = "";

                        JSONObject data;

                        try {
                            JSONArray vtuber = response.getJSONArray("channels");
                            //txt_items.setText("Response: " + response.toString());
                            data = vtuber.getJSONObject(index2-1);
                            name = data.getString("name");
                            twitterLink = data.getString("twitter_link");
                            desc = data.getString("description");
                            subCount = data.getString("subscriber_count");
                            viewCount = data.getString("view_count");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        txt_name.setText("Name: " + name.toString());
                        txt_subCount.setText("Subscriber: " + subCount.toString());
                        txt_viewCount.setText("View Count: "+ viewCount.toString());
                        txt_desc.setText("Channel Description: " + desc.toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
            });

                queue.add(request);

            }
        });

    }
}