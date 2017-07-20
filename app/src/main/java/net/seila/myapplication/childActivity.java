package net.seila.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class childActivity extends AppCompatActivity {


    private TextView titulo;
    private TextView ano;
    private TextView duracao;
    private TextView sinopse;
    private TextView rating;
    private ImageView imgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        titulo = (TextView) findViewById(R.id.titulo);
        ano = (TextView) findViewById(R.id.ano);
        //duracao = (TextView) findViewById(R.id.duracao);
        sinopse = (TextView) findViewById(R.id.sinopse);
        imgView = (ImageView) findViewById(R.id.thumb);
        rating = (TextView) findViewById(R.id.rating);

        Intent intent2 = getIntent();
        if(intent2.hasExtra("titulo")){
            titulo.setText(intent2.getStringExtra("titulo"));
        }else{
            titulo.setText("");
        }


        if(intent2.hasExtra("ano")){
            String tmpData = intent2.getStringExtra("ano");
            //String[] parts = tmpData.split("-");
            //String tmpAno = parts[0];
            ano.setText(tmpData);
        }else{
            ano.setText("");
        }

        if(intent2.hasExtra("rating")){

            String ratingNumber = intent2.getStringExtra("rating") + " / 10";
            rating.setText(ratingNumber.toString());

        }else{
            rating.setText("");
        }

        if(intent2.hasExtra("sinopse")){
            String aa = intent2.getStringExtra("sinopse");
            sinopse.setText(aa);
        }else{
            sinopse.setText("");
        }

        //poster
        if(intent2.hasExtra("thumb_path")){

            String posterPath = intent2.getStringExtra("thumb_path");

            Picasso.with(getBaseContext())
                    .load(posterPath)
                    .into(imgView);

        }else{
            titulo.setText("");
        }

    }
}
