package com.example.apphome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphome.Game;
import com.example.apphome.MyAdapter;
import static com.example.apphome.GameDao.listAllGames;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeInterface extends AppCompatActivity {

    RecyclerView recyclerView;
    //    List<DataClass> dataList;
//    List<Game> dataList;
    MyAdapter adapter;
    //    DataClass androidData;
    Game androidData;
    SearchView searchView;
    String mGameName, mClassification, mCompanyName;
    List<Game> dataList = new ArrayList<>();
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

//        ...

        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeInterface.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Obtenha a lista de jogos do banco de dados
        dataList = listAllGames(HomeInterface.this);

        // Verifique se a lista não está vazia antes de prosseguir
        if (dataList != null && !dataList.isEmpty()) {
            adapter = new MyAdapter(HomeInterface.this, dataList);
            recyclerView.setAdapter(adapter);
        } else {System.out.println("Erro HomeInterface");
            // Lidar com o caso em que a lista de jogos está vazia (por exemplo, exibir uma mensagem de aviso)
        }

//        ...
    }


    //Pesquisa
    private void searchList(String text) {
        List<Game> dataSearchList = new ArrayList<>();
        for (Game data : dataList) {
            if (data.getGameName().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
