package com.abovis.paginasamarillasapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abovis.paginasamarillasapp.R;
import com.abovis.paginasamarillasapp.activities.DetailPaginaActivity;
import com.abovis.paginasamarillasapp.models.Paginas;

import java.util.ArrayList;
import java.util.List;

public class PaginasAdapter extends RecyclerView.Adapter<PaginasAdapter.ViewHolder> {

    private static final String TAG = PaginasAdapter.class.getSimpleName();

    private AppCompatActivity activity;

    private List<Paginas> paginas;

    public PaginasAdapter(AppCompatActivity activity){
        this.activity = activity;
        paginas = new ArrayList<>();
    }

    public void setPaginas(List<Paginas> paginas){
        this.paginas = paginas;
    }

    @NonNull
    @Override   // Qué layout va a usar y mantenerlo precargado
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_paginas, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override   // Cómo se va a enlazar el dato en cada item layout (ViewHolder)
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Paginas paginas = this.paginas.get(position);

        viewHolder.nameText.setText(paginas.getName());
        viewHolder.direccionText.setText(paginas.getAddress());
        viewHolder.telefonoText.setText(paginas.getPhone());


        Context context = viewHolder.itemView.getContext();
        int resourceid = context.getResources().getIdentifier(
                paginas.getLogo(),
                "drawable",
                context.getPackageName()
        );
        viewHolder.pictureImage.setImageResource(resourceid);

        // Definiendo el evento onclick
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + paginas);

                Intent intent = new Intent(v.getContext(), DetailPaginaActivity.class);
                intent.putExtra("id", paginas.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return paginas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pictureImage;
        TextView nameText;
        TextView direccionText;
        TextView telefonoText;
        TextView detalleText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pictureImage = itemView.findViewById(R.id.picture_image);
            nameText = itemView.findViewById(R.id.name_text);
            direccionText = itemView.findViewById(R.id.direccion_text);
            telefonoText = itemView.findViewById(R.id.telefono_text);
            detalleText = itemView.findViewById(R.id.detalle_text);
        }
    }
}
