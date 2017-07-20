package net.seila.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rod on 13/05/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder>{

    private int mNumberItems;
    List<ListItem> listaFilmes;

    private Context context;


    public RecyclerAdapter(List<ListItem> listaFilmes, Context context){
        this.context = context;
        this.listaFilmes = listaFilmes;
    }


    class myViewHolder extends RecyclerView.ViewHolder{

        //cria o item que esta no layout do xml
        public TextView titulo;
        public ImageView capaFilme;


        public myViewHolder(final View itemView)
        {
            super(itemView);
            capaFilme = (ImageView) itemView.findViewById(R.id.imageView1);
        }

    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;

        LayoutInflater inflater = LayoutInflater.from(context); // cria inflater
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately); //

        myViewHolder viewHolder = new myViewHolder(view); //cria viewholder com a view para retornar

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        ListItem listItem = listaFilmes.get(position);

        final String r_titulo = listItem.getTitulo();
        final String r_thumbPath = listItem.getUrlCapa();
        final String r_ano = listItem.getAno();
        final String r_duracao= "200min"; //not used;
        final String r_rating = listItem.getRating();
        final String r_sinopse = listItem.getSinopse();

        Picasso.with(context)
                .load(listItem.getUrlCapa())
                .into(holder.capaFilme);


        holder.capaFilme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), childActivity.class);

                intent.putExtra("titulo", r_titulo); //titulo
                intent.putExtra("thumb_path", r_thumbPath); //thumb path
                intent.putExtra("ano", r_ano); //ano
                intent.putExtra("duracao",r_duracao); //duracao
                intent.putExtra("rating", r_rating); //rating
                intent.putExtra("sinopse", r_sinopse); //sinopse

                view.getContext().startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {

        int a ;

        if(listaFilmes != null && !listaFilmes.isEmpty()) {
            a = listaFilmes.size();
        }
        else {
            a = 0;
        }
        return a;
    }


}
