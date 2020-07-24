package com.example.examen.Adaptadores;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.example.examen.R;
import com.example.examen.clases.Datos;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Datos> codigos;
    private Context Ctx;
    private OnItemClickListener listener;
    public MyAdapter( Context cont,List<Datos> names, OnItemClickListener listener)
    {
        this.codigos=names;
        this.Ctx=cont;
        this.listener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.recicler_view_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Datos pro=codigos.get(position);
        holder.bind(pro.getNombre(),listener);
      Glide.with(Ctx)
              .load(pro.getImagen())
              .into(holder.imagen);


        holder.descripcion.setText(pro.getNombre());
    }

    @Override
    public int getItemCount() {
        return codigos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView descripcion;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.descripcion=(TextView) itemView.findViewById(R.id.txtnombre);
            this.imagen=(ImageView) itemView.findViewById(R.id.imageView);
        }
        public void bind(final String name, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(name,getAdapterPosition());
                }
            });
        }

    }
    public  interface OnItemClickListener{
        void onItemClick(String name, int position);

    }

}
