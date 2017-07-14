package net.seila.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class childActivity extends AppCompatActivity {


    private TextView titulo;
    private TextView ano;
    private TextView duracao;
    private TextView sinopse;
    private ImageView imgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        titulo = (TextView) findViewById(R.id.titulo);
        ano = (TextView) findViewById(R.id.ano);
        duracao = (TextView) findViewById(R.id.duracao);
        sinopse = (TextView) findViewById(R.id.sinopse);
        imgView = (ImageView) findViewById(R.id.thumb);

        Intent intent2 = getIntent();
        if(intent2.hasExtra(Intent.EXTRA_TEXT)){
            String txtdoIntent = intent2.getStringExtra(Intent.EXTRA_TEXT);
            titulo.setText(txtdoIntent);
        }



    }
}
