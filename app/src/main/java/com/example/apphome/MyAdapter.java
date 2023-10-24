package com.example.apphome;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.apphome.Game;
import com.example.apphome.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Game> dataList;
    private List<Game> filteredList;

    public MyAdapter(Context context, List<Game> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.filteredList = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void setSearchList(List<Game> dataSearchList) {
        this.filteredList = dataSearchList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView recTitle;
        TextView recDesc;
        TextView recLang;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recTitle = itemView.findViewById(R.id.recTitle);
            recDesc = itemView.findViewById(R.id.recDesc);
            recLang = itemView.findViewById(R.id.recLang);
        }
    }
//...
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (filteredList == null || position < 0 || position >= filteredList.size()) {
            return; // Verifique se a lista está nula ou a posição é inválida
        }
        Game game = filteredList.get(position);

        holder.recTitle.setText(game.getGameName());
        holder.recDesc.setText(game.getCompanyName());
        holder.recLang.setText(game.getClassification());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = game.getLink();

                if (link != null && !link.isEmpty()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    context.startActivity(browserIntent);
                }
                else{System.out.println("deu ruim myadapter");}
            }
        });
    }
//...
}
