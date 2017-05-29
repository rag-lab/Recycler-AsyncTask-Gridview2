package net.seila.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rod on 14/05/2017.
 */

public class Movie implements Serializable {

    private String mUrl;
    private String mTitulo;

    public Movie(JSONObject photoJSON) {
        try {
            mUrl = photoJSON.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public String getUrl() {
        return mUrl;
    }


}
