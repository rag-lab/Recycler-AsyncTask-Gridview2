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


        public myViewHolder(final View itemView)
        {
            super(itemView);
            //titulo = (TextView) itemView.findViewById(R.id.itemNumber);
            capaFilme = (ImageView) itemView.findViewById(R.id.imageView1);

            /*
            capaFilme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(),
                            "clicked",
                            Toast.LENGTH_LONG).show();
                }
            });
            */
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

        /*
        Picasso.with(getApplicationContext())
                                .load(URL)
                                // The placeholder image is shown immediately and
                                // replaced by the remote image when Picasso has
                                // finished fetching it.
                                .placeholder(R.drawable.ic_launcher)
                                //A request will be retried three times before the error placeholder is shown.
                                .error(R.drawable.ic_launcher)
                                // Transform images to better fit into layouts and to
                                // reduce memory size.
                                .resize(250, 400).centerCrop().rotate(180)
                                // No fade animation while displaying the image
                                //.noFade()
                                // To fit the image to the screen, but throws exception while using with resize
                                //.fit()
                                .into(imageView);
         */


        Picasso.with(context)
                .load(listItem.getUrlCapa())
                .into(holder.capaFilme);


        holder.capaFilme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(),
                        "clicked",
                        Toast.LENGTH_LONG).show();

                //Context ctx2 = MainActivity.this;
                Intent intent = new Intent(view.getContext(), childActivity.class);

                //myIntent.putExtra("firstName", "Your First Name Here");
                //myIntent.putExtra("lastName", "Your Last Name Here");

                //intent.putExtra(Intent.EXTRA_TEXT, "banana");
                view.getContext().startActivity(intent);


            }
        });

        //Log.v("RAG",listItem.getUrlCapa());
        //Log.v("RAG",listItem.getTitulo());
        //Log.v("RAG",listItem.getAno());
        //Log.v("RAG",listItem.getDuracao());
        //Log.v("RAG",listItem.getSinopse());

    }


    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }


}
