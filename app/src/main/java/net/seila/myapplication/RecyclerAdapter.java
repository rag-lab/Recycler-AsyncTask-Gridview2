package net.seila.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rod on 13/05/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder>{

    private int mNumberItems;
    private ArrayList<Movie> mMovies;
    List<ListItem> listaFilmes;

    private Context context;


    public RecyclerAdapter(List<ListItem> listaFilmes, Context context){
        this.context = context;
        this. listaFilmes = listaFilmes;
    }


    class myViewHolder extends RecyclerView.ViewHolder{

        //cria o item que esta no layout do xml
        public TextView titulo;
        public ImageView capaFilme;


        public myViewHolder(View itemView)
        {
            super(itemView);
            //titulo = (TextView) itemView.findViewById(R.id.itemNumber);
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

        //holder.titulo.setText(listItem.getTitulo());

        Picasso.with(context)
                .load(listItem.getUrlCapa())
                //.resize(185, 278)
                .into(holder.capaFilme);

        Log.v("RAG",listItem.getUrlCapa());

    }


    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }


}
