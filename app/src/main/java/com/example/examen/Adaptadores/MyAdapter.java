package com.example.examen.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.R;
import com.example.examen.clases.Datos;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Datos> codigos;
    private Context Ctx;
    public MyAdapter( Context cont,List<Datos> names)
    {
        this.codigos=names;
        this.Ctx=cont;
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

        Glide.with(Ctx)
                .load(pro.getImagen())
                .into(holder.imagen);

        holder.name.setText(pro.getNombre());
        holder.descripcion.setText(pro.getDescripcion());
        holder.precio.setText(String.valueOf(pro.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return codigos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView descripcion;
        TextView precio;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name=(TextView) itemView.findViewById(R.id.txtnombre);
            this.descripcion=(TextView) itemView.findViewById(R.id.txtdescripcion);
            this.precio=(TextView) itemView.findViewById(R.id.txtprecio);
            this.imagen=(ImageView) itemView.findViewById(R.id.imageView);
        }

    }

}