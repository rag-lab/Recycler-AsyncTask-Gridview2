package net.seila.myapplication;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    //private static final String urlJSON = "http://api.themoviedb.org/3/movie/popular?api_key=8481813f6a52db086ab5d607c8c94667";
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<ListItem> listItems;
    private String urlJSON="";


    //ragmovie
    //rodrigo
    // v3 api: 8481813f6a52db086ab5d607c8c94667
    //v4: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4NDgxODEzZjZhNTJkYjA4NmFiNWQ2MDdjOGM5NDY2NyIsInN1YiI6IjU5MTg2N2MwOTI1MTQxNTgwZDA0NTVhMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jd-mWFW4xJa95giS9CmGsKpJ5TodjHZYl40cwDyZDH8
    //api url: https://api.themoviedb.org/3/movie/550?api_key=8481813f6a52db086ab5d607c8c94667
    //http://api.themoviedb.org/3/movie/top_rated?api_key=8481813f6a52db086ab5d607c8c94667
    //http://api.themoviedb.org/3/movie/popular?api_key=8481813f6a52db086ab5d607c8c94667

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create/configure recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        GridLayoutManager  gridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        gridLayoutManager.setSpanCount(2);
        recyclerView.setLayoutManager(gridLayoutManager);



        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

        listItems = new ArrayList<>();

        urlJSON = String.format( getString(R.string.base_url_popular),getString(R.string.APIKEY));

        Log.v("RAG", urlJSON);

        try {
            new loadDataInBackground().execute(new URL(urlJSON));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        /*
        try {
            String dados = Util.getResponseFromHttpUrl(new URL(urlJSON));
            Log.v("RAG", dados);

        } catch (IOException e) {
            e.printStackTrace();
        }
*/


    /*



        try {
            new loadDataInBackground().execute(new URL(urlJSON));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        */
        //loadData();

    }



    public class loadDataInBackground extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPostExecute(String s) {

            recyclerView.setAdapter(recyclerAdapter);

            //Log.v("RAG",s.toString());
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected String doInBackground(URL... params) {

            URL urlSearch = params[0];
            String aa="";


            try {

                aa = Util.getResponseFromHttpUrl(urlSearch);

                JSONObject jsonObject = new JSONObject(aa);
                JSONArray array = jsonObject.getJSONArray("results");

                for(int i = 0;i<array.length();i++)
                {
                    JSONObject o = array.getJSONObject(i);

                    //get base poster path
                    String poster_path = getString(R.string.base_url_poster);
                    poster_path += o.getString("poster_path");

                    ListItem item = new ListItem(o.getString("original_title"), poster_path);

                    listItems.add(item);
                    Log.v("RAG", item.getTitulo() + "--" + poster_path);
                }


                recyclerAdapter = new RecyclerAdapter(listItems, getApplicationContext());



            } catch (IOException e1) {
                e1.printStackTrace();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return aa;

        }
    }


    private void loadData(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlJSON,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("results");

                            for(int i = 0;i<array.length();i++)
                            {
                                JSONObject o = array.getJSONObject(i);

                                //get base poster path
                                String poster_path = getString(R.string.base_url_poster);
                                poster_path += o.getString("poster_path");

                                ListItem item = new ListItem(o.getString("original_title"), poster_path);

                                listItems.add(item);
                                Log.v("RAG", item.getTitulo() + "--" + poster_path);
                            }

                            recyclerAdapter = new RecyclerAdapter(listItems, getApplicationContext());

                            recyclerView.setAdapter(recyclerAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
