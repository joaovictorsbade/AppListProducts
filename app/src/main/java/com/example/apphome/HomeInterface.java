package com.example.apphome;

import static com.example.apphome.GameDao.listAllGames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.apphome.Game;
import com.example.apphome.R;
import com.example.apphome.MyAdapter;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeInterface extends AppCompatActivity {

    public static final String TAG = "Home Activty";

    RecyclerView recyclerView;
    List<Game> game = new ArrayList<>();
    MyAdapter adapter;
    Game androidData;
    private List<Game> gameList;
    SearchView searchView;

    String mGameName , mLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeInterface.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);


        // Recupere a lista de jogos do banco de dados
        List<Game> gameList = listAllGames(HomeInterface.this);

        // Verifique se a lista não está vazia antes de prosseguir
        if (gameList != null && !gameList.isEmpty()) {
            game = new ArrayList<>(gameList); // Defina game como uma nova instância da lista gameList
            adapter = new MyAdapter(HomeInterface.this, gameList);
            recyclerView.setAdapter(adapter);
        } else {
            // Lidar com o caso em que a lista de jogos está vazia (por exemplo, exibir uma mensagem de aviso)
        }
    }

    //    Pesquisa
    private void searchList(String text){
        List<Game> dataSearchList = new ArrayList<>();
        for (Game data : game){
            if (data.getGameName().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}