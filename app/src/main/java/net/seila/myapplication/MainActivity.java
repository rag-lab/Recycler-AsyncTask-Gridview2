package net.seila.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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


public class MainActivity extends AppCompatActivity {

    //private static final String urlJSON = "http://api.themoviedb.org/3/movie/popular?api_key=8481813f6a52db086ab5d607c8c94667";
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<ListItem> listItems;
    private String urlJSON="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create/configure recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager  gridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        listItems = new ArrayList<>();

        recyclerAdapter = new RecyclerAdapter(listItems, getBaseContext());
        recyclerView.setAdapter(recyclerAdapter);

        urlJSON = String.format( getString(R.string.base_url_popular),getString(R.string.APIKEY));


        Log.v("RAG", urlJSON);

        try {

            if(isOnline()){

                new loadDataInBackground().execute(new URL(urlJSON));
            }else{

                Toast toast = Toast.makeText(getApplicationContext(), R.string.NOINTERNET,
                        Toast.LENGTH_SHORT);
                toast.show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }



    public class loadDataInBackground extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPostExecute(String s) {

            recyclerView.setAdapter(recyclerAdapter);
            //recyclerAdapter.notifyDataStateChanged();

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

                listItems.clear();

                for(int i = 0;i<array.length();i++)
                {
                    JSONObject o = array.getJSONObject(i);

                    //get base poster path
                    String poster_path = getString(R.string.base_url_poster);
                    poster_path += o.getString("poster_path");

                    String titulo = o.getString("original_title");
                    String ano = o.getString("release_date");
                    String duracao = o.getString("vote_count");
                    String sinopse = o.getString("overview");
                    String rating = o.getString("vote_average");

                    ListItem item = new ListItem(titulo,
                            poster_path,
                            duracao,
                            ano,
                            sinopse,
                            rating);

                    listItems.add(item);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuId = item.getItemId();
        Context ctx = MainActivity.this;

        if(menuId==R.id.menuit1){
            urlJSON = String.format( getString(R.string.base_url_popular),getString(R.string.APIKEY));
        }else{
            urlJSON = String.format( getString(R.string.base_url_toprated),getString(R.string.APIKEY));
        }

        try {

            if(isOnline()){

                new loadDataInBackground().execute(new URL(urlJSON));

            }else{

                Toast toast = Toast.makeText(getApplicationContext(), R.string.NOINTERNET,
                        Toast.LENGTH_SHORT);
                toast.show();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
