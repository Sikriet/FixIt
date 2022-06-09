package com.example.fixit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Producto> productoList;
    Context context;

    public RecyclerViewAdapter(List<Producto> productoList, Context context) {
        this.productoList = productoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_mechanic, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_productName.setText(productoList.get(position).getNombre_producto());
        Glide.with(this.context).load("https://assets.stickpng.com/images/580b585b2edbce24c47b2bdb.png").into(holder.iv_productImage); //TODO: Cambiar la URL por las imagenes obtenidas de internet
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_productImage;
        TextView tv_productName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_productImage = itemView.findViewById(R.id.iv_productImage);
            tv_productName = itemView.findViewById(R.id.tv_productName);
        }
    }
}