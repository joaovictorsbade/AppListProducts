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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Game> gameList;
    private List<Game> filteredList;

    public MyAdapter(Context context, List<Game> gameList) {
        this.context = context;
        this.gameList = gameList;
        this.filteredList = new ArrayList<>(gameList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
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
            }
        });

    }

    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase().trim();
                if (query.isEmpty()) {
                    filteredList = new ArrayList<>(gameList);
                } else {
                    List<Game> temp = new ArrayList<>();
                    for (Game game : gameList) {
                        if (game.getGameName().toLowerCase().contains(query) || game.getLink().toLowerCase().contains(query)) {
                            temp.add(game);
                        }
                    }
                    filteredList = temp;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<Game>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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

     public void setSearchList(List<Game> dataSearchList){
       this.gameList = dataSearchList;
         notifyDataSetChanged();
       }

}
