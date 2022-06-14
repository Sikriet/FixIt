package com.example.fixit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ValoracionAdapter extends RecyclerView.Adapter<ValoracionAdapter.MyViewHolder> {

    List<Valoraciones> valoracionesList;
    Context context;

    public ValoracionAdapter(List<Valoraciones> valoracionesList, Context context) {
        this.valoracionesList = valoracionesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_valoracion, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String nombre_completo = valoracionesList.get(position).getNombre_usuario() + " " + valoracionesList.get(position).getApellido_usuario();
        holder.tv_nombreVal.setText(nombre_completo);
        holder.ratingBar.setRating(Float.parseFloat(valoracionesList.get(position).getCalificacion_valoracion()));
        holder.tv_comentarioVal.setText(valoracionesList.get(position).getComentario_valoracion());
    }

    @Override
    public int getItemCount() {
        return valoracionesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nombreVal;
        RatingBar ratingBar;
        TextView tv_comentarioVal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombreVal = itemView.findViewById(R.id.tv_nombreVal);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tv_comentarioVal = itemView.findViewById(R.id.tv_comentarioVal);
        }
    }
}
